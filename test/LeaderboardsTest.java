package test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LeaderboardsTest {

    @Test
    public void emptyFile(){
        assertNull(Player.leaderboards("emptyPlayer.txt"));
    }

    @Test
    public void noFileFound(){
        assertNull(Player.leaderboards("nonexistantPlayer.txt"));
    }

    @Test
    public void only10(){
        assertTrue(Player.leaderboards("playermorethan10.txt").size() == 10);
    }

    @Test
    public void correctOrder(){
       ArrayList<String> top10 = Player.leaderboards("leaderboardTest.txt");
       ArrayList<String> testOrder = new ArrayList<>();
       testOrder.add("1. test5 | 22");
       testOrder.add("2. test1 | 11");
       testOrder.add("3. test3 | 9");
       testOrder.add("4. test2 | 6");
       testOrder.add("5. ...");
       testOrder.add("6. ...");
       testOrder.add("7. ...");
       testOrder.add("8. ...");
       testOrder.add("9. ...");
       testOrder.add("10. ...");
       assertEquals(top10, testOrder);
    }

}
