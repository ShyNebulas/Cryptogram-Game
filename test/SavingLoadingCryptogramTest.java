package test;


import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Cryptogram;
import src.LetterCryptogram;
import src.NumberCryptogram;
import src.SavingLoading;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** This test fills the txt file and then checks the contents
 * Because the save function asks for user input if
 * there is a saved game under that name,
 * you must clear the cryptogramTest.txt file before and after running these tests**/

public class SavingLoadingCryptogramTest {

    public Cryptogram testL;
    public Cryptogram testN;
    public String solutionPhraseL;
    public String solutionPhraseN;
    public String testNameN = "testN";
    public String testNameL = "testL";
    public String filename = "cryptogramTest.txt";
    public File file = new File("cryptogram.txt");

    @BeforeEach
    public void setUp() {
        testL = new LetterCryptogram();
        solutionPhraseL = testL.phrase; // getting phrase
        testL.cipheredArray = testL.generateCipheredArray(solutionPhraseL); // making new cryptoPhrase

        testN = new NumberCryptogram();
        solutionPhraseN = testN.phrase; // getting phrase
        testN.cipheredArray = testN.generateCipheredArray(solutionPhraseN); // making new cryptoPhrase
    }

    @Test
    public void fileReadTest() {
        assertTrue(file.canRead());
    }

    @Test
    public void saveNloadLetterTest() {
        SavingLoading.saveCryptogram(testNameL,testL, testL.getPlayingArray(testL.cipheredArray),filename); //saving file
        Cryptogram test2 = null;

        ArrayList<String> playerProgress = new ArrayList<>();//declaring/making tempArraylist
        test2= SavingLoading.loadCryptogram(testNameL, playerProgress,filename);// loading saved cryptogram to test2

        assertEquals((test2.toString()),(testL.toString()));
        assertEquals((playerProgress.toString().toString()),((testL.getPlayingArray(testL.cipheredArray)).toString()));
    }

    @Test
    public void saveNloadNumberTest() {
        SavingLoading.saveCryptogram(testNameN,testN, testN.getPlayingArray(testN.cipheredArray),filename); //saving file
        Cryptogram test2 = null; //declaring new cryptogram
        // Must declare arraylist (cipheredArray) before passing it into load!
        ArrayList<String> playerProgress = new ArrayList<>();//declaring/making tempArraylist
        test2= SavingLoading.loadCryptogram(testNameN, playerProgress,filename);// loading saved cryptogram to test2

        assertEquals((test2.toString()),(testN.toString()));
        assertEquals((playerProgress.toString().toString()),((testN.getPlayingArray(testN.cipheredArray)).toString()));
    }

}