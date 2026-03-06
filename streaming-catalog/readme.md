# Streaming Catalog - Intern Challenge

Java project that processes an episode catalog from a CSV file with inconsistent data.
The goal is to clean records, discard invalid entries, detect duplicates, and generate analysis-ready outputs.

## What it does

1. Reads `input/episodes.csv`.
2. Normalizes fields (text, numbers, and dates).
3. Validates minimum required records.
4. Deduplicates episodes with a flexible key strategy.
5. Generates:
	 - `output/episodes_clean.csv`
	 - `output/report.md`

## Main Rules

- **Normalization**:
	- `trim` and collapse spaces in text.
	- Negative numbers become `0`.
	- Empty or null title becomes `Untitled Episode`.
	- Invalid dates remain empty (`Unknown` in output).

- **Validation**:
	- Discarded if `seriesName` is empty.
	- Discarded if missing all at once: episode number, real title, and date.

- **Deduplication**:
	- K1: `series + season + episode`
	- K2: `series + episode + title` (when season is missing)
	- K3: `series + season + title` (when episode number is missing)
	- On conflict, the best quality record is kept (valid date, real title, valid numbers).

## Structure

- `src/main/java/org/fabrizio/streaming/input/episodes.csv`: source file.
- `src/main/java/org/fabrizio/streaming/output/episodes_clean.csv`: clean catalog.
- `src/main/java/org/fabrizio/streaming/output/report.md`: quality report.
- `src/main/java/org/fabrizio/streaming/Main.java`: entry point.

## Requirements

- Java 25
- Maven

## Execution

From the `streaming-catalog` folder:

```bash
mvn clean compile
```

Then run `Main` from an IDE (VS Code/IntelliJ) to generate the output files.
