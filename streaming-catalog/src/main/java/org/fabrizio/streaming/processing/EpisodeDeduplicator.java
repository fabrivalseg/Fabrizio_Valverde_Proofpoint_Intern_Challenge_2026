package org.fabrizio.streaming.processing;

import org.fabrizio.streaming.model.Episode;
import org.fabrizio.streaming.reporting.ProcessingStatistics;

import java.util.*;

public class EpisodeDeduplicator {

    private final EpisodeQualityComparator comparator = new EpisodeQualityComparator();

    public List<Episode> deduplicate(List<Episode> episodes, ProcessingStatistics stats) {

        Map<String, Episode> index = new LinkedHashMap<>();

        for (Episode episode : episodes) {
            List<String> keys = generateKeys(episode);

            Episode existing = keys.stream()
                    .map(index::get)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);

            if (existing == null) {
                keys.forEach(k -> index.put(k, episode));
            } else {
                stats.incrementDuplicates();
                Episode best = comparator.compare(episode, existing) > 0 ? episode : existing;
                generateKeys(existing).forEach(k -> index.put(k, best));
                generateKeys(episode).forEach(k -> index.put(k, best));
            }
        }

        return new ArrayList<>(new LinkedHashSet<>(index.values()));
    }

    private List<String> generateKeys(Episode e) {
        List<String> keys = new ArrayList<>();
        String series = normalize(e.getSeriesName());
        String title = normalize(e.getEpisodeTitle());
        int season  = e.getSeasonNumber();
        int episode = e.getEpisodeNumber();

        if (season != 0 && episode != 0)
            keys.add("K1|" + series + "|" + season + "|" + episode);

        if (season == 0 && episode != 0)
            keys.add("K2|" + series + "|0|" + episode + "|" + title);

        if (season != 0 && episode == 0)
            keys.add("K3|" + series + "|" + season + "|0|" + title);

        return keys;
    }

    private String normalize(String value) {
        if (value == null) return "";
        return value.trim().replaceAll("\\s+", " ").toLowerCase();
    }
}
