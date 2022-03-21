package src;

import java.util.*;
import java.lang.*;

public class Game {
    Player currentPlayer = new Player("", 0.0, 0, 0, 0, 0);
    static Cryptogram currentCryptogram;
    static ArrayList<String> inGameArray = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public Game() {
    }

        public void onStartMenu () {
            String name;
            Scanner scUsername = new Scanner(System.in);
            System.out.println("Enter your username");
            System.out.println("...................\n");
            name = scUsername.nextLine().toLowerCase();
                currentPlayer = SavingLoading.loadOldStats(name);
                int selection;
                int exit = 0;
                while (exit == 0) {
                    Scanner scCryptType = new Scanner(System.in);
                    System.out.println("Select your option:");
                    System.out.println("...................\n");
                    System.out.println("1-Press 1 to play Alphabet Cryptogram");
                    System.out.println("2-Press 2 to play Number Cryptogram");
                    System.out.println("3-Press 3 Load a saved cryptogram");
                    System.out.println("4-Press 4 to view your details");
                    System.out.println("5-Press 5 to Quit");

                    selection = scCryptType.nextInt();

                    if (selection <= 5 && selection > 0) {
                        switch (selection) {
                            case 1 -> {
                                currentPlayer.incrementCryptogramsPlayed();
                                playAlphabetCryptogram();
                            }
                            case 2 -> {
                                currentPlayer.incrementCryptogramsPlayed();
                                playNumberCryptogram();
                            }
                            case 3 -> {
                                inGameArray = new ArrayList<>();
                                currentCryptogram = SavingLoading.loadCryptogram(name, inGameArray, "cryptogram.txt");
                                if(currentCryptogram == null) {
                                    System.out.println("No previously saved game found, please start a new game");
                                }
                                else {
                                    inGameMenu();
                                }
                            }
                            case 4 -> {
                                PlayerDetails(currentPlayer);
                            }
                            case 5 -> {
                                SavingLoading.savePlayer(currentPlayer,"player.txt");
                                exit = 1;
                                System.out.println("BYE!");
                            }
                            default -> {
                            }
                        }
                    } else {
                        System.out.println("Invalid Input,please try again.");
                    }
                }

            }

            public void playAlphabetCryptogram () {
                System.out.println("***************************************************************");
                System.out.println("***************** Letter Cryptogram Puzzle ********************");
                System.out.println("***************************************************************");

                currentCryptogram = new LetterCryptogram();
                String solutionPhrase = currentCryptogram.phrase;
                currentCryptogram.cipheredArray = currentCryptogram.generateCipheredArray(solutionPhrase);
                inGameArray = currentCryptogram.getPlayingArray(currentCryptogram.cipheredArray);

                inGameMenu();
            }

            public void playNumberCryptogram () {

                System.out.println("***************************************************************");
                System.out.println("***************** Number Cryptogram Puzzle ********************");
                System.out.println("***************************************************************");

                currentCryptogram = new NumberCryptogram();
                String solutionPhrase = currentCryptogram.phrase;
                currentCryptogram.cipheredArray = currentCryptogram.generateCipheredArray(solutionPhrase);
                inGameArray = currentCryptogram.getPlayingArray(currentCryptogram.cipheredArray);
                inGameMenu();

            }

            public void PlayerDetails (Player currentPlayer){

                System.out.println("Player Details:");
                System.out.println("...................\n");
                System.out.println("Username: " + currentPlayer.getUsername());
                System.out.println("Accuracy: " + currentPlayer.getAccuracy());
                System.out.println("Total Guesses: " + currentPlayer.getTotalGuesses());
                System.out.println("Total Correct Guesses:" + currentPlayer.getTotalCorrectGuesses());
                System.out.println("Number of Crytograms Played: " + currentPlayer.getCryptogramsPlayed());
                System.out.println("Number of Crytograms Completed: " + currentPlayer.getCryptogramsCompleted());

            }

