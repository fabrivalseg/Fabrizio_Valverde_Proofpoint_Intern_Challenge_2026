package org.fabrizio.streaming.processing;

import org.fabrizio.streaming.model.Episode;
import org.fabrizio.streaming.reporting.ProcessingResult;
import org.fabrizio.streaming.reporting.ProcessingStatistics;

import java.util.ArrayList;
import java.util.List;

public class EpisodeProcessor {

    private final EpisodeNormalizer normalizer = new EpisodeNormalizer();
    private final EpisodeValidator validator = new EpisodeValidator();

    public ProcessingResult process(List<Episode> rawEpisodes) {

        ProcessingStatistics stats = new ProcessingStatistics();
        List<Episode> validEpisodes = new ArrayList<>();

        for (Episode raw : rawEpisodes) {

            stats.incrementTotalInput();

            Episode normalized = normalizer.normalize(raw);
            if (normalizer.wasCorrected(raw, normalized)){
                stats.incrementCorrected();
            }

            if (!validator.isValid(normalized)) {
                stats.incrementDiscarded();
                continue;
            }

            validEpisodes.add(normalized);
        }

        EpisodeDeduplicator deduplicator = new EpisodeDeduplicator();
        List<Episode> deduplicated = deduplicator.deduplicate(validEpisodes, stats);

        stats.setTotalOutput(deduplicated.size());

        return new ProcessingResult(deduplicated, stats);
    }
}
