import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BioInformatics {
    private double[] probabilities;
    private Map<String, Integer> patternCount;
    private double entropyThreshold;
    private long duration;

    /**
     * Constructor for generating random DNA sequences and detecting motifs.
     *
     * @param loops      number of sequences to generate
     * @param minSize    minimum size of each sequence
     * @param maxSize    maximum size of each sequence
     * @param probabilities probabilities of each base (A, C, G, T)
     * @param motifSize  size of the motif to detect
     * @param filename   output file name
     * @param entropyThreshold   threshold for entropy calculation
     */
    public BioInformatics(int loops, int minSize, int maxSize, double[] probabilities, int motifSize, String filename, double  entropyThreshold) {
        this.patternCount = new HashMap<>();
        this.probabilities = probabilities;
        this.entropyThreshold = entropyThreshold;
        this.duration = 0;
        filename  = "./data/"+ filename + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < loops; i++) {
                String sequence;
                do {
                    int sequenceSize = (int) (Math.random() * (maxSize - minSize)) + minSize;
                    sequence = generateSequence(sequenceSize);
                } while (calculateEntropy(sequence) < this.entropyThreshold);

                writer.write(sequence);
                writer.newLine();
                detectMotif(sequence, motifSize);
            }
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }

    /**
     * Constructor for reading and detecting motifs from a file.
     *
     * @param motifSize  size of the motif to detect
     * @param filename   input file name
     */
    public BioInformatics(int motifSize,  String filename) {
        this.patternCount = new HashMap<>();
        this.duration = 0;
        filename = "./data/" + filename +  ".txt";
        readAndDetectMotifFromFile(filename, motifSize);
    }

    /**
     * Generates a random DNA sequence of the given size.
     *
     * @param size size of the sequence
     * @return random DNA sequence
     */
    private String generateSequence(int size) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < size; i++) {
            double r = Math.random();
            if (r < probabilities[0]) {
                sequence.append('A');
            } else if (r < probabilities[1]) {
                sequence.append('C');
            } else if (r < probabilities[2]) {
                sequence.append('G');
            } else {
                sequence.append('T');
            }
        }
        return sequence.toString();
    }

    /**
     * Detects motifs in a given sequence.
     *
     * @param sequence  DNA sequence
     * @param motifSize size of the motif
     */
    private void detectMotif(String sequence, int motifSize) {
        long  startTime = System.currentTimeMillis();
        for (int i = 0; i <= sequence.length() - motifSize; i++) {
            String pattern = sequence.substring(i, i + motifSize);
            patternCount.put(pattern, patternCount.getOrDefault(pattern, 0) + 1);
        }
        long  endTime = System.currentTimeMillis();
        long sequenceDuration = endTime - startTime;
        this.duration += sequenceDuration;
    }

    /**
     * Reads and detects motifs from a file.
     *
     * @param filename   input file name
     * @param motifSize  size of the motif to detect
     */
    private void readAndDetectMotifFromFile(String filename, int motifSize) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                detectMotif(line, motifSize);
            }
        } catch (IOException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
        }
    }

    /**
     * Prints the motif(s) with the highest number of occurrences.
     * 
     * This method finds the maximum number of occurrences in the patternCount map
     * and prints each motif with that number of occurrences. If there are multiple motifs
     * with the maximum number of occurrences, it selects the one with the highest number of
     * consecutive repeated bases.
     * 
     * @see #patternCount
     */
    public void printPatternCount() {
        int maxOccurrences = patternCount.values().stream()
                                        .max(Integer::compareTo)
                                        .orElse(0);
        
        Map<String, Integer> maxMotifs = patternCount.entrySet().stream()
                .filter(entry -> entry.getValue() == maxOccurrences)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        String bestMotif = maxMotifs.keySet().stream()
                .max(Comparator.comparingInt(this::countConsecutiveRepeatedBases))
                .orElse("");

        System.out.println("Motif(s) with the highest number of occurrences (" + maxOccurrences + "):");
        if (!bestMotif.isEmpty()) {
            System.out.println("Pattern: " + bestMotif + " - Occurrences: " + maxMotifs.get(bestMotif));
        }

        System.out.println("It took " + this.duration + " milliseconds to find the motifs");
    }

    /**
     * Counts the number of consecutive repeated bases in a given motif.
     * 
     * @param motif The motif for which to count consecutive repeated bases.
     * @return The count of the highest number of consecutive repeated bases.
     */
    private int countConsecutiveRepeatedBases(String motif) {
        int maxCount = 0;
        int currentCount = 1;

        for (int i = 1; i < motif.length(); i++) {
            if (motif.charAt(i) == motif.charAt(i - 1)) {
                currentCount++;
            } else {
                maxCount = Math.max(maxCount, currentCount);
                currentCount = 1;
            }
        }
        maxCount = Math.max(maxCount, currentCount);

        return maxCount;
    }

    /**
     * Calculates the Shannon entropy of a given DNA sequence.
     * 
     * Shannon entropy is a measure of the uncertainty or randomness in a probability distribution.
     * In the context of DNA sequences, it can be used to quantify the complexity or variability of the sequence.
     * 
     * This method calculates the Shannon entropy of the given DNA sequence by first determining the frequency of each base (A, C, G, T),
     * then using these frequencies to calculate the entropy.
     * 
     * @param sequence the DNA sequence for which to calculate the Shannon entropy
     * @return the Shannon entropy of the given DNA sequence
     */
    private double calculateEntropy(String sequence) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char base : sequence.toCharArray()) {
            freqMap.put(base, freqMap.getOrDefault(base, 0) + 1);
        }

        double entropy = 0.0;
        int length = sequence.length();
        for (int count : freqMap.values()) {
            double probability = (double) count / length;
            entropy -= probability * Math.log(probability) / Math.log(2);
        }

        return entropy;
    }

    /**
     * Main entry point of the program.
     * 
     * This method provides a command-line interface for the user to interact with the BioInformatics class.
     * It prompts the user to choose between generating new DNA sequences or reading from an existing file,
     * and then guides the user through the process of providing the necessary input parameters.
     * 
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean writeMode;

        while (true) {
            System.out.println("Do you want to generate new sequences or read from an existing file? (write (w)/read (r)):");
            String mode = sc.nextLine().trim().toLowerCase();
            if (mode.equals("w") || mode.equals("write") || mode.equals("r") || mode.equals("read")) {
                writeMode = mode.equals("w") || mode.equals("write");
                break;
            } else {
                System.out.println("Invalid option. Please choose \"write (w)\" or \"read (r)\".");
            }
        }

        System.out.println("Enter the filename (without the extention):");
        String filename = sc.nextLine();

        if (writeMode) {
            System.out.println("Enter the number of sequences to generate:");
            int loops = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the minimum length of these strings:");
            int min = sc.nextInt();
            sc.nextLine();
            int max;
            do {
                System.out.println("Now enter the maximum length: ");
                max = sc.nextInt();
                sc.nextLine();
                if (max <= min) {
                    System.out.println("The maximum length must be greater than the minimum length.");
                }
            } while (max <= min);
            System.out.println("Enter the probabilities for each DNA base (A, C, G, T), respectively:");
            double probA = sc.nextDouble();
            double probC = sc.nextDouble();
            double probG = sc.nextDouble();
            double probT = sc.nextDouble();
            sc.nextLine();
            double[] probabilities = {probA, probC, probG, probT};
            System.out.println("Enter the size of the patterns to find: ");
            int patternSize = sc.nextInt();
            System.out.println("Enter the entropy threshold value: ");
            double entropyThreshold = sc.nextDouble();
            sc.close();

            BioInformatics bio = new BioInformatics(loops, min, max, probabilities, patternSize, filename, entropyThreshold);
            bio.printPatternCount();

        } else {
            System.out.println("Enter the size of the patterns to find: ");
            int patternSize = sc.nextInt();
            sc.close();

            BioInformatics bio = new BioInformatics(patternSize, filename);
            bio.printPatternCount();
        }
    }
}
