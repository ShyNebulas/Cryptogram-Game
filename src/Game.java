package src;

import java.util.HashMap;
import java.util.Scanner;
import java.lang.*;

import static java.lang.Character.isLetter;


public class Game {
    HashMap <Player,String> playerGameMapping = new HashMap<>();
    Player currentPlayer;
    Cryptogram currentCryptogram;

    public Game(Player p){
        this.currentPlayer = p;

    }

    public void onStartMenu() {
        String name;
        Scanner scUsername = new Scanner(System.in);
        System.out.println("Enter your username");
        System.out.println("...................\n");
        name = scUsername.nextLine();
        Player newPlayer = new Player(name, 0, 0, 0, 0);
        this.currentPlayer = newPlayer;
        int selection;
        int exit = 0;
        while (exit==0) {
            Scanner scCryptType = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to play Alphabet Cryptogram");
            System.out.println("2-Press 2 to play Number Cryptogram");
            System.out.println("3-Press 3 to Quit");

            selection = scCryptType.nextInt();

            if (selection <= 3 && selection > 0) {
                switch (selection) {
                    case 1:
                        playAlphabetCryptogram();
                        break;
                    case 2:
                        playNumberCryptogram();
                        break;
                    case 3:
                        exit = 1;
                        System.exit(1);
                    default:
                        break;
                }
            }
            else {
                System.out.println("Invalid Input,please try again.");
            }
        }
    }

    public void playAlphabetCryptogram() {
        System.out.println("***************************************************************");
        System.out.println("***************** Letter Cryptogram Puzzle *************************");
        System.out.println("***************************************************************");

        LetterCryptogram newLetterGame = new LetterCryptogram();
        String solutionPhrase = newLetterGame.phrase;
        String cipheredPhrase = newLetterGame.generateCipheredPhrase(solutionPhrase);
        currentCryptogram = newLetterGame;
        System.out.println(cipheredPhrase);

        int selection = 0;
        int exit = 0;
        while (exit ==0) {
            Scanner scInGame = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to Enter and replace a letter");
            System.out.println("2-Press 2 to Undo a replaced letter");
            System.out.println("3-Press 3 to Quit");

            selection=scInGame.nextInt();
            if (selection >0 && selection <4) {
                switch (selection) {
                    case 1 :
                       cipheredPhrase = enterLetter(cipheredPhrase);
                        break;

                    case 2:
                        cipheredPhrase= undoLetter(cipheredPhrase);
                        break;
                    case 3:
                        exit = 1;
                        System.exit(1);
                    default:
                        break;
                }
            }
            else {
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public void playNumberCryptogram() {

        System.out.println("***************************************************************");
        System.out.println("***************** Number Cryptogram Puzzle *************************");
        System.out.println("***************************************************************");

        NumberCryptogram newNumberGame = new NumberCryptogram();
        String solutionPhrase = newNumberGame.phrase;
        String cipheredPhrase = newNumberGame.generateCipheredPhrase(solutionPhrase);
        currentCryptogram = newNumberGame;

        int selection = 0;
        int exit = 0;
        while (exit ==0) {

            System.out.println(cipheredPhrase);

            Scanner scInGame = new Scanner(System.in);
            System.out.println("Select your option:");
            System.out.println("...................\n");
            System.out.println("1-Press 1 to Enter and replace a number with a letter");
            System.out.println("2-Press 2 to Undo a replaced letter");
            System.out.println("3-Press 3 to Quit");

            selection=scInGame.nextInt();
            if (selection >0 && selection <4) {
                switch (selection) {
                    case 1 :
                        // implement enter letter
                        break;

                    case 2:
                        // implement undoLetter;
                        break;

                    case 3:
                        exit = 1;
                        System.exit(1);
                    default:
                        break;
                }
            }
            else {
                System.out.println("Invalid input, please try again");
            }
        }
    }

        public String enterLetter(String cipheredPhrase) {
        // we need to create a map : that stores the phrase letters mapped to it's ciphered letter/numbers.
            // you can get the actual solution phrase from this.currentcryptogram.phrase
         // so if phrase is BATMAN encryption is ZXUKXL
            // our map will look like {B -> Z, A -> X, T -> U ...} and so on
            // so when user selects entering letter as an option
            // this function must ask for letter to replace and letter to replace with
            // update every instance of that letter in the map with the replacement.
            // if they guessed it correct, the Key and Value will be the same.
            // check if guessing correct, if guess correct increment player.numCorrectGuesses
            // otherwise increment number of guesses.
            // similar for the number cryptogram. consider each number a string, instead of 1 consider it "1"
            // return a string, updated string. with the replaced letter.
            // make sure to check user input, if it is anything but a single letter/number alert "invalid user input
            // have a similar implementation for undo letter.


            Scanner sc = new Scanner(System.in);
            do {
                Scanner guessInput = new Scanner(System.in);
                System.out.println("Please enter the Letter to replace:");
                String letter = guessInput.nextLine();

                int letterIndex=  GamePhrase.indexOf(letter);
                //  System.out.println("The Letter index is: "+letterIndex);

                if (letter.length() > 1 ) {
                    System.out.println("one letter at a time ");
                    enterLetter();
                }
                System.out.println("please enter your replacement:");
                String guessLetter = guessInput.nextLine();
                if (guessLetter.length() > 1) {
                    System.out.println("Only allowed one Guess at a time please");
                    enterLetter();
                }

                if(isOnlyLetters(guessLetter)) {
                    char guessChar = guessLetter.charAt(0);
                    char[] guessLetterArray = guessLetter.toCharArray();



                    //if (!contains(str, guessChar)) {
                    // str = String.valueOf(guessLetterArray);

                    newPhrase = GamePhrase.replace(letter, guessLetter);


                    //char[] guessArray = newPhrase.toCharArray();
                    //System.out.println("you this guess this before")

                }

            }while (GamePhrase != Solution);
            return null;

        }

    private static boolean isOnlyLetters(String s){
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;

    }

    public static boolean contains(String str, char chr ) {

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == chr)
                return true;
        return false;
    }



    public static void main(String[] args) {
        LetterCryptogram testAplhabetGame = new LetterCryptogram();
        NumberCryptogram testNumberGame = new NumberCryptogram();
        System.out.println(testAplhabetGame.phrase);
        System.out.println(testAplhabetGame.generateCipheredPhrase(testAplhabetGame.phrase));
        System.out.println(testNumberGame.phrase);
        System.out.println(testNumberGame.generateCipheredPhrase(testNumberGame.phrase));

    }

}
