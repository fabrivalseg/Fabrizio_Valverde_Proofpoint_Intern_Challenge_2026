# Data Quality Report

## Processing Summary

- Total input records: 48
- Total output records: 25
- Discarded records: 4
- Corrected records: 12
- Duplicates detected: 18

## Deduplication Strategy

### Identification of Duplicates

Episodes are considered duplicates when they refer to the same logical episode. Due to incomplete or missing data in the source file, a flexible multi-key approach was implemented to handle different scenarios where critical identifying information may be absent.

**Three matching strategies are applied:**

1. **Primary Key (K1)**: Series name + Season number + Episode number  
   - Applied when both season and episode numbers are valid (> 0)
   - This is the most reliable identifier for complete records

2. **Secondary Key (K2)**: Series name + Episode number + Episode title  
   - Applied when season number is missing but episode number exists
   - Uses normalized title as additional discriminator
   - Handles cases where episodes lack season information

3. **Tertiary Key (K3)**: Series name + Season number + Episode title  
   - Applied when episode number is missing but season exists
   - Uses normalized title as additional discriminator
   - Handles cases where episodes lack episode numbering

**Why this approach?**  
This multi-key strategy was chosen because the source data contains records with incomplete information. A single rigid key would miss duplicates or fail to identify records correctly. By generating multiple keys per episode and checking all of them, the system can detect duplicates even when some fields are missing, while avoiding false positives by incorporating title information when numerical identifiers are absent.

### Normalization Process

All text fields used in key generation are normalized to ensure consistent matching:

- Leading and trailing whitespace removed
- Multiple consecutive spaces collapsed into single spaces
- Case-insensitive comparison (converted to lowercase)

This ensures that minor formatting differences don't prevent duplicate detection.

### Quality-Based Selection

When duplicate entries are found, the record with the highest quality is retained. Quality is determined by the following criteria, applied in order:

1. **Air Date**: Episodes with a valid date are preferred over 'Unknown'
2. **Episode Title**: Episodes with an actual title are preferred over 'Untitled Episode'
3. **Complete Numbering**: Episodes with both valid season and episode numbers (> 0) are preferred
4. **Insertion Order**: If all quality metrics are equal, the first occurrence in the source file is retained

This ensures that the most complete and informative record is preserved when merging duplicates.
