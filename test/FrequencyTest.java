package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class FrequencyTest {
	public Cryptogram test;
	public Game t;

@BeforeEach
void setUp() {
	test = new LetterCryptogram();
	this.t = new Game();
}

@Test
void FrequencyTest() {
	if(t.selection=='3'){
		t.LetterNumberFrequency();
	}
}

}




