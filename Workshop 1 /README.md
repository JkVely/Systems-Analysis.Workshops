# Workshop 1 — Entropy and Divide & Conquer

## Overview
This project is part of the **Systems Analysis** course, aimed at developing skills in generating genetic sequences and detecting motifs. The main objectives are to implement a strategy for generating artificial DNA sequences and apply motif detection using **Divide & Conquer** strategies. The sequences are composed of nucleotide bases A, C, G, and T, and the detection of motifs is optimized for entropy analysis.

## Requirements
- Java 8 or higher
- Basic knowledge of bioinformatics
- Knowledge of algorithms for motif detection and entropy analysis

## Features
- **Generate DNA sequences**: The program can create a custom number of random DNA sequences with user-defined lengths and probabilities for each nucleotide base (A, C, G, T).
- **Motif Detection**: Detect patterns (motifs) of a specified size within the DNA sequences.
- **File Operations**: Supports reading and writing sequences to files. Users can choose between generating new sequences or reading from an existing file for motif detection.
- **Entropy Filtering** (future work): Can potentially use entropy measures to filter out repetitive sequences.

## How to Run

1. **Generate DNA Sequences and Detect Motifs**:
    - Run the program in write mode to generate new sequences:
    ```bash
    Do you want to generate new sequences or read from an existing file? (write (w)/read (r)): w
    Enter the filename (without extention): sequences
    Enter the number of sequences to generate: 100
    Enter the minimum length of these strings: 5
    Enter the maximum length: 100
    Enter the probabilities for each DNA base (A, C, G, T), respectively: 0.25 0.25 0.25 0.25
    Enter the size of the patterns to find: 6
    ```

2. **Read from an Existing File**:
    - Run the program in read mode to detect motifs from an existing file:
    ```bash
    Do you want to generate new sequences or read from an existing file? (write (w)/read (r)): r
    Enter the filename (without extention): sequences
    Enter the size of the patterns to find: 6
    ```
