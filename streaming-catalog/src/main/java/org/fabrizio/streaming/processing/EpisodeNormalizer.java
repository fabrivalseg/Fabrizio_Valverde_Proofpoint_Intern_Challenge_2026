package org.fabrizio.streaming.processing;

import org.fabrizio.streaming.model.Episode;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class EpisodeNormalizer {

    public Episode normalize(Episode raw) {
        return new Episode(
                normalizeString(raw.getSeriesName()),
                normalizeNumber(raw.getSeasonNumber()),
                normalizeNumber(raw.getEpisodeNumber()),
                normalizeTitle(raw.getEpisodeTitle()),
                normalizeDate(raw.getAirDate())
        );
    }

    public boolean wasCorrected(Episode before, Episode after) {
        return !Objects.equals(before.getSeriesName(),   after.getSeriesName())
                || !Objects.equals(before.getEpisodeTitle(), after.getEpisodeTitle())
                || before.getSeasonNumber()  != after.getSeasonNumber()
                || before.getEpisodeNumber() != after.getEpisodeNumber()
                || !Objects.equals(before.getAirDate(), after.getAirDate());
    }

    private String normalizeString(String value) {
        if (value == null) return "";
        return value.trim().replaceAll("\\s+", " ");
    }

    private int normalizeNumber(int number) {
        return Math.max(number, 0);
    }

    private String normalizeTitle(String title) {
        if (title == null) return "Untitled Episode";
        String cleaned = title.trim().replaceAll("\\s+", " ");
        return cleaned.isEmpty() ? "Untitled Episode" : cleaned;
    }

    private Optional<LocalDate> normalizeDate(Optional<LocalDate> date) {
        return date.filter(d -> d.getYear() > 0);
    }
}