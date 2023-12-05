package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("src\\main\\java\\org\\example\\inputs\\Day4.txt"));
            System.out.println("lines = " + input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = input.toArray(new String[0]);
        int grandTotal = 0;

        Part1(lines, grandTotal);
//        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
//            String currentLine = lines[lineIndex];
//            String[] parts = lines[lineIndex].split("\\|");
//            if (parts.length == 2) {
//                String winningNumbersStr = parts[0].replaceAll("Card \\d+:", "").trim();
//                String playedNumbersStr = parts[1].trim();
//
//                String[] winningNumbers = winningNumbersStr.split("\\s+");
//                String[] playedNumbers = playedNumbersStr.split("\\s+");
//
//                int matchCount = countMatchingNumbers(winningNumbers, playedNumbers);
//
//                for (int i = matchCount; i > 0; i--) {
//                    String[] duplicatedLine = Arrays.copyOf(lines, lines.length + 1);
//                    insertLine(duplicatedLine, lineIndex + 1, lines[lineIndex]);
//                    lines = duplicatedLine;
//                }
//            }
//        }
    }

    private static void insertLine(String[] array, int index, String value) {
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[index] = value;
    }

    private static void Part1(String[] lines, int grandTotal) {
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String[] parts = lines[lineIndex].split("\\|");

            if (parts.length == 2) {
                String winningNumbersStr = parts[0].replaceAll("Card \\d+:","").trim();
                String playedNumbersStr = parts[1].trim();

                String[] winningNumbers = winningNumbersStr.split("\\s+");
                String[] playedNumbers = playedNumbersStr.split("\\s+");

                int matchCount = countMatchingNumbers(winningNumbers, playedNumbers);
                int points = calculatePoints(matchCount);
                grandTotal += points;

                System.out.println("Winning Numbers: " + Arrays.toString(winningNumbers));
                System.out.println("Played Numbers: " + Arrays.toString(playedNumbers));
                System.out.println("Match Count: " + matchCount);
                System.out.println("Card Points = " + points);
                System.out.println("Grand Total = " + grandTotal);
                System.out.println("-".repeat(50));
            }

        }
    }
    private static int countMatchingNumbers(String[] winningNumbers, String[] playedNumbers) {
        Set<String> winningSet = new HashSet<>(Arrays.asList(winningNumbers));
        Set<String> playedSet = new HashSet<>(Arrays.asList(playedNumbers));

        winningSet.retainAll(playedSet);

        return winningSet.size();
    }
    private static int calculatePoints(int matchCount) {
        return (int) Math.pow(2, matchCount - 1);
    }
}
