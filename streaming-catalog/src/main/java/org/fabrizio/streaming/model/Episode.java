package org.fabrizio.streaming.model;

import java.time.LocalDate;
import java.util.Optional;

public class Episode {
    private final String seriesName;
    private final int seasonNumber;
    private final int episodeNumber;
    private final String episodeTitle;
    private final Optional<LocalDate> airDate;

    public Episode(String seriesName, int seasonNumber, int episodeNumber, String episodeTitle, Optional<LocalDate> airDate) {
        this.seriesName = seriesName;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.episodeTitle = episodeTitle;
        this.airDate = airDate;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public Optional<LocalDate> getAirDate() {
        return airDate;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "seriesName='" + seriesName + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", episodeNumber=" + episodeNumber +
                ", episodeTitle='" + episodeTitle + '\'' +
                ", airDate=" + airDate +
                '}';
    }
}
