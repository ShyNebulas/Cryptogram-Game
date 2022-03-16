package src;
import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavingLoadingCryptogramTest {

    public Cryptogram testL;
    public Cryptogram testN;
    public String solutionPhraseL;
    public String solutionPhraseN;
    public File file = new File("cryptogram.txt");;

    @BeforeEach
    public void setUp() {
        testL = new LetterCryptogram(); //declaring cryptogram
        solutionPhraseL = testL.phrase; // getting phrase
        testL.cipheredArray = testL.generateCipheredArray(solutionPhraseL); // making new cryptoPhrase

        testN = new LetterCryptogram(); //declaring cryptogram
        solutionPhraseN = testN.phrase; // getting phrase
        testN.cipheredArray = testN.generateCipheredArray(solutionPhraseN); // making new cryptoPhrase
    }

    @Test
    public void fileReadTest() {
        assertTrue(file.canRead());
    }

    @Test
    public void saveNloadLetterTest() {
        SavingLoading.saveCryptogram(testL, testL.getPlayingArray(testL.cipheredArray)); //saving file
        Cryptogram test2 = new LetterCryptogram(); //declaring new cryptogram
        // Must declare arraylist (cipheredArray) before passing it into load!
        test2.cipheredArray = new ArrayList<>(); //declaring/making cipheredArray
        ArrayList<String> playerProgress = new ArrayList<>();//declaring/making tempArraylist
        SavingLoading.loadCryptogram(test2, playerProgress);// loading saved cryptogram to test2

        assertEquals((test2.toString()),(testL.toString()));
        assertEquals((playerProgress.toString().toString()),((testL.getPlayingArray(testL.cipheredArray)).toString()));
    }

    @Test
    public void saveNloadNumberTest() {
        SavingLoading.saveCryptogram(testN, testN.getPlayingArray(testN.cipheredArray)); //saving file
        Cryptogram test2 = new NumberCryptogram(); //declaring new cryptogram
        // Must declare arraylist (cipheredArray) before passing it into load!
        test2.cipheredArray = new ArrayList<>(); //declaring/making cipheredArray
        ArrayList<String> playerProgress = new ArrayList<>();//declaring/making tempArraylist
        SavingLoading.loadCryptogram(test2, playerProgress);// loading saved cryptogram to test2

        assertEquals((test2.toString()),(testN.toString()));
        assertEquals((playerProgress.toString().toString()),((testN.getPlayingArray(testN.cipheredArray)).toString()));
    }


}