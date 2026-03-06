package org.fabrizio.streaming.processing;

import org.fabrizio.streaming.model.Episode;

import java.util.Comparator;

public class EpisodeQualityComparator implements Comparator<Episode> {

    @Override
    public int compare(Episode e1, Episode e2) {

        int dateCompare = Boolean.compare(
                e1.getAirDate().isPresent(),
                e2.getAirDate().isPresent()
        );
        if (dateCompare != 0) return dateCompare;

        boolean e1HasRealTitle = !e1.getEpisodeTitle().equals("Untitled Episode");
        boolean e2HasRealTitle = !e2.getEpisodeTitle().equals("Untitled Episode");

        int titleCompare = Boolean.compare(e1HasRealTitle, e2HasRealTitle);
        if (titleCompare != 0) return titleCompare;

        boolean e1ValidNumbers = e1.getSeasonNumber() > 0 && e1.getEpisodeNumber() > 0;
        boolean e2ValidNumbers = e2.getSeasonNumber() > 0 && e2.getEpisodeNumber() > 0;

        return Boolean.compare(e1ValidNumbers, e2ValidNumbers);
    }
}
