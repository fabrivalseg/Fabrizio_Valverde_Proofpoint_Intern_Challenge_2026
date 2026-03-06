package org.fabrizio.streaming.reporting;

import org.fabrizio.streaming.model.Episode;

import java.util.List;

public class ProcessingResult {

    private final List<Episode> cleanEpisodes;
    private final ProcessingStatistics statistics;

    public ProcessingResult(List<Episode> cleanEpisodes,
                            ProcessingStatistics statistics) {
        this.cleanEpisodes = cleanEpisodes;
        this.statistics = statistics;
    }

    public List<Episode> getCleanEpisodes() {
        return cleanEpisodes;
    }

    public ProcessingStatistics getStatistics() {
        return statistics;
    }
}
