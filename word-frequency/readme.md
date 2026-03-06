# Word Frequency Analysis

This program performs a **word frequency analysis** on a text file.

It reads a text file, counts how many times each word appears, and prints the **10 most frequent words**.

The comparison is **case-insensitive** and ignores punctuation and special characters.

## Features

- Case-insensitive word comparison
- Removal of punctuation and special characters using regular expressions
- Efficient word counting`
- Streaming file processing line-by-line to avoid loading the entire file into memory

## How It Works

1. The program opens the input text file.
2. The file is processed **line by line**.
3. Each line is normalized:
4. Words are extracted using a regular expression.
5. Each word is counted.
6. The program prints the 10 most frequent words.