import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BioInformatics {

    private double[] probabilities;
    private Map<String, Integer> patternCount;

    /**
     * Constructor for generating random DNA sequences and detecting motifs.
     *
     * @param loops      number of sequences to generate
     * @param minSize    minimum size of each sequence
     * @param maxSize    maximum size of each sequence
     * @param probabilities probabilities of each base (A, C, G, T)
     * @param motifSize  size of the motif to detect
     * @param filename   output file name
     */
    public BioInformatics(int loops, int minSize, int maxSize, double[] probabilities, int motifSize, String filename) {
        this.patternCount = new HashMap<>();
        this.probabilities = probabilities;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < loops; i++) {
                int sequenceSize = (int) (Math.random() * (maxSize - minSize)) + minSize;
                String sequence = generateSequence(sequenceSize);
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
        for (int i = 0; i <= sequence.length() - motifSize; i++) {
            String pattern = sequence.substring(i, i + motifSize);
            patternCount.put(pattern, patternCount.getOrDefault(pattern, 0) + 1);
        }
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
     * Prints the count of each pattern found in the DNA sequences.
     * 
     * This method iterates over the patternCount map and prints each pattern along with its occurrence count.
     * 
     * @see #patternCount
     */
    public void printPatternCount() {
        patternCount.forEach((key, value) ->
                System.out.println("Pattern: " + key + " - Occurrences: " + value)
        );
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

        while (true) {
            System.out.println("Do you want to generate new sequences or read from an existing file? (write (w)/read (r)):");
            String mode = sc.nextLine().trim().toLowerCase();

            if (mode.equals("w") || mode.equals("write") || mode.equals("r") || mode.equals("read")) {
                boolean writeMode = mode.equals("w") || mode.equals("write");
                break;
            } else {
                System.out.println("Invalid option. Please choose \"write (w)\" or \"read (r)\".");
            }
        }

        System.out.println("Enter the filename:");
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
            sc.close();

            BioInformatics bio = new BioInformatics(loops, min, max, probabilities, patternSize, filename);
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

