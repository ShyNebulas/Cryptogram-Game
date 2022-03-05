package src;

import java.util.*;
import java.lang.*;

public class Game {
    static Player currentPlayer;
    static Cryptogram currentCryptogram;
    static String inGamePhrase;

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
        String cipheredPhrase = currentCryptogram.generateCipheredPhrase(solutionPhrase);
        currentCryptogram.cipheredPhrase = cipheredPhrase;
        inGamePhrase = cipheredPhrase;


        int selection;
        int exit = 0;
        while (exit == 0) {
            System.out.println("Original Puzzle:");
            System.out.println(cipheredPhrase);
            System.out.println("Your Progress:");
            System.out.println(inGamePhrase);
            Scanner scInGame = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to Enter and replace a letter");
            System.out.println("2-Press 2 to Undo a replaced letter");
            System.out.println("3-Press 3 to Quit");
            try {
                selection = scInGame.nextInt();

                if (selection > 0 && selection < 4) {
                    switch (selection) {
                        case 1 -> inGamePhrase = enterLetter(inGamePhrase);
                        case 2 -> inGamePhrase = undoLetter(inGamePhrase, currentCryptogram.cipheredPhrase);
                        case 3 -> exit = 1;
                        default -> {
                        }
                    }
                } else {
                    System.out.println("Invalid input, please try again");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void playNumberCryptogram() {


        System.out.println("***************************************************************");
        System.out.println("***************** Number Cryptogram Puzzle ********************");
        System.out.println("***************************************************************");

        NumberCryptogram newNumberGame = new NumberCryptogram();
        String solutionPhrase = newNumberGame.phrase;
        String cipheredPhrase = newNumberGame.generateCipheredPhrase(solutionPhrase);
        currentCryptogram = newNumberGame;
        newNumberGame.cipheredPhrase = cipheredPhrase;
        inGamePhrase = cipheredPhrase;

        int selection;
        int exit = 0;
        while (exit == 0) {
            System.out.println("Original Puzzle:");
            System.out.println(cipheredPhrase);
            System.out.println("Your Progress:");
            System.out.println(inGamePhrase);
            Scanner scInGame = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to Enter and replace a number with a letter");
            System.out.println("2-Press 2 to Undo a replaced letter");
            System.out.println("3-Press 3 to Quit");

            selection = scInGame.nextInt();
            if (selection > 0 && selection < 4) {
                switch (selection) {
                    case 1:
                        inGamePhrase = enterLetter(inGamePhrase);
                        break;

                    case 2:
                       inGamePhrase = undoLetter(inGamePhrase,currentCryptogram.cipheredPhrase);
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

    public String enterLetter(String inGamePhrase) {
        int exitCode = 0;
        while (exitCode == 0) {
            Scanner firstInput = new Scanner(System.in);

            System.out.println("Type 0 to quit OR");
            System.out.println("Please enter the Letter/number to replace:");
            String ToReplace = firstInput.nextLine().toUpperCase();
            if (Objects.equals(ToReplace, "0")) {
                exitCode = 1;
            } else if (ToReplace.matches("[A-Za-z]") || ((Integer.parseInt(ToReplace) >= 1)|| (Integer.parseInt(ToReplace)) <=26)){
                System.out.println("please enter your replacement:");
                Scanner secondInput = new Scanner(System.in);
                String replacement = secondInput.nextLine().toUpperCase();
                if (replacement.matches("[A-Za-z]") || ((Integer.parseInt(replacement)>=1) || (Integer.parseInt(replacement)) <=26)){
                    inGamePhrase = inGamePhrase.replace(ToReplace, replacement);
                    currentPlayer.incrementTotalGuesses();
                    currentPlayer.updateAccuracy();
                    if (checkIfGuessCorrect(ToReplace.charAt(0),currentCryptogram.phrase,inGamePhrase)) {
                        currentPlayer.incrementTotalCorrectGuesses();
                        currentPlayer.updateAccuracy();
                    }
                    else if (checkIfCryptogramSolved(currentCryptogram.phrase,inGamePhrase)){
                        currentPlayer.incrementCryptogramsCompleted();
                        System.out.println("Congratulations you have finished this Cryptogram!");

                    }
                    return inGamePhrase;
                } else {
                    System.out.println("Invalid input, please enter one letter or a number");
                }

            } else {
                System.out.println("Invalid input, one letter/number at a time ");
            }
        }
        return inGamePhrase;
    }

    public String undoLetter(String inGamePhrase, String cipheredPhrase) {
        int exitCode = 0;
        Scanner undoInput = new Scanner(System.in);
        while(exitCode ==0) {
            System.out.println("Type 0 to quit OR");
            System.out.println("Please enter the Letter/number to undo:"); //use replacement instead of to replace
            //replace original puzzle letter rather than user entered letter

            String ToUndo = undoInput.nextLine().toUpperCase();
            if (Objects.equals(ToUndo, "0")) {
                exitCode = 1;
            }
            else if(ToUndo.matches("[A-Za-z]") || ((Integer.parseInt(ToUndo) >= 1)|| (Integer.parseInt(ToUndo)) <=26)) {
                char originalLetter = cipheredPhrase.toUpperCase().charAt(inGamePhrase.indexOf(ToUndo));
                    if (originalLetter==ToUndo.charAt(0)){
                        System.out.println("This letter/number has not been mapped before");
                    }
                    else {
                        inGamePhrase=inGamePhrase.replace(ToUndo.charAt(0),originalLetter);
                        return inGamePhrase;
                    }
            }
            else {
                System.out.println("Invalid input please try again");
            }
        }
        return inGamePhrase;
    }
    //doesn't work with numbers.

    public Boolean checkIfCryptogramSolved(String solutionPhrase,String usersAnswer) {
        return solutionPhrase.equalsIgnoreCase(usersAnswer);
    }

    public Boolean checkIfGuessCorrect(char replacement, String solutionPhrase, String usersAnswer) {
        char [] solArray = solutionPhrase.toCharArray();
        char [] ansArray = usersAnswer.toCharArray();
        boolean bool = false;
        for (int j =0 ; j< ansArray.length; j++) {
            if (replacement == ansArray[j]) {
                bool= solArray[j] == ansArray[j];
            }
        }
       return bool;
    }
    //doesn't work with numbers.

    public static void main(String[] args) {

        Game testGame = new Game();
        testGame.onStartMenu();
    }
}
