package src;

public class Player {

    private String username;
    private double accuracy;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

    public Player (String username,double accuracy, int totalGuesses, int cryptogramsPlayed, int cryptogramsCompleted){
        this.username = username;
        this.accuracy= accuracy;
        this.totalGuesses = totalGuesses;
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

    public void updateAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getTotalGuesses() {
        return this.totalGuesses;
    }

    public void incrementTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses+1;
    }

    public int getCryptogramsPlayed() {
        return this.cryptogramsPlayed;
    }

    public void incrementCryptogramsPlayed(int cryptogramsPlayed) {
        this.cryptogramsPlayed = cryptogramsPlayed+1;
    }

    public int getCryptogramsCompleted() {
        return this.cryptogramsCompleted;
    }

    public void incrementCryptogramsCompleted(int cryptogramsCompleted) {
        this.cryptogramsCompleted = cryptogramsCompleted +1;
    }

}
