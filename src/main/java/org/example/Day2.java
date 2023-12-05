package org.example;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("src\\main\\java\\org\\example\\inputs\\Day2.txt"));
            System.out.println("lines = " + input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = input.toArray(new String[0]);
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
        System.out.println("totalGames = " + totalGames);
        System.out.println("totalPower = " + totalPower);
    }

}