            public void inGameMenu () {
                int selection = -1;
                int exit = 0;
                while (exit == 0) {
                    System.out.println(ANSI_RED + "Original Puzzle: \n" + ANSI_RESET);
                    printCipheredArray(currentCryptogram.cipheredArray);
                    System.out.println("\n");
                    System.out.println(ANSI_YELLOW + "Your Progress:\n" + ANSI_RESET);
                    printInGameProgress(inGameArray);
                    System.out.println("\n");
                    Scanner scInGame = new Scanner(System.in);
                    System.out.println("Select your option:");
                    System.out.println("...................\n");
                    System.out.println("1-Press 1 to Enter and replace with a letter");
                    System.out.println("2-Press 2 to Undo a replaced letter");
                    System.out.println("3-Press 3 to save your progress");
                    System.out.println("4-Press 4 to go to the previous menu");
                    try {
                        selection = scInGame.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid Input.");
                    }
                    if (selection > 0 && selection < 5) {
                        switch (selection) {
                            case 1:
                                getInputForEnterLetter();
                                break;

                            case 2:
                                getInputForUndoLetter();
                                break;

                            case 3:
                                SavingLoading.savePlayer(currentPlayer, "player.txt");
                                SavingLoading.saveCryptogram(currentPlayer.getUsername(),currentCryptogram, inGameArray, "cryptogram.txt");
                                break;
                            case 4:
                                exit = 1;
                                break;
                            default:
                                break;
                        }
                    } else {
                        System.out.println("Invalid input, please try again");
                    }
                }

            }

