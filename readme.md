# Proofpoint Intern Program 2026 – Technical Challenge

This repository contains my solutions for the technical challenge required for the **Proofpoint Intern Program 2026**.

## Repository Structure

Fabrizio_Valverde_Proofpoint_Intern_Challenge_2026
│
├── streaming-catalog
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── org.fabrizio.streaming
│   │               ├── input
│   │               │   └── episodes.csv
│   │               │
│   │               ├── io
│   │               │   ├── CsvReader.java
│   │               │   ├── CsvWriter.java
│   │               │   └── MdWriter.java
│   │               │
│   │               ├── model
│   │               │   └── Episode.java
│   │               │
│   │               ├── processing
│   │               │   ├── EpisodeProcessor.java
│   │               │   ├── EpisodeNormalizer.java
│   │               │   ├── EpisodeValidator.java
│   │               │   ├── EpisodeDeduplicator.java
│   │               │   └── EpisodeQualityComparator.java
│   │               │
│   │               ├── reporting
│   │               │   ├── ProcessingResult.java
│   │               │   └── ProcessingStatistics.java
│   │               │
│   │               ├── output
│   │               │   ├── episodes_clean.csv
│   │               │   └── report.md
│   │               │
│   │               └── Main.java
│
└── word-frequency-analysis
    ├── main.py
    ├── file.txt
    └── README.md