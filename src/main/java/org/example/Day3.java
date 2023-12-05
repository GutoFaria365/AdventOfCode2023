package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("src\\main\\java\\org\\example\\inputs\\Day3.txt"));
            System.out.println("lines = " + input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = input.toArray(new String[0]);
        Map<Integer, Character> positionMapBefore = new HashMap<>();
        Map<Integer, Character> positionMapAfter = new HashMap<>();

        int total = 0;
        boolean validNumber = false;

        int gearRatio = 0;
        boolean validGear = false;
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            char[] characters = (line + ".").toCharArray();
            Map<Integer, Character> positionMap = new HashMap<>();
            StringBuilder currentWord = new StringBuilder();
            StringBuilder currentGear = new StringBuilder();

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

                int beforeIndex = i - 1;
                int afterIndex = i + 1;

                if (i == 0) {
                    beforeIndex = -1;
                } else if (i == characters.length - 1) {
                    afterIndex = -1;
                }

                validNumber = isValidNumber(validNumber, characters, i, positionMapBefore.get(beforeIndex));
                validNumber = isValidNumber(validNumber, characters, i, positionMapBefore.get(i));
                validNumber = isValidNumber(validNumber, characters, i, positionMapBefore.get(afterIndex));

                validNumber = isValidNumber(validNumber, characters, i, positionMapAfter.get(beforeIndex));
                validNumber = isValidNumber(validNumber, characters, i, positionMapAfter.get(i));
                validNumber = isValidNumber(validNumber, characters, i, positionMapAfter.get(afterIndex));

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

                //PART 2
//                if (characters[i] == '*') {
//                    int tempGear = 0;
//
//                    if (Character.isDigit(positionMap.get(i+1))){
//                        currentGear.append(positionMap.get(i+1));
//                        if (Character.isDigit(positionMap.get(i+2))) {
//                            currentGear.append(positionMap.get(i+2));
//                            if (Character.isDigit(positionMap.get(i+3))) {
//                                currentGear.append(positionMap.get(i+3));
//                            }
//                        }
//                        System.out.println("Gear: " + currentGear);
//                    }
//                    if (Character.isDigit(positionMap.get(i-1))){
//                       if (currentGear.length() > 0){
//                           tempGear = Integer.parseInt(currentGear.toString());
//                           currentGear.setLength(0);
//                       }
//                        if (Character.isDigit(positionMap.get(i-2))) {
//                            if (Character.isDigit(positionMap.get(i-3))) {
//                                currentGear.append(positionMap.get(i-3));
//                            }
//                            currentGear.append(positionMap.get(i-2));
//                        }
//                        currentGear.append(positionMap.get(i-1));
//                        System.out.println("Gear: " + currentGear);
//                    }
//
//                    if (Character.isDigit(positionMapBefore.get(i-1))){
//                        if (currentGear.length() > 0){
//                            tempGear = Integer.parseInt(currentGear.toString());
//                            currentGear.setLength(0);
//                        }
//
//                        if (Character.isDigit(positionMapBefore.get(i-2))) {
//                            if (Character.isDigit(positionMapBefore.get(i))) {
//                                currentGear.append(positionMapBefore.get(i-2));
//                                currentGear.append(positionMapBefore.get(i-1));
//                                currentGear.append(positionMapBefore.get(i));
//
//                            } else if (Character.isDigit(positionMapBefore.get(i-3))) {
//                                currentGear.append(positionMapBefore.get(i-3));
//                                currentGear.append(positionMapBefore.get(i-2));
//                                currentGear.append(positionMapBefore.get(i-1));
//                            }
//
//                        }
//
//                        System.out.println("Gear: " + currentGear);
//                    } else if (Character.isDigit(positionMapBefore.get(i))){
//                        if (currentGear.length() > 0){
//                            tempGear = Integer.parseInt(currentGear.toString());
//                            currentGear.setLength(0);
//                        }
//
//                        if (Character.isDigit(positionMapBefore.get(i-1))) {
//                            if (Character.isDigit(positionMapBefore.get(i-2))) {
//                                currentGear.append(positionMapBefore.get(i-2));
//                                currentGear.append(positionMapBefore.get(i-1));
//                                currentGear.append(positionMapBefore.get(i));
//                            } else if (Character.isDigit(positionMapBefore.get(i+1))) {
//                                currentGear.append(positionMapBefore.get(i-1));
//                                currentGear.append(positionMapBefore.get(i));
//                                currentGear.append(positionMapBefore.get(i+1));
//                            }
//                        } else if (Character.isDigit(positionMapBefore.get(i+1))) {
//                            currentGear.append(positionMapBefore.get(i));
//                            currentGear.append(positionMapBefore.get(i+1));
//                            if (Character.isDigit(positionMapBefore.get(i+2))) {
//                                currentGear.append(positionMapBefore.get(i+2));
//                            }
//                        }
//
//                        System.out.println("Gear: " + currentGear);
//                    } else if (Character.isDigit(positionMapBefore.get(i+1))){
//                        currentGear.append(positionMapBefore.get(i+1));
//
//                        if (Character.isDigit(positionMapBefore.get(i+2))) {
//                            if (Character.isDigit(positionMapBefore.get(i))) {
//                                currentGear.append(positionMapBefore.get(i-2));
//                                currentGear.append(positionMapBefore.get(i-1));
//                                currentGear.append(positionMapBefore.get(i));
//
//                            } else if (Character.isDigit(positionMapBefore.get(i+3))) {
//                                currentGear.append(positionMapBefore.get(i+1));
//                                currentGear.append(positionMapBefore.get(i+2));
//                                currentGear.append(positionMapBefore.get(i+3));
//                            }
//
//                        }
//                        System.out.println("Gear: " + currentGear);
//                    }
//
//
//                    if (Character.isDigit(positionMapAfter.get(beforeIndex))){
//                        currentGear.append(positionMapAfter.get(beforeIndex));
//                        System.out.println("Gear: " + currentGear);
//                    }
//                    if (Character.isDigit(positionMapAfter.get(i))){
//                        currentGear.append(positionMapAfter.get(i));
//                        System.out.println("Gear: " + currentGear);
//                    }
//                    if (Character.isDigit(positionMapAfter.get(afterIndex))){
//                        currentGear.append(positionMapAfter.get(afterIndex));
//                        System.out.println("Gear: " + currentGear);
//                    }
////                    currentWord.append(characters[i]);
//                }
//                } else if (currentWord.length() > 0) {
//                    if (validNumber){
//                        validNumber = false;
//                        total += Integer.parseInt(currentWord.toString());
//                        currentWord.append("v");
//                    }
//                    System.out.println("Word: " + currentWord);
//                    currentWord.setLength(0);
//                }


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

        if ((characters[i] >= '0' && characters[i] <= '9') && (nextChar != '.') && (!Character.isDigit(nextChar))) {
            validNumber = true;
        }
        return validNumber;
    }

}
