package src;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StatsTest {

    Player p = new Player("Alvin",75.0,10, 5, 3, 1);


    @Test
    void testGetUsername()
    {
        assertEquals("Alvin", p.getUsername());
    }

    @Test
    void testGetAccuracy()
    {
        assertEquals(75.0, p.getAccuracy());
    }

    @Test
    void testGetTotalGuesses()
    {
        assertEquals(10.0, p.getTotalGuesses());
    }

    @Test
    void testGetCorrectGuesses() {
        assertEquals(5.0, p.getTotalCorrectGuesses());
    }

    @Test
    void testGetCryptogramsPlayed()
    {
        assertEquals(4, p.getCryptogramsPlayed());
    }

    @Test
    void testGetCryptogramsCompleted()
    {
        assertEquals(4, p.getCryptogramsCompleted());
    }

    @Test
    void testSetAccuracy() {
        p.setAccuracy(80.0);
        assertEquals(80.0, p.getAccuracy());

        p.setAccuracy(40.0);
        assertEquals(40.0, p.getAccuracy());
    }

    @Test
    void testUpdateAccuracy() {
        p.updateAccuracy();
        assertEquals(90.0, p.getAccuracy());
    }

    @Test
    void testIncrementCryptogramsCompleted() {
        p.incrementCryptogramsCompleted();
        assertEquals(5, p.getCryptogramsCompleted());

        p.incrementCryptogramsCompleted();
        assertEquals(6, p.getCryptogramsCompleted());
    }

    @Test
    void testIncrementTotalGuesses() {
        p.incrementTotalGuesses();
        assertEquals(2.0, p.getTotalGuesses());

        p.incrementTotalGuesses();
        assertEquals(15.0, p.getTotalGuesses());
    }

    @Test
    void testIncrementCorrectGuesses() {
        p.incrementTotalCorrectGuesses();
        assertEquals(6.0, p.getTotalCorrectGuesses());

        p.incrementTotalCorrectGuesses();
        assertEquals(7.0, p.getTotalCorrectGuesses());
    }

    @Test
    void testIncrementCryptogramsPlayed() {
        p.incrementCryptogramsPlayed();
        assertEquals(4.0, p.getCryptogramsPlayed());

        p.incrementCryptogramsPlayed();
        assertEquals(3.0, p.getCryptogramsPlayed());
    }

}
