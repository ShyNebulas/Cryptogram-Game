package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Player {

    private String username;
    private double accuracy;
    private int totalGuesses;
    private int totalCorrectGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

    public Player (String username,double accuracy, int totalGuesses, int totalCorrectGuesses, int cryptogramsPlayed, int cryptogramsCompleted){
        this.username = username;
        this.accuracy= accuracy;
        this.totalGuesses = totalGuesses;
        this.totalCorrectGuesses = totalCorrectGuesses;
        this.cryptogramsPlayed = cryptogramsPlayed;
        this.cryptogramsCompleted=cryptogramsCompleted;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }

    public void updateAccuracy() {
        this.accuracy = ((double) this.totalCorrectGuesses /this.totalGuesses) * 100;
    }

    public int getTotalGuesses() {
        return this.totalGuesses;
    }

    public void setTotalGuesses(int totalGuesses) { this.totalGuesses = totalGuesses; }

    public void incrementTotalGuesses() {
        this.totalGuesses = this.totalGuesses+1;
    }

    public int getTotalCorrectGuesses() {
        return this.totalCorrectGuesses;
    }

    public void setTotalCorrectGuesses(int totalCorrectGuesses) { this.totalCorrectGuesses = totalCorrectGuesses; }

    public void incrementTotalCorrectGuesses() {
        this.totalCorrectGuesses = this.totalCorrectGuesses+1;
    }

    public int getCryptogramsPlayed() {
        return this.cryptogramsPlayed;
    }

    public void setCryptogramsPlayed(int cryptogramsPlayed) { this.cryptogramsPlayed = cryptogramsPlayed; }

    public void incrementCryptogramsPlayed() {
        this.cryptogramsPlayed = this.cryptogramsPlayed+1;
    }

    public int getCryptogramsCompleted() {
        return this.cryptogramsCompleted;
    }

    public void setCryptogramsCompleted(int cryptogramsCompleted) { this.cryptogramsCompleted = cryptogramsCompleted; }

    public void incrementCryptogramsCompleted() {
        this.cryptogramsCompleted = this.cryptogramsCompleted +1;
    }

    @Override
    public String toString() {
        return this.username + ";" + this.accuracy + ";" + this.totalGuesses + ";" + this.totalCorrectGuesses + ";" + this.cryptogramsPlayed + ";" + this.cryptogramsCompleted;
    }

    public static HashMap<String, Integer> sort(HashMap<String, Integer> hashmap) {

        List<Map.Entry<String, Integer>> mapList = new LinkedList<Map.Entry<String, Integer>>(hashmap.entrySet());

        Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> object1, Map.Entry<String, Integer> object2) {

                return object2.getValue().compareTo(object1.getValue());

            }

        });

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> element: mapList) {

            temp.put(element.getKey(), element.getValue());

        }

        return temp;

    }

    public static ArrayList<String> leaderboards(String filename) {

        ArrayList<String> top10 = new ArrayList<>();

        Scanner scanner = null;

        try {

            scanner = new Scanner(new File(filename));

            if(!scanner.hasNextLine()) {

                System.out.println("No players have been stored yet!");
                return null;

            }

        } catch(FileNotFoundException error) {

            System.out.println("[Error]" + "'" + filename + "'" + "file not found");
            return null;

        }

        HashMap<String, Integer> players = new HashMap<String, Integer>();

        while(scanner.hasNextLine()) {

            String[] splitLine = scanner.nextLine().split(";");

            players.put(splitLine[0], Integer.valueOf(splitLine[5]));

        }

        HashMap<String, Integer> test = sort(players);

        int counter = 1;

        for(Map.Entry<String, Integer> element: test.entrySet()) {
        if(counter <= 10) {
            if (element.getValue() > 1) {

                top10.add(counter + ". " + element.getKey() + " | " + element.getValue());

                counter++;
            }
        }

        }

        while(counter <= 10){
            top10.add(counter + ". ...");
            counter++;
        }
        return top10;

    }

}
