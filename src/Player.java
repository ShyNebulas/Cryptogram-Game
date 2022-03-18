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

}
