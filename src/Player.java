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

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getTotalGuesses() {
        return this.totalGuesses;
    }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public int getCryptogramsPlayed() {
        return this.cryptogramsPlayed;
    }

    public void setCryptogramsPlayed(int cryptogramsPlayed) {
        this.cryptogramsPlayed = cryptogramsPlayed;
    }

    public int getCryptogramsCompleted() {
        return this.cryptogramsCompleted;
    }

    public void setCryptogramsCompleted(int cryptogramsCompleted) {
        this.cryptogramsCompleted = cryptogramsCompleted;
    }

}
