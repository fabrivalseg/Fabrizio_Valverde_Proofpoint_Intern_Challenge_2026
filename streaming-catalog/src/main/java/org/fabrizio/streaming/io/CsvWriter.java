package org.fabrizio.streaming.io;

import org.fabrizio.streaming.model.Episode;

import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class CsvWriter {

    private static final String OUTPUT_FILE = "src/main/java/org/fabrizio/streaming/output/episodes_clean.csv";

    public void write(List<Episode> cleanEpisodes) {

        try (
                Writer writer = new FileWriter(OUTPUT_FILE);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader(
                                "Series Name",
                                "Season Number",
                                "Episode Number",
                                "Episode Title",
                                "Air Date"
                        ))
        ) {

            for (Episode episode : cleanEpisodes) {

                String airDate = episode.getAirDate()
                        .map(date -> date.toString())
                        .orElse("Unknown");

                csvPrinter.printRecord(
                        episode.getSeriesName(),
                        episode.getSeasonNumber(),
                        episode.getEpisodeNumber(),
                        episode.getEpisodeTitle(),
                        airDate
                );
            }

            csvPrinter.flush();

        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file", e);
        }
    }
}
