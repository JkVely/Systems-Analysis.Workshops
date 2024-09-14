# BioInformatics

## Overview

This project focuses on **motif detection** in RNA sequences. A motif is a specific subsequence that repeats within an RNA chain. The RNA chains are procedurally generated based on parameters such as the number of sequences and their length intervals. The program detects and counts the frequency of specified motifs within these sequences.

## Features

- **Procedural RNA Sequence Generation:** Generates RNA sequences with customizable lengths and base probabilities.
- **Motif Detection:** Identifies and counts occurrences of motifs (subsequences of specified length) within generated RNA sequences.
- **Flexible Configuration:** Adjust the number of sequences, length intervals, base probabilities, and motif length.

## Requirements

- Java 8 or higher

## Installation

1. **Clone the Repository:**
 ```bash
   git clone https://github.com/JkVely/BioInformatics.git
```
2. **Navigate to the Project Directory:**

```bash
cd BioInformatics
```

## Usage
1. **Compile the Code:**

```bash
javac BioInformatics.java
```
2. **Run the Program:**

```bash
java BioInformatics
```
3. **Follow the Prompts:**

The program will prompt you to enter:

- The number of sequences to generate
- The minimum and maximum length of the sequences
- The probabilities for each base (A, C, G, T)
- The length of the motifs to find

After entering these values, the program will generate the RNA sequences, detect motifs, and print the count of each motif found.

## Example
```plaintext
Ingrese el numero de secuencias a generar: 
50
Digite el largo minimo de dichas cadenas:
100
Ahora digite el largo maximo:
200
Ingrese las probabilidades para cada base:
0.25
0.5
0.75
1
Ingrese el tamaño de los patrones a encontrar:
2
```
## Output
```plaintext
Patrón: AA - Ocurrencias: 438
Patrón: TT - Ocurrencias: 428
Patrón: GG - Ocurrencias: 409
Patrón: CC - Ocurrencias: 448
Patrón: AC - Ocurrencias: 503
Patrón: CG - Ocurrencias: 496
Patrón: AG - Ocurrencias: 429
Patrón: GT - Ocurrencias: 474
Patrón: TA - Ocurrencias: 457
Patrón: TC - Ocurrencias: 437
Patrón: CT - Ocurrencias: 473
Patrón: AT - Ocurrencias: 432
Patrón: TG - Ocurrencias: 482
Patrón: GA - Ocurrencias: 442
Patrón: GC - Ocurrencias: 489
Patrón: CA - Ocurrencias: 458
```
