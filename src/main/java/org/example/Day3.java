package org.example;

import java.util.HashMap;
import java.util.Map;

public class Day3 {
    public static void main(String[] args) {
        String input =
                "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...$.*....\n" +
                ".664.598.";
        String[] lines = input.split("\n");
        Map<Integer, Character> positionMapBefore = new HashMap<>();
        Map<Integer, Character> positionMapAfter;
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            char[] characters = line.toCharArray();
            Map<Integer, Character> positionMap = new HashMap<>();
            StringBuilder currentWord = new StringBuilder();

            if (lineIndex < lines.length - 1) {
                char[] nextCharacters = lines[lineIndex + 1].toCharArray();
                positionMapAfter = new HashMap<>();
                for (int i = 0; i < nextCharacters.length; i++) {
                    positionMapAfter.put(i, nextCharacters[i]);
                }
            } else {
                positionMapAfter = new HashMap<>();
            }


            for (int i = 0; i < characters.length; i++) {
                char currentChar = characters[i];
                positionMap.put(i, currentChar);

            }

            for (int i = 0; i < characters.length; i++) {
                char previousChar;
                char nextChar;
                if (i == 0) {
                    previousChar = '.';
                    nextChar = positionMap.get(i + 1);

                } else if (i + 1 < characters.length) {
                    previousChar = positionMap.get(i - 1);
                    nextChar = positionMap.get(i + 1);

                } else {
                    previousChar = positionMap.get(i - 1);
                    nextChar = '.';

                }

                if (previousChar != '.') {
                    if (!Character.isLetterOrDigit(previousChar)){
                        currentWord.append("V");
                    }
                }
                if (nextChar != '.') {
                    if (!Character.isLetterOrDigit(nextChar)){
                        currentWord.append("V");
                    }

                }


            }

            for (char currentChar : characters) {
                if (Character.isDigit(currentChar)) {
                    currentWord.append(currentChar);

                } else if (currentWord.length() > 0) {
                    System.out.println("Word: " + currentWord);
                    currentWord.setLength(0);
                }

            }

            System.out.println("Position Map Before: " + positionMapBefore);
            System.out.println("Position Map After: " + positionMapAfter);
            positionMapBefore.clear();
            positionMapBefore.putAll(positionMap);
            System.out.println("Position Map: " + positionMap);
            System.out.println("-".repeat(50));
        }

    }
}
