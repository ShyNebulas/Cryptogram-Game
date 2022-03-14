package test;
import org.junit.jupiter.api.BeforeEach;
import src.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UnitTest {

    Cryptogram cryptogramLetter;
    Cryptogram cryptogramNumber;
    Player player;
    Game game;

    ArrayList<String> letterInGameArray;
    ArrayList<String> numberInGameArray;

    @BeforeEach
    public void setup() {

       cryptogramLetter = new LetterCryptogram();
       cryptogramNumber = new NumberCryptogram();
       player = new Player("Username", 0, 0, 0, 0, 0);

    }

}
