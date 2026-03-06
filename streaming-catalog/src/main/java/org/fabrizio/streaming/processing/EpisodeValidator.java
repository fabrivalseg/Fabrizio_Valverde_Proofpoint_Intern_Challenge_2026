package org.fabrizio.streaming.processing;

import org.fabrizio.streaming.model.Episode;

public class EpisodeValidator {

    public boolean isValid(Episode episode) {

        if (episode.getSeriesName() == null || episode.getSeriesName().isBlank()) {
            return false;
        }

        boolean missingEpisodeNumber = episode.getEpisodeNumber() == 0;
        boolean missingTitle = episode.getEpisodeTitle().equals("Untitled Episode");
        boolean missingAirDate = episode.getAirDate().isEmpty();

        if (missingEpisodeNumber && missingTitle && missingAirDate) {
            return false;
        }

        return true;
    }
}
