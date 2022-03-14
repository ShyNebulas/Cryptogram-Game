package src;

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

    public void updateAccuracy() {
        this.accuracy = ((double) this.totalGuesses /this.totalCorrectGuesses) * 100;
    }

    public int getTotalGuesses() {
        return this.totalGuesses;
    }

    public void incrementTotalGuesses() {
        this.totalGuesses = this.totalGuesses+1;
    }

    public int getTotalCorrectGuesses() {
        return this.totalCorrectGuesses;
    }
    public void incrementTotalCorrectGuesses() {
        this.totalCorrectGuesses = this.totalCorrectGuesses+1;
    }

    public int getCryptogramsPlayed() {
        return this.cryptogramsPlayed;
    }

    public void incrementCryptogramsPlayed() {
        this.cryptogramsPlayed = this.cryptogramsPlayed+1;
    }

    public int getCryptogramsCompleted() {
        return this.cryptogramsCompleted;
    }

    public void incrementCryptogramsCompleted() {
        this.cryptogramsCompleted = this.cryptogramsCompleted +1;
    }

}
