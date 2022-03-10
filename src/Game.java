package src;

import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    static Player currentPlayer;
    static Cryptogram currentCryptogram;
    static ArrayList<String> inGameArray;

    public Game() {
    }

    public void onStartMenu() {
        String name;
        Scanner scUsername = new Scanner(System.in);
        System.out.println("Enter your username");
        System.out.println("...................\n");
        name = scUsername.nextLine();
        currentPlayer = new Player(name, 0, 0, 0,0, 0);
        int selection;
        int exit = 0;
        while (exit == 0) {
            Scanner scCryptType = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to play Alphabet Cryptogram");
            System.out.println("2-Press 2 to play Number Cryptogram");
            System.out.println("3-Press 3 to Quit");

            selection = scCryptType.nextInt();

            if (selection <= 3 && selection > 0) {
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

    public void playAlphabetCryptogram() {
        System.out.println("***************************************************************");
        System.out.println("***************** Letter Cryptogram Puzzle ********************");
        System.out.println("***************************************************************");

        currentCryptogram = new LetterCryptogram();
        String solutionPhrase = currentCryptogram.phrase;
        currentCryptogram.cipheredArray = currentCryptogram.generateCipheredArray(solutionPhrase);
        inGameArray = currentCryptogram.getPlayingArray(currentCryptogram.cipheredArray);

        inGameMenu();
    }

    public void playNumberCryptogram() {

        System.out.println("***************************************************************");
        System.out.println("***************** Number Cryptogram Puzzle ********************");
        System.out.println("***************************************************************");

        currentCryptogram =  new NumberCryptogram();
        String solutionPhrase = currentCryptogram.phrase;
        currentCryptogram.cipheredArray = currentCryptogram.generateCipheredArray(solutionPhrase);
        inGameArray = currentCryptogram.getPlayingArray(currentCryptogram.cipheredArray);
        inGameMenu();


    }

    public void inGameMenu() {
        int selection=-1;
        int exit = 0;
        while (exit == 0) {
            System.out.println("Original Puzzle: \n");
            printCipheredArray(currentCryptogram.cipheredArray);
            System.out.println("\n");
            System.out.println("Your Progress:\n");
            printInGameProgress(inGameArray);
            System.out.println("\n");
            Scanner scInGame = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to Enter and replace with a letter");
            System.out.println("2-Press 2 to Undo a replaced letter");
            System.out.println("3-Press 3 to Quit");
            try {
                selection = scInGame.nextInt();
            }
            catch (Exception e) {
                System.out.println("Invalid Input.");
            }

            if (selection > 0 && selection < 4) {
                switch (selection) {
                    case 1:
                        inGameArray= enterLetter(inGameArray);
                        break;

                    case 2:
                        inGameArray = undoLetter(inGameArray,currentCryptogram.cipheredArray);
                        break;

                    case 3:
                        exit = 1;
                    default:
                        break;
                }
            } else {
                System.out.println("Invalid input, please try again");
            }
        }

    }

    public ArrayList<String> enterLetter( ArrayList<String> inGameArray) {
        int exitCode = 1;
        while (exitCode == 1) {
            System.out.println("Please enter the letter to replace or 0 to go back to previous menu");
            Scanner getInput = new Scanner(System.in);
            try {
            }
            catch (Exception e) {
                System.out.println("Please enter a valid input");
            }

        }
        return null;

    }

    public ArrayList<String> undoLetter( ArrayList<String> inGameArray,ArrayList<String> cipheredArray) {
        int exitCode = 1;
        while (exitCode == 1) {
            System.out.println("Please enter the letter to undo or 0 to go back to previous menu");
            Scanner getInput = new Scanner(System.in);
            try {

            }
            catch (Exception e) {
                System.out.println("Please enter a valid input");
            }

        }
        return null;

    }

    public void printCipheredArray(ArrayList<String> cipheredArray) {
        for( String each : cipheredArray) {
            if(Objects.equals(each,"0")){
                System.out.print("  ");
            }
            else if (each.matches("[a-zA-Z]")) {
                System.out.print(each);
            }

            else {
                System.out.print(each + " ");
            }
        }
    }

    public void printInGameProgress(ArrayList<String> inGameArray) {
        for(String each : inGameArray) {
            if(Objects.equals(each,"0")){
                System.out.print("  ");
            }
            else {
                System.out.print(each);
            }
        }
    }

    public boolean checkIfCryptogramSolved (ArrayList<String> inGameArray, String solutionPhrase) {
        boolean toreturn = false;
        for (int i =0; i<inGameArray.size(); i++) {
            if(inGameArray.get(i).matches("[a-zA-Z]") && Character.isLetter(solutionPhrase.charAt(i))) {
                toreturn = Objects.equals(inGameArray.get(i),Character.toString(solutionPhrase.charAt(i)));
                if(!toreturn) {
                    return false;
                }
            }
        }
        return toreturn;
    }



        public static void main(String[] args) {

        Game testGame = new Game();
        ArrayList<String> test = new ArrayList<>();
        test.add("A");
        test.add("0");
        test.add("B");
        test.add("C");
        test.add("D");
        test.add("0");
        test.add("X");
        test.add("Y");
        test.add("Z");
        System.out.println(testGame.checkIfCryptogramSolved(test,"A BCD XYZ"));

        testGame.onStartMenu();
    }
}
