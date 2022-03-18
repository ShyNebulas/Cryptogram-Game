package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SavingLoading {

    /*
    cryptogramToSolve = Jumbled-up solution/cryptogram problem
    cryptogramPlayerProgress = Editable cryptogramToSolve, to match our solution phrase
     */
    static void loadCryptogram(String playerName, Cryptogram cryptogramToSolve, ArrayList<String> cryptogramPlayerProgress, String filename) {

        Scanner scanner = null;
        List<String> lines = new ArrayList<>();

        try {

            scanner = new Scanner(new File(filename));

        } catch (FileNotFoundException error) {

            System.out.println("[Error] 'savedProgress.txt' file not found");
            System.exit(1);
        }

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        int toCheck = 0;

        for (int i = 0; i<lines.size(); i++) {

            String[] line = lines.get(i).split(";");
            if(Objects.equals(line[0], playerName)) {
                cryptogramToSolve.phrase = line[1];

                // Converts line[1] (cipheredArray) to ArrayList
                for (String character : line[2].substring(1, line[2].length() - 1).replaceAll(" ", "").split(",")) {

                    cryptogramToSolve.cipheredArray.add(character);

                }

                // Converts line[2] (PlayerProgress) to ArrayList
                for (String character : line[3].substring(1, line[3].length() - 1).replaceAll(" ", "").split(",")) {

                    cryptogramPlayerProgress.add(character);

                }
                toCheck =1;
            }
        }
        if(toCheck ==0) {
            System.out.println("Username not found, please retry or start a new game");
        }
        scanner.close();
    }

    /*
    cryptogramToSolve = Jumbled-up solution/cryptogram problem
    cryptogramPlayerProgress = Editable cryptogramToSolve, to match our solution phrase
     */
    static void saveCryptogram(String playerName, Cryptogram cryptogramToSolve, ArrayList<String> cryptogramPlayerProgress, String filename) {

        List<String> lines = new ArrayList<>();
        Scanner scanner = null;
        try {
            File file = new File(filename);
            if(file.exists()) {
                try {
                    scanner = new Scanner(new File(filename));

                }
                catch (FileNotFoundException error) {

                    System.out.println("[Error] 'savedProgress.txt' file not found");
                    System.exit(1);
                }
                while (scanner.hasNextLine()) {
                    lines.add(scanner.nextLine());
                }
                int toCheck = 0;

                for (int i = 0; i<lines.size(); i++) {

                    String[] line = lines.get(i).split(";");
                    if(Objects.equals(line[0], playerName)) {
                        try {

                            FileWriter fileWriter = new FileWriter(filename);

                            fileWriter.write(playerName + ";" +cryptogramToSolve + ";" + cryptogramPlayerProgress);

                            fileWriter.close();

                        } catch (IOException error) {

                            System.out.println("[Error] FileWriter caused an error while writing to 'cryptogram.txt");
                            System.exit(1);

                        }

                        toCheck =1;
                    }
                }
                if(toCheck ==0) {
                    System.out.println("Username not found, please retry or start a new game");
                }
                scanner.close();


            }


        } catch (IOException error) {

            System.out.println("[Error] Unable to create 'cryptogram.txt'");
            System.exit(1);

        }

    }

    static void savePlayer(Player player,String filename) {

        try {

            File file = new File(filename);
            file.createNewFile();

        } catch (IOException error) {

            System.out.println("[Error] Unable to create 'player.txt'");
            System.exit(1);

        }

        try {

            FileWriter fileWriter = new FileWriter(filename, true);

            fileWriter.write(player.toString() + "\n");

            fileWriter.close();

        } catch (IOException error) {

            System.out.println("[Error] FileWriter caused an error while writing to 'player.txt");
            System.exit(1);

        }

    }
}
