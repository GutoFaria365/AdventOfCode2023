package org.example;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day2 {
    public static void main(String[] args) {
        String input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        String[] lines = input.split("\n");
        int totalGames = 0;

        for (String line : lines) {
            int gameNumber = -1;
            int maxRed = 12;
            int maxGreen = 13;
            int maxBlue = 14;
            String[] gameParts = line.split(":");
            if (gameParts.length > 0) {
                try {
                    gameNumber = Integer.parseInt(gameParts[0].replaceAll("[^0-9]", ""));
                    System.out.println("gameNumber = " + gameNumber);
                } catch (NumberFormatException ignored) {
                }
            }
            String[] words = line.split("[,;:]");
            System.out.println("words = " + Arrays.toString(words));

            for (String word : words) {
                try {
                    String[] parts = word.trim().split("\\s+");
                    int number = Integer.parseInt(parts[0]);
                    String color = parts[1];
                    if (number > maxRed && "red".equals(color)) {
                        gameNumber = 0;
                        System.out.println("Line contains a number higher than " + maxRed + " red");
                        break;
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
                }
            }
            for (String word : words) {
                try {
                    String[] parts = word.trim().split("\\s+");
                    int number = Integer.parseInt(parts[0]);
                    String color = parts[1];
                    if (number > maxGreen && "green".equals(color)) {
                        gameNumber = 0;
                        System.out.println("Line contains a number higher than " + maxGreen + " green");
                        break;
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
                }
            }
            for (String word : words) {
                try {
                    String[] parts = word.trim().split("\\s+");
                    int number = Integer.parseInt(parts[0]);
                    String color = parts[1];
                    if (number > maxBlue && "blue".equals(color)) {
                        gameNumber = 0;
                        System.out.println("Line contains a number higher than " + maxBlue + " blue");
                        break;
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
                }
            }


            totalGames += gameNumber;
            System.out.println("totalGames = " + totalGames);
            System.out.println("-".repeat(50));
        }

        //part2
        int totalPower = 0;
        for (String line : lines) {
            Map<String, Integer> maxNumbersByColor = new HashMap<>();
            int linePower = 0;
            String[] words = line.split("[,;:]");
            System.out.println("words = " + Arrays.toString(words));

            for (String word : words) {
                try {
                    String[] parts = word.trim().split("\\s+");
                    int number = Integer.parseInt(parts[0]);
                    String color = parts[1];

                    int currentMax = maxNumbersByColor.getOrDefault(color, Integer.MIN_VALUE);
                    if (number > currentMax) {
                        maxNumbersByColor.put(color, number);
                    }

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
                }
            }

            linePower = maxNumbersByColor.getOrDefault("red", 0);
            System.out.println("linePower = " + linePower);
            linePower = linePower * maxNumbersByColor.getOrDefault("green", 0);
            System.out.println("linePower = " + linePower);
            linePower = linePower * maxNumbersByColor.getOrDefault("blue", 0);
            System.out.println("linePower = " + linePower);

            totalPower += linePower;
            System.out.println("Maximum numbers by color: " + maxNumbersByColor);
            System.out.println("totalPower = " + totalPower);
            System.out.println("-".repeat(50));
        }

    }

}
