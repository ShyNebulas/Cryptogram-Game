package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
/**
 * This test is checking a test file player2.txt and newplayer.txt,
 * errors have been added in newplayer.txt on purpose to check for exceptions thrown
 * player2.txt has a test player with the same syntax as in the original player details file
 * do not alter these files they are vital for the tests to pass.
 * **/
class LoadDetailsTest {
	Player a;

@BeforeEach
void setUp() {

}

@Test
void LoadTheirDetails() {
	Scanner input;
	try {
		input = new Scanner(new File("player2.txt")).useDelimiter(";"); //gets users details
		a = new Player(input.next(), input.nextDouble(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt()); //stores users details
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	if(a.getCryptogramsPlayed()>0.5 && a.getUsername()!=null) { //checks if the user has played at last one game and has entered their name
		 a.toString(); //shows the user their details
	}else {
		fail();
	}
}



@Test
void ErrorLoadingDetails() throws InputMismatchException, FileNotFoundException {

assertThrows(InputMismatchException.class,
		() -> {
				Scanner input = new Scanner(new File("newplayer.txt")).useDelimiter(";"); //gets users details but I added an error to it
			    a = new Player(input.next(), input.nextDouble(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt()); //stores users details
		});
}

@Test
void PlayerNotFound() {
	Scanner input;
	try {
		input = new Scanner(new File("player2.txt")).useDelimiter(";"); //gets users details
		a = new Player(input.next(), input.nextDouble(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt()); //stores users details
	} catch (FileNotFoundException e) {
		  File file = new File("newplayer.txt");
          try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
}




