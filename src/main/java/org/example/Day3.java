package org.example;

import java.util.HashMap;
import java.util.Map;

public class Day3 {
    public static void main(String[] args) {
        String input =
                "467..114............\n" +
                "...*...........3339.\n" +
                "..35..633...........\n" +
                "......#.............\n" +
                "617*................\n" +
                ".....+.58...........\n" +
                "..592...............\n" +
                "......755...........\n" +
                "...$.*..............\n" +
                ".664.598............";
        String[] lines = input.split("\n");
        Map<Integer, Character> positionMapBefore = new HashMap<>();
        Map<Integer, Character> positionMapAfter = new HashMap<>();
        int total = 0;
        boolean validNumber = false;
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


                validNumber = isValidNumber(validNumber, characters, i, previousChar);
                validNumber = isValidNumber(validNumber, characters, i, nextChar);

                validNumber = isValidNumber(validNumber, characters, i, positionMapBefore.get(i-1));
                validNumber = isValidNumber(validNumber, characters, i, positionMapBefore.get(i));
                validNumber = isValidNumber(validNumber, characters, i, positionMapBefore.get(i+1));

                validNumber = isValidNumber(validNumber, characters, i, positionMapAfter.get(i-1));
                validNumber = isValidNumber(validNumber, characters, i, positionMapAfter.get(i));
                validNumber = isValidNumber(validNumber, characters, i, positionMapAfter.get(i+1));


                if (Character.isDigit(characters[i])) {
                    currentWord.append(characters[i]);

                } else if (currentWord.length() > 0) {
                    if (validNumber){
                        validNumber = false;
                        total += Integer.parseInt(currentWord.toString());
                        currentWord.append("v");
                    }
                    System.out.println("Word: " + currentWord);
                    currentWord.setLength(0);
                }

            }

            System.out.println("Position Map Before: " + positionMapBefore);
            System.out.println("Position Map After: " + positionMapAfter);
            positionMapBefore.clear();
            positionMapBefore.putAll(positionMap);
            System.out.println("Position Map: " + positionMap);
            System.out.println("total = " + total);
            System.out.println("-".repeat(50));
        }

    }

    private static boolean isValidNumber(boolean validNumber, char[] characters, int i, Character nextChar) {
        if (nextChar == null){
            return validNumber;
        }
        if ((characters[i] >= '0' && characters[i] <= '9') && (nextChar != '.') && (nextChar < '0' || nextChar > '9')) {
            validNumber = true;
        }
        return validNumber;
    }
}
