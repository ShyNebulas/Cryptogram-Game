package src;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoadDetailsTest {

	public Game game;

    @BeforeEach
    public void setUp() {
    	game.currentPlayer.setCryptogramsPlayed(1);
    	game.currentPlayer.setUsername("ee");
    	game.currentPlayer.setCryptogramsCompleted(5);
    	game.currentPlayer.setAccuracy(4);
    	game.currentPlayer.setTotalCorrectGuesses(7);
    	game.currentPlayer.setTotalGuesses(4);
    }
    
	
	
	
	@Test
	void LoadTestAfterOneGame() {

		String expected = "ee";
		String actual = game.currentPlayer.getUsername();
		assertEquals(expected,actual,"a");
		
	}
    	//Scanner input;
		//try {
		//	input = new Scanner(new File("C://Users//benoo//Desktop//player.txt"));
		//    currentPlayer.setUsername(input.nextLine());
		  //  currentPlayer.setAccuracy(input.nextDouble());
		 //   currentPlayer.setTotalGuesses(input.nextInt());
		//  currentPlayer.setTotalCorrectGuesses(input.nextInt());
		//    currentPlayer.setCryptogramsPlayed(input.nextInt());
		 //   currentPlayer.setCryptogramsCompleted(input.nextInt());
		//input.close();
		//g.onStartMenu();
		//} catch (FileNotFoundException e) {
	//		e.printStackTrace();
		//}
    //	g.currentPlayer.setCryptogramsPlayed(2);
    //	if(g.currentPlayer.getCryptogramsPlayed()>1) {
    	//	(g.currentPlayer.getUsername(), "");
	//	assertEquals("",this.game.currentPlayer.getUsername());
    	}