            public void getInputForEnterLetter () {
                int exitCode = 1;
                while (exitCode == 1) {
                    System.out.println(ANSI_CYAN + "Please enter the value to replace or 0 to go back to previous menu" + ANSI_RESET);
                    Scanner getInput = new Scanner(System.in);
                    try {
                        String toReplace = getInput.nextLine().toUpperCase();
                        if (currentCryptogram.cipheredArray.contains(toReplace)) {
                            for (int i = 0; i < inGameArray.size(); i++) {
                                if (Objects.equals(currentCryptogram.cipheredArray.get(i), toReplace)) {
                                    if (!Objects.equals(inGameArray.get(i), "_")) {
                                        System.out.println("This cryptogram value is mapped to something before, type 0 to overwrite or 1 to keep original");
                                        Scanner getThirdInput = new Scanner(System.in);
                                        String option = getThirdInput.nextLine();
                                        if (Objects.equals(option, "0")) {
                                            System.out.println(ANSI_BLUE + "Please enter the replacement letter or 0 to go back to previous menu" + ANSI_RESET);
                                            Scanner getSecondInput = new Scanner(System.in);
                                            String replacement = getSecondInput.nextLine().toUpperCase();
                                            if (replacement.matches("[a-zA-Z]")) {
                                                if (inGameArray.contains(replacement)) {
                                                    System.out.println("this letter is used. either undo mapping or pick a different letter");
                                                    break;
                                                } else {
                                                    inGameArray = enterLetter(toReplace, replacement);
                                                    currentPlayer.incrementTotalGuesses();
                                                    checkIfGuessCorrect(replacement, inGameArray, currentCryptogram.phrase);
                                                    currentPlayer.updateAccuracy();
                                                    if (checkIfCryptogramSolved(inGameArray, currentCryptogram.phrase)) {
                                                        currentPlayer.incrementCryptogramsCompleted();
                                                        SavingLoading.savePlayer(currentPlayer,"player.txt");
                                                        System.out.println("You have finished this cryptogram successfully Congratulations!");
                                                        exitCode = 0;
                                                        onStartMenu();
                                                        break;

                                                    } else if ((!inGameArray.contains("_")) && (!checkIfCryptogramSolved(inGameArray, currentCryptogram.phrase))) {
                                                        System.out.println("Your solution is incorrect, you have unsuccessfully finished cryptogram");
                                                        exitCode = 0;
                                                        onStartMenu();
                                                        break;
                                                    }
                                                    exitCode = 0;
                                                    break;
                                                }

                                                // if inGameArray.contains (replacement)
                                                // display error that this letter is used. either undo mapping or pick a different letter

                                            } else if (Objects.equals(replacement, "0")) {
                                                exitCode = 0;
                                                break;
                                            } else {
                                                System.out.println("Please enter a valid alphabet letter.");
                                            }

                                        } else if (Objects.equals(option, "1")) {
                                            exitCode = 0;
                                            break;
                                        } else {
                                            System.out.println("please pick a valid option");
                                        }
                                    } else {
                                        System.out.println("Please enter the replacement letter or 0 to go back to previous menu");
                                        Scanner getSecondInput = new Scanner(System.in);
                                        String replacement = getSecondInput.nextLine().toUpperCase();
                                        if (replacement.matches("[a-zA-Z]")) {
                                            if (inGameArray.contains(replacement)) {
                                                System.out.println("this letter is used. either undo mapping or pick a different letter");
                                                exitCode = 0;
                                                break;
                                            } else {
                                                inGameArray = enterLetter(toReplace, replacement);
                                                currentPlayer.incrementTotalGuesses();
                                                checkIfGuessCorrect(replacement, inGameArray, currentCryptogram.phrase);
                                                currentPlayer.updateAccuracy();
                                                if (checkIfCryptogramSolved(inGameArray, currentCryptogram.phrase)) {
                                                    currentPlayer.incrementCryptogramsCompleted();
                                                    SavingLoading.savePlayer(currentPlayer,"player.txt");
                                                    System.out.println("You have finished this cryptogram successfully Congratulations!");
                                                    exitCode = 0;
                                                    onStartMenu();
                                                    break;
                                                } else if ((!inGameArray.contains("_")) && (!checkIfCryptogramSolved(inGameArray, currentCryptogram.phrase))) {
                                                    System.out.println("Your solution is incorrect, you have unsuccessfully finished cryptogram");
                                                    exitCode = 0;
                                                    inGameMenu();
                                                    break;
                                                }
                                                exitCode = 0;
                                                break;
                                            }
                                        } else {
                                            System.out.println("Please enter a valid letter from the alphabet A to Z");
                                        }
                                    }
                                }
                            }
                        } else if (Objects.equals(toReplace, "0")) {
                            exitCode = 0;
                            break;
                        } else {
                            System.out.println("The puzzle does not have this value");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                    }
                }
            }

            public ArrayList<String> enterLetter (String toReplace, String replacement){
                for (int i = 0; i < inGameArray.size(); i++) {
                    if (Objects.equals(currentCryptogram.cipheredArray.get(i), toReplace.toUpperCase())) {
                        inGameArray.set(i, replacement.toUpperCase());
                    }
                }
                return inGameArray;
            }

            public void getInputForUndoLetter () {
                int exitCode = 1;
                while (exitCode == 1) {
                    System.out.println("Please enter the Cryptogram value to undo");
                    Scanner getInput = new Scanner(System.in);
                    String Undo = getInput.nextLine().toUpperCase();

                    if (Objects.equals(Undo, "0")) {
                        exitCode = 0;
                        break;

                    } else if (currentCryptogram.cipheredArray.contains(Undo)) {
                        for (int i = 0; i < currentCryptogram.cipheredArray.size(); i++) {
                            if (Objects.equals(currentCryptogram.cipheredArray.get(i), Undo)) {
                                if (inGameArray.get(i).matches("[a-zA-Z]")) {
                                    undoLetter(Undo, inGameArray);
                                    exitCode = 0;
                                    break;
                                } else {
                                    System.out.println("This value has not been mapped before cannot undo");
                                    exitCode = 0;
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid Input,This value is not in the puzzle");
                        exitCode = 0;
                        break;
                    }
                }
            }

            public ArrayList<String> undoLetter (String undo, ArrayList < String > inGameArray){
                for (int i = 0; i < currentCryptogram.cipheredArray.size(); i++) {
                    if (Objects.equals(currentCryptogram.cipheredArray.get(i), undo)) {
                        if (inGameArray.get(i).matches("[a-zA-Z]")) {
                            inGameArray.set(i, "_");
                        }

                    }
                }
                return inGameArray;
            }



    public void printCipheredArray (ArrayList < String > cipheredArray) {
                for (String each : cipheredArray) {
                    if (Objects.equals(each, "-1")) {
                        System.out.print("  ");
                    } else if (each.matches("[a-zA-Z]")) {
                        System.out.print(ANSI_BLUE + each + ANSI_RESET);
                    } else {
                        System.out.print(each + " ");
                    }
                }
            }

            public void printInGameProgress (ArrayList < String > inGameArray) {
                for (String each : inGameArray) {
                    if (Objects.equals(each, "-1")) {
                        System.out.print("  ");
                    } else {
                        System.out.print(ANSI_YELLOW + each + ANSI_RESET);
                    }
                }
            }

            public boolean checkIfGuessCorrect (String replacement, ArrayList < String > inGameArray, String
            solutionPhrase){
                boolean checkBool = false;
                for (int i = 0; i < inGameArray.size(); i++) {
                    if (Objects.equals(inGameArray.get(i).toUpperCase(), replacement.toUpperCase())) {
                        checkBool = Objects.equals(inGameArray.get(i).toUpperCase(), Character.toString(solutionPhrase.charAt(i)).toUpperCase());
                        break;
                    }
                }
                if (checkBool) {
                    currentPlayer.incrementTotalCorrectGuesses();
                    currentPlayer.updateAccuracy();
                }
                return checkBool;
            }

            public boolean checkIfCryptogramSolved (ArrayList < String > inGameArray, String solutionPhrase){
                String test = String.join(",", inGameArray).replaceAll(",", "").replaceAll("-1", " ");
                return Objects.equals(test.toUpperCase(), solutionPhrase.toUpperCase());
            }

            public static void main (String[]args){
                Game testGame = new Game();
                testGame.onStartMenu();
            }
        }

