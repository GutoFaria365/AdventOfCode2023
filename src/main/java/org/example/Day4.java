package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day4 {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("src\\main\\java\\org\\example\\inputs\\Day4.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = input.toArray(new String[0]);
        List<String> linesList = new ArrayList<>(Arrays.asList(lines));
        List<String> linesListTemp = new ArrayList<>(Arrays.asList(lines));
        int iteration=0;
        int grandTotal = 0;
        int grandTotal2 = 0;

        grandTotal = part1(lines, grandTotal);
        grandTotal2 = part2(linesList, linesListTemp, iteration, grandTotal2);
        System.out.println("Part1 total = " + grandTotal);
        System.out.println("Part2 total = " + grandTotal2);
    }

    private static int part2(List<String> linesList, List<String> linesListTemp, int iteration, int grandTotal2) {
        for (int lineIndex = 0; lineIndex < linesList.size(); lineIndex++) {

            String[] parts = linesList.get(lineIndex).split("\\|");
            String winningNumbersStr = parts[0].replaceAll("Card \\d+:", "").trim();
            String playedNumbersStr = parts[1].trim();

            String[] winningNumbers = winningNumbersStr.split("\\s+");
            String[] playedNumbers = playedNumbersStr.split("\\s+");

            int matchCount = countMatchingNumbers(winningNumbers, playedNumbers);

            Map<String, Integer> cardCounts = countCards(linesListTemp);
            String targetCard = extractCardIdentifier(linesList.get(lineIndex));
            int cards = cardCounts.get(targetCard);

            for (int i = 0; i < cards; i++) {
                int nextLine = 1;
                for (int j = matchCount; j > 0; j--) {
                    int tempIndex = lineIndex;
                    insertLine(linesListTemp, lineIndex + nextLine, linesListTemp.get(tempIndex + nextLine));
                    nextLine++;
                }
            }
            grandTotal2 += cards;
            iteration++;
            System.out.println("iteration = " + iteration);
            System.out.println("Current Card = " + linesList.get(lineIndex));
            System.out.println("Amount of cards = " + cards);
            System.out.println("matchCount = " + matchCount);
//            System.out.println("lines = " + linesList);
//            System.out.println("Current cards = " + linesListTemp);
//            cardCounts.forEach((card, count) -> System.out.println(card + ": " + count + " copies"));
            System.out.println("-".repeat(50));

        }
        return grandTotal2;
    }

    private static Map<String, Integer> countCards(List<String> linesList) {
        Map<String, Integer> cardCounts = new HashMap<>();

        for (String line : linesList) {
            String cardIdentifier = extractCardIdentifier(line);
            cardCounts.put(cardIdentifier, cardCounts.getOrDefault(cardIdentifier, 0) + 1);
        }

        return cardCounts;
    }
    private static String extractCardIdentifier(String line) {
        return line.split(":")[0].trim();
    }

    private static void insertLine(List<String> list, int index, String value) {
        if (index >= 0 && index <= list.size()) {
            list.add(list.size(), value);
        } else {

            System.out.println("Invalid index for insertion");
        }
    }

    private static int part1(String[] lines, int grandTotal) {
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
        return grandTotal;
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
