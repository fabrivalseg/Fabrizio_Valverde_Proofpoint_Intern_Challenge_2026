# Data Quality Report

## Processing Summary

- Total input records: 16
- Total output records: 9
- Discarded records: 2
- Corrected records: 7
- Duplicates detected: 5

## Deduplication Strategy

### How I identify duplicates

The main problem was that the input data has a lot of incomplete information. I couldn't simply use series + season + episode because many records have these fields empty or set to 0. That's why I implemented a system with 3 different types of keys, and for each episode I generate the keys that apply depending on what data is available:

**1. Primary key (K1)**: `series + season + episode`
   - Used when I have both season and episode valid (> 0)
   - It's the most reliable way to identify an episode

**2. Secondary key (K2)**: `series + episode + title`
   - Used when the season is missing but I have the episode number
   - I add the normalized title to avoid false positives

**3. Tertiary key (K3)**: `series + season + title`
   - Used when the episode number is missing but I have the season
   - I also use the normalized title to differentiate

The idea is that when I process an episode, I generate all the keys I can based on what data I have. Then I check if any of those keys already exists in my index. If I find a match on any of them, it's a duplicate. This allows me to detect duplicates even when records have different missing fields.

### Normalization

To keep comparisons consistent, I normalize all text:

- Remove leading and trailing spaces
- Collapse multiple spaces into one
- Convert everything to lowercase

This way I avoid format differences (capitalization, extra spaces, etc.) from preventing duplicate detection.

### Selecting the best record

When I find duplicates, I keep the one with the best quality. The criteria I use is:

1. I prefer episodes with a valid date over those with 'Unknown'
2. I prefer episodes with a real title over 'Untitled Episode'
3. I prefer episodes that have valid season and episode numbers
4. If they tie on everything, I keep the first one that appeared in the file

This way I make sure to keep the most complete and useful record.
