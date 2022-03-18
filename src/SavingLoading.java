package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        try {

            scanner = new Scanner(new File(filename));

        } catch(FileNotFoundException error) {

            System.out.println("[Error]" + "'" + filename + "'" + "file not found");
            System.exit(1);

        }

        boolean flag = false;

        while(scanner.hasNextLine()) {

            String[] splitLine = scanner.nextLine().split(";");

            if(splitLine[1].equals(playerName)) {

                flag = true;

                if(splitLine.equals("L")) {

                    cryptogramToSolve = new LetterCryptogram();

                } else {

                    cryptogramToSolve = new NumberCryptogram();

                }

                cryptogramToSolve.phrase = splitLine[1];

                // Converts line[1] (cipheredArray) to ArrayList
                for(String character: splitLine[1].substring(1, splitLine[1].length() - 1).replaceAll(" ", "").split(",")) {

                    cryptogramToSolve.cipheredArray.add(character);

                }

                // Converts line[2] (PlayerProgress) to ArrayList
                for(String character: splitLine[2].substring(1, splitLine[2].length() - 1).replaceAll(" ", "").split(",")) {

                    cryptogramPlayerProgress.add(character);

                }

                break;

            }

        }

        if(!flag) {

            System.out.println(playerName + " not found");

        }

        scanner.close();

    }

    /*
    cryptogramToSolve = Jumbled-up solution/cryptogram problem
    cryptogramPlayerProgress = Editable cryptogramToSolve, to match our solution phrase
     */
    static void saveCryptogram(String playerName, Cryptogram cryptogramToSolve, ArrayList<String> cryptogramPlayerProgress, String filename) {

        try {

            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            int lineNumber = 0;
            boolean flag = false;

            for(String line: lines) {

                String[] lineSplit = line.split(";");

                if(lineSplit[0].equals(playerName)) {

                    if(cryptogramToSolve instanceof LetterCryptogram) {

                        lines.set(lineNumber, "L;" + playerName + ";" + cryptogramToSolve + ";" + cryptogramPlayerProgress);

                    } else {

                        lines.set(lineNumber, "N;" + playerName + ";" + cryptogramToSolve + ";" + cryptogramPlayerProgress);

                    }

                    flag = true;
                    break;

                }

                lineNumber++;

            }

            if(!flag) {

                lines.add(playerName + ";" +cryptogramToSolve + ";" + cryptogramPlayerProgress);

            }

            Files.write(path, lines, StandardCharsets.UTF_8);

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
