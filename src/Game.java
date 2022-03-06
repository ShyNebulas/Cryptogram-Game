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
                        inGamePhrase = enterNumLetter(inGamePhrase);
                        break;

                    case 2:
                       inGamePhrase = undoNumLetter(inGamePhrase,currentCryptogram.cipheredPhrase);
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
            }
            else if (inGamePhrase.contains(ToReplace)){

                System.out.println("please enter your replacement:");

                Scanner secondInput = new Scanner(System.in);
                String replacement = secondInput.nextLine().toUpperCase();
                if (replacement.matches("[A-Za-z]")){
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
                    System.out.println("Invalid input, please enter a valid letter");
                }
            }
            else {
                if (!currentCryptogram.cipheredPhrase.contains(ToReplace)) {
                    System.out.println("This letter either does not exist in the puzzle");
                }
                else {
                    System.out.println("This letter was mapped before Type 1 to overwrite it, or 0 to keep previous mapping");
                    Scanner choice = new Scanner(System.in);
                    try {
                        int selectedChoice = choice.nextInt();
                        if (selectedChoice==0) {
                            return inGamePhrase;
                        }
                        else if (selectedChoice == 1) {
                            System.out.println("please enter your replacement:");

                            Scanner secondInput = new Scanner(System.in);
                            String replacement = secondInput.nextLine().toUpperCase();
                            if (!checkIfPlainLetterMapped(replacement,inGamePhrase,currentCryptogram.cipheredPhrase)){
                                String newToReplace = Character.toString(inGamePhrase.charAt(currentCryptogram.cipheredPhrase.indexOf(ToReplace)));
                                int  replacingIndex =  currentCryptogram.cipheredPhrase.indexOf(ToReplace);
                                while (replacingIndex >=0) {
                                    inGamePhrase = inGamePhrase.substring(0, replacingIndex) + replacement + inGamePhrase.substring(replacingIndex + 1);
                                    replacingIndex = currentCryptogram.cipheredPhrase.indexOf(ToReplace, replacingIndex + 1);
                                }
                                currentPlayer.incrementTotalGuesses();
                                currentPlayer.updateAccuracy();
                                if (checkIfGuessCorrect(newToReplace.charAt(0),currentCryptogram.phrase,inGamePhrase)) {
                                    currentPlayer.incrementTotalCorrectGuesses();
                                    currentPlayer.updateAccuracy();
                                }
                                else if (checkIfCryptogramSolved(currentCryptogram.phrase,inGamePhrase)){
                                    currentPlayer.incrementCryptogramsCompleted();
                                    System.out.println("Congratulations you have finished this Cryptogram!");

                                }
                                return inGamePhrase;
                            }
                            else {
                                System.out.println("This letter has already been mapped before");
                            }
                        }
                        else {
                            throw new Exception("Invalid Input Choice");
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                }
            }
        }
        return inGamePhrase;
    }


    public String enterNumLetter(String inGamePhrase) {
        int exitCode = 0;
        while (exitCode == 0) {
            Scanner firstInput = new Scanner(System.in);

            System.out.println("Type 0 to quit OR");
            System.out.println("Please enter the number to replace:");
                String ToReplace = firstInput.nextLine();
                if (Objects.equals(ToReplace, "0")) {
                    exitCode = 1;
                }
                else if (inGamePhrase.contains(ToReplace)) {
                    System.out.println("please enter your replacement:");
                    Scanner secondInput = new Scanner(System.in);
                    String replacement = secondInput.nextLine().toUpperCase();
                    if (!inGamePhrase.contains(replacement)) {
                        inGamePhrase = inGamePhrase.replace(" " +ToReplace + " ",replacement + " ");
                        return inGamePhrase;
                    } else {
                        System.out.println("Invalid input, or this letter has already been mapped before");
                    }
                }
                else {
                    System.out.println("Invalid input, please enter one letter or a number");
                }
            }
            return inGamePhrase;
    }
    public String undoLetter(String inGamePhrase, String cipheredPhrase) {
        int exitCode = 0;
        Scanner undoInput = new Scanner(System.in);
        while(exitCode ==0) {
            System.out.println("Type 0 to quit OR");
            System.out.println("Please enter the Letter to undo:");

            String ToUndo = undoInput.nextLine().toUpperCase();
            if (Objects.equals(ToUndo, "0")) {
                exitCode = 1;
            }
            else if(inGamePhrase.contains(ToUndo)) {
                char originalLetter = cipheredPhrase.toUpperCase().charAt(inGamePhrase.indexOf(ToUndo));
                    if (originalLetter==ToUndo.charAt(0)){
                        System.out.println("This letter has not been mapped to anything before");
                    }
                    else {
                        int  replacingIndex =  cipheredPhrase.indexOf(originalLetter);
                        while (replacingIndex >=0) {
                            inGamePhrase=inGamePhrase.substring(0,replacingIndex) + originalLetter + inGamePhrase.substring(replacingIndex+1);
                            replacingIndex =cipheredPhrase.indexOf(originalLetter,replacingIndex+1) ;
                        }
                        return inGamePhrase;
                    }
            }
            else {
                System.out.println("Invalid input please try again");
            }
        }
        return inGamePhrase;
    }


    public String undoNumLetter(String inGamePhrase, String cipheredPhrase) {
        int exitCode = 0;
        Scanner undoInput = new Scanner(System.in);
        while (exitCode == 0) {
            System.out.println("Type 0 to quit OR");
            System.out.println("Please enter the letter to undo:");

            String ToUndo = undoInput.nextLine().toUpperCase();
            if (Objects.equals(ToUndo, "0")) {
                exitCode = 1;
            }
            else if (cipheredPhrase.contains(ToUndo)) {
                String originalNum = cipheredPhrase.substring(inGamePhrase.indexOf(ToUndo));
                if (Objects.equals(originalNum,ToUndo)) {
                    System.out.println("This letter has not been mapped to anything before");
                }
                else {
                    inGamePhrase = inGamePhrase.replace(originalNum,ToUndo);
                    return inGamePhrase;
                }
            }
        }
        return inGamePhrase;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
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

    public Boolean checkIfPlainLetterMapped (String replacement, String inGamePhrase, String cipheredPhrase) {
        boolean bool = false;
        for ( int i =0; i<cipheredPhrase.length(); i++) {
            if (inGamePhrase.charAt(i) == cipheredPhrase.charAt(i)){
                bool = replacement.charAt(0) == inGamePhrase.charAt(i);
            }
        }
        return bool;
    }


    public static void main(String[] args) {

        Game testGame = new Game();
         testGame.onStartMenu();

    }
}
