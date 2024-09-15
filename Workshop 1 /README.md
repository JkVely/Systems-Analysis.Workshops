# Workshop 1 — Entropy and Divide & Conquer

## Overview
This project is part of the **Systems Analysis** course, aimed at developing skills in generating genetic sequences and detecting motifs. The main objectives are to implement a strategy for generating artificial DNA sequences and apply motif detection using **Divide & Conquer** strategies. The sequences are composed of nucleotide bases A, C, G, and T, and the detection of motifs is optimized for entropy analysis.

## Requirements
- Java 8 or higher
- Basic knowledge of bioinformatics
- Knowledge of algorithms for motif detection and entropy analysis

## Features
- **Generate DNA sequences**: The program can create a custom number of random DNA sequences with user-defined lengths and probabilities for each nucleotide base (A, C, G, T). Note: The base probabilities should be specified in the following order:
  - A: Value between 0 and 0.3
  - C: Value between 0.3 and 0.6
  - G: Value between 0.6 and 0.8
  - T: Value between 0.8 and 1
    *Note:* The provided values are suggested thresholds to achieve results that are more reflective of real-life scenarios.
- **Motif Detection**: Detect patterns (motifs) of a specified size within the DNA sequences.
- **File Operations**: Supports reading and writing sequences to files. Users can choose between generating new sequences or reading from an existing file for motif detection.
- **Entropy Filtering**: Uses entropy measures to filter out sequences with high repetition. The entropy threshold must be between 0 and 2, where 2 represents high entropy and 0 represents low entropy.

## How to Run

1. **Generate DNA Sequences and Detect Motifs**:
    - Run the program in write mode to generate new sequences:
    ```bash
    Do you want to generate new sequences or read from an existing file? (write (w)/read (r)): w
    Enter the filename (without extension): dataExample
    Enter the number of sequences to generate: 240240
    Enter the minimum length of these strings: 5
    Enter the maximum length: 100
    Enter the probabilities for each DNA base (A, C, G, T), respectively: 0.25 0.50 0.75 1.00
    Enter the size of the patterns to find: 4
    Enter the entropy threshold value: 1.25
    ```

2. **Read from an Existing File**:
    - Run the program in read mode to detect motifs from an existing file:
    ```bash
    Do you want to generate new sequences or read from an existing file? (write (w)/read (r)): r
    Enter the filename (without extension): dataExample
    Enter the size of the patterns to find: 6
    ```

3. **Output**:
   - The program will return the motifs with the highest number of occurrences in the entire dataset, along with the time it took to find them:
   ```bash
   Motif(s) with the highest number of occurrences (11311):
   Pattern: AGGG - Occurrences: 11311
   It took 873 milliseconds to find the motifs
   ```

## Notes
- Ensure that the base probabilities are provided in the correct order (A, C, G, T) for meaningful results.
- The entropy threshold allows you to filter sequences based on their repetitiveness, with 0 being very repetitive and 2 being more varied.
