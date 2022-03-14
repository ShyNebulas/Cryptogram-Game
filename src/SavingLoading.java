package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SavingLoading {

    /*
    cryptogramToSolve = Jumbled-up solution/cryptogram problem
    cryptogramPlayerProgress = Editable cryptogramToSolve, to match our solution phrase
     */
    static void loadCryptogram(Cryptogram cryptogramToSolve, ArrayList<String> cryptogramPlayerProgress) {

        Scanner scanner = null;

        try {

            scanner = new Scanner(new File("cryptogram.txt"));

        } catch(FileNotFoundException error) {

            System.out.println("[Error] 'savedProgress.txt' file not found");
            System.exit(1);
        }

        String[] lines = scanner.nextLine().split(";");

        cryptogramToSolve.phrase = lines[0];

        // Converts line[1] (cipheredArray) to ArrayList
        for(String character: lines[1].substring(1, lines[1].length() - 1).replaceAll(" ", "").split(",")) {

            cryptogramToSolve.cipheredArray.add(character);

        }

        // Converts line[2] (PlayerProgress) to ArrayList
        for(String character: lines[2].substring(1, lines[2].length() - 1).replaceAll(" ", "").split(",")) {

            cryptogramPlayerProgress.add(character);

        }

        scanner.close();

    }

    /*
    cryptogramToSolve = Jumbled-up solution/cryptogram problem
    cryptogramPlayerProgress = Editable cryptogramToSolve, to match our solution phrase
     */
    static void saveCryptogram(Cryptogram cryptogramToSolve, ArrayList<String> cryptogramPlayerProgress) {

        try {

            File file = new File("cryptogram.txt");
            file.createNewFile();

        } catch(IOException error) {

            System.out.println("[Error] Unable to create 'cryptogram.txt'");
            System.exit(1);

        }

        try {

            FileWriter fileWriter = new FileWriter("cryptogram.txt");

            fileWriter.write(cryptogramToSolve + ";" + cryptogramPlayerProgress);

            fileWriter.close();

        } catch(IOException error) {

            System.out.println("[Error] FileWriter caused an error while writing to 'cryptogram.txt");
            System.exit(1);

        }

    }

    static void savePlayer(Player player) {

        try {

            File file = new File("player.txt");
            file.createNewFile();

        } catch(IOException error) {

            System.out.println("[Error] Unable to create 'player.txt'");
            System.exit(1);

        }

        try {

            FileWriter fileWriter = new FileWriter("player.txt");

            fileWriter.write(player.toString());

            fileWriter.close();

        } catch(IOException error) {

            System.out.println("[Error] FileWriter caused an error while writing to 'player.txt");
            System.exit(1);

        }

    }

    public static void main(String[] args) {

        /*

        Cryptogram test = new LetterCryptogram();
        String solutionPhrase = test.phrase;
        test.cipheredArray = test.generateCipheredArray(solutionPhrase);

        SavingLoading.saveCryptogram(test, test.getPlayingArray(test.cipheredArray));

        Cryptogram test2 = new LetterCryptogram();

        test2.cipheredArray = new ArrayList<>();

        // Must declare arraylist (PlayerProgress) before passing it into load!
        ArrayList<String> blah = new ArrayList<>();

        SavingLoading.loadCryptogram(test2, blah);

        System.out.println(test2.toString());
        System.out.println(blah.toString());

        */

        /*
        Player player = new Player("Cameron", 1, 1, 1, 1, 5);
        SavingLoading.savePlayer(player);

        Player player2 = new Player("Cameron", 0, 0, 0, 0, 0);

        SavingLoading.loadPlayer(player2);

        System.out.println(player2);

         */

    }

}
