package org.fabrizio.streaming.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.fabrizio.streaming.model.Episode;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvReader {

    public List<Episode> read(String filePath) {
        List<Episode> episodes = new ArrayList<>();

        try (Reader reader = new FileReader(filePath)) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .parse(reader);

            for (CSVRecord record : records) {
                String seriesName = record.size() > 0 ? record.get(0) : "";
                String seasonStr = record.size() > 1 ? record.get(1) : "";
                String episodeStr = record.size() > 2 ? record.get(2) : "";
                String title = record.size() > 3 ? record.get(3) : "";
                String airDateStr = record.size() > 4 ? record.get(4) : "";

                int seasonNumber = parseInteger(seasonStr);
                int episodeNumber = parseInteger(episodeStr);

                Optional<LocalDate> airDate = parseDate(airDateStr);

                Episode episode = new Episode(
                        seriesName,
                        seasonNumber,
                        episodeNumber,
                        title,
                        airDate
                );

                episodes.add(episode);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        return episodes;
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private Optional<LocalDate> parseDate(String value) {
        try {
            return Optional.of(LocalDate.parse(value.trim()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}