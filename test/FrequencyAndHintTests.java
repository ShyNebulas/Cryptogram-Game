package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class FrequencyAndHintTests {
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




