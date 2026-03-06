package org.fabrizio.streaming;

import org.fabrizio.streaming.io.CsvReader;
import org.fabrizio.streaming.io.CsvWriter;
import org.fabrizio.streaming.io.MdWriter;
import org.fabrizio.streaming.model.Episode;
import org.fabrizio.streaming.processing.EpisodeProcessor;
import org.fabrizio.streaming.reporting.ProcessingResult;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String inputPath = "src/main/java/org/fabrizio/streaming/input/episodes.csv";

        CsvReader reader = new CsvReader();
        List<Episode> rawEpisodes = reader.read(inputPath);

        EpisodeProcessor processor = new EpisodeProcessor();
        ProcessingResult result = processor.process(rawEpisodes);

        CsvWriter writer = new CsvWriter();
        writer.write(result.getCleanEpisodes());

        MdWriter mdWriter = new MdWriter();
        mdWriter.write(result.getStatistics());

        System.out.println("Total input: " + result.getStatistics().getTotalInput());
        System.out.println("Total output: " + result.getStatistics().getTotalOutput());
        System.out.println("Discarded records: " + result.getStatistics().getDiscarded());
        System.out.println("Corrected records: " + result.getStatistics().getCorrected());
        System.out.println("Duplicates detected: " + result.getStatistics().getDuplicates());
        System.out.println("File created successfully");

    }
}