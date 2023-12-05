package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("src\\main\\java\\org\\example\\inputs\\Day1.txt"));
            System.out.println("lines = " + input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = input.toArray(new String[0]);
        int total = 0;

        for (String line : lines) {
            System.out.println("------------------------");
            System.out.println("line = " + line);
            String wordsToNumbers = findNumbers(line);

            String numbersOnly = wordsToNumbers.replaceAll("[^0-9]", "");

            if (!numbersOnly.isEmpty()) {
                char firstDigit = numbersOnly.charAt(0);
                char lastDigit = numbersOnly.charAt(numbersOnly.length() - 1);

                int firstNumber = Character.getNumericValue(firstDigit);
                int lastNumber = Character.getNumericValue(lastDigit);
                total += (firstNumber * 10 + lastNumber);
                int thisOne = (firstNumber * 10 + lastNumber);
                System.out.println("this = " + thisOne);


                System.out.println("current: " + total);
            }
        }
        System.out.println("total = " + total);
    }
    public static String findNumbers(String wordsToNumbers) {

        String[] numberWords = {"nine","seven", "two", "three", "four", "five", "six", "eight", "one"};

        for (String word : numberWords) {
             if (wordsToNumbers.contains(word)){
                 wordsToNumbers = wordsToNumbers.replace(removeFirstAndLastCharacter(word), getNumericValue(removeFirstAndLastCharacter(word)));

             };
        }
        System.out.println("wordsToNumbers = " + wordsToNumbers);

        return wordsToNumbers;
    }
    public static String removeFirstAndLastCharacter(String input) {
        if (input.equals("six") || input.equals("one")) {
            return input;
        }
        if (input.length() > 2) {
            return input.substring(1, input.length() - 1);
        } else {
            return input;
        }
    }
    public static String getNumericValue(String word) {
        switch (word) {
            case "one":
                return "1";
            case "w":
                return "2";
            case "hre":
                return "3";
            case "ou":
                return "4";
            case "iv":
                return "5";
            case "six":
                return "6";
            case "eve":
                return "7";
            case "igh":
                return "8";
            case "in":
                return "9";
            default:
                return word;
        }
    }
}
