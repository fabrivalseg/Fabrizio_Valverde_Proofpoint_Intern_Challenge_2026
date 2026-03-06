package org.fabrizio.streaming.io;

import org.fabrizio.streaming.reporting.ProcessingStatistics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MdWriter {

    private static final String OUTPUT_FILE = "src/main/java/org/fabrizio/streaming/output/report.md";

    public void write(ProcessingStatistics stats) {

        try (Writer writer = new FileWriter(OUTPUT_FILE)) {

            writer.write("# Data Quality Report\n\n");

            writer.write("## Processing Summary\n\n");

            writer.write("- Total input records: " + stats.getTotalInput() + "\n");
            writer.write("- Total output records: " + stats.getTotalOutput() + "\n");
            writer.write("- Discarded records: " + stats.getDiscarded() + "\n");
            writer.write("- Corrected records: " + stats.getCorrected() + "\n");
            writer.write("- Duplicates detected: " + stats.getDuplicates() + "\n\n");


            writer.write("## Deduplication Strategy\n\n");

            writer.write("### How I identify duplicates\n\n");

            writer.write("The main problem was that the input data has a lot of incomplete information. ");
            writer.write("I couldn't simply use series + season + episode because many records have these fields empty or set to 0. ");
            writer.write("That's why I implemented a system with 3 different types of keys, and for each episode I generate the keys that apply depending on what data is available:\n\n");

            writer.write("**1. Primary key (K1)**: `series + season + episode`\n");
            writer.write("   - Used when I have both season and episode valid (> 0)\n");
            writer.write("   - It's the most reliable way to identify an episode\n\n");

            writer.write("**2. Secondary key (K2)**: `series + episode + title`\n");
            writer.write("   - Used when the season is missing but I have the episode number\n");
            writer.write("   - I add the normalized title to avoid false positives\n\n");

            writer.write("**3. Tertiary key (K3)**: `series + season + title`\n");
            writer.write("   - Used when the episode number is missing but I have the season\n");
            writer.write("   - I also use the normalized title to differentiate\n\n");

            writer.write("The idea is that when I process an episode, I generate all the keys I can based on what data I have. ");
            writer.write("Then I check if any of those keys already exists in my index. If I find a match on any of them, ");
            writer.write("it's a duplicate. This allows me to detect duplicates even when records have different missing fields.\n\n");

            writer.write("### Normalization\n\n");

            writer.write("To keep comparisons consistent, I normalize all text:\n\n");

            writer.write("- Remove leading and trailing spaces\n");
            writer.write("- Collapse multiple spaces into one\n");
            writer.write("- Convert everything to lowercase\n\n");

            writer.write("This way I avoid format differences (capitalization, extra spaces, etc.) from preventing duplicate detection.\n\n");

            writer.write("### Selecting the best record\n\n");

            writer.write("When I find duplicates, I keep the one with the best quality. The criteria I use is:\n\n");

            writer.write("1. I prefer episodes with a valid date over those with 'Unknown'\n");
            writer.write("2. I prefer episodes with a real title over 'Untitled Episode'\n");
            writer.write("3. I prefer episodes that have valid season and episode numbers\n");
            writer.write("4. If they tie on everything, I keep the first one that appeared in the file\n\n");

            writer.write("This way I make sure to keep the most complete and useful record.\n");


        } catch (IOException e) {
            throw new RuntimeException("Error writing report.md", e);
        }
    }
}
