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
    public static Cryptogram loadCryptogram(String playerName,  ArrayList<String> cryptogramPlayerProgress, String filename) {
        Cryptogram cryptogramToSolve;
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

            if(splitLine.length !=5) {
                System.out.println("File is corrupt cannot read saved game");
            }

            if(splitLine[1].equals(playerName)) {

                flag = true;

                if(splitLine[0].equals("L")) {

                    cryptogramToSolve = new LetterCryptogram();
                    cryptogramToSolve.cipheredArray= new ArrayList<>();

                } else {

                    cryptogramToSolve = new NumberCryptogram();
                    cryptogramToSolve.cipheredArray= new ArrayList<>();

                }

                cryptogramToSolve.phrase = splitLine[2];

                // Converts line[1] (cipheredArray) to ArrayList
                for(String character: splitLine[3].substring(1, splitLine[3].length() - 1).replaceAll(" ", "").split(",")) {

                    cryptogramToSolve.cipheredArray.add(character);

                }

                // Converts line[2] (PlayerProgress) to ArrayList
                for(String character: splitLine[4].substring(1, splitLine[4].length() - 1).replaceAll(" ", "").split(",")) {

                    cryptogramPlayerProgress.add(character);

                }
                return cryptogramToSolve;
            }
        }

        if(!flag) {

            System.out.println(playerName + " not found");
            return null;

        }

        scanner.close();

        return null;
    }

    /*
    cryptogramToSolve = Jumbled-up solution/cryptogram problem
    cryptogramPlayerProgress = Editable cryptogramToSolve, to match our solution phrase
     */
    public static void saveCryptogram(String playerName, Cryptogram cryptogramToSolve, ArrayList<String> cryptogramPlayerProgress, String filename) {

        try {

            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            int lineNumber = 0;
            boolean flag = false;

            for(String line: lines) {

                String[] lineSplit = line.split(";");

                if(lineSplit[1].equals(playerName)) {
                    System.out.println("You have an old saved game, type 1 to overwrite or 0 to go back.");
                    Scanner getInput = new Scanner(System.in);
                    String input = getInput.nextLine();
                    if(Objects.equals(input,"1")) {
                        if (cryptogramToSolve instanceof LetterCryptogram) {
                            lines.set(lineNumber, "L;" + playerName + ";" + cryptogramToSolve + ";" + cryptogramPlayerProgress);
                        } else {
                            lines.set(lineNumber, "N;" + playerName + ";" + cryptogramToSolve + ";" + cryptogramPlayerProgress);
                        }
                        flag = true;
                        break;
                    }
                    else {
                        break;
                    }

                }
                lineNumber++;

            }

            if(!flag) {
                if (cryptogramToSolve instanceof LetterCryptogram){
                    lines.add("L" + ";" + playerName + ";" +cryptogramToSolve + ";" + cryptogramPlayerProgress);
                }
                else {

                    lines.add("N" + ";" + playerName + ";" + cryptogramToSolve + ";" + cryptogramPlayerProgress);
                }

            }

            Files.write(path, lines, StandardCharsets.UTF_8);

        } catch (IOException error) {

            System.out.println("[Error] Unable to create 'cryptogram.txt'");
            System.exit(1);

        }

    }

    public static void savePlayer(Player player,String filename) {

        try {

            File file = new File(filename);
            if(!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException error) {

            System.out.println("[Error] Unable to create 'player.txt'");
            System.exit(1);

        }

        try {
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            int lineNumber = 0;
            boolean flag = false;
            for(String line: lines) {

                String[] lineSplit = line.split(";");

                if (lineSplit[0].equals(player.getUsername())) {
                    lines.set(lineNumber, player.toString() +"\n");
                    FileWriter fileWriter = new FileWriter(filename);
                    fileWriter.write(String.join("\n", lines));
                    fileWriter.close();
                    flag = true;
                    break;

                }
                lineNumber++;
            }
            if (!flag) {
                FileWriter fileWriter = new FileWriter(filename, true);
                fileWriter.write(player.toString() + "\n");
                fileWriter.close();
            }
        }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static Player loadOldStats(String name) {
        Player player = new Player(name,0,0,0,0,0);
        List<String> lines = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File("player.txt")).useDelimiter(";");
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }
            input.close();
            int flag=0;
            for (int i=0; i<lines.size();i++) {
                String[] line = lines.get(i).split(";");
                if(line.length != 6 && !lines.isEmpty()) {
                    System.out.println("File corrupt, cannot read stats.");
                }
                else {
                    if (Objects.equals(line[0], name)) {
                        player.setUsername(name);
                        player.setAccuracy(Double.parseDouble(line[1]));
                        player.setTotalGuesses(Integer.parseInt(line[2]));
                        player.setTotalCorrectGuesses(Integer.parseInt(line[3]));
                        player.setCryptogramsPlayed(Integer.parseInt(line[4]));
                        player.setCryptogramsCompleted(Integer.parseInt(line[5]));
                        flag = 1;
                        input.close();
                    }
                }
            }
            if(flag ==0) {
                System.out.println("This username was not found before, new player created with default stats");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File to load stats not found");
            e.printStackTrace();
        }
        return player;
    }
}
