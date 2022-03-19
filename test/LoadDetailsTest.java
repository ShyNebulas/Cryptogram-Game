
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Player;

class LoadDetailsTest {
	Player a;

@BeforeEach
void setUp() {

}

@Test
void LoadTheirDetails() {
	Scanner input;
	try {
		input = new Scanner(new File("player.txt")); //gets users details
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
				Scanner input = new Scanner(new File("player2.txt")); //gets users details but I added an error to it
			    a = new Player(input.next(), input.nextDouble(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt()); //stores users details
		});
}

@Test
void PlayerNotFound() {
	Scanner input;
	try {
		input = new Scanner(new File("player.txt")); //gets users details
		a = new Player(input.nextLine(), input.nextDouble(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt()); //stores users details
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




