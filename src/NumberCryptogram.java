package src;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NumberCryptogram extends Cryptogram {

    public Map<String, Integer> getNumberMap() {
        Map<String, Integer> numberCrypt = new HashMap<>();
        shuffle(numbers);
        for (int i = 0; i < alphabet.length; i++)
        {
            numberCrypt.put(alphabet[i] , numbers[numbers[i]-1]);
        }
        return numberCrypt;
    }

@Override
    public String generateCipheredPhrase (String phraseToCipher) {
        String returnPhrase = "";
        phraseToCipher= phraseToCipher.toUpperCase();
        Map<String, Integer> cipherKey;
        cipherKey = getNumberMap();

        for (int i=0; i<phraseToCipher.length(); i++){
            if (!cipherKey.containsKey(Character.toString(phraseToCipher.charAt(i)))) {
                returnPhrase += "   "; //this is a tab
            }
            else {
                returnPhrase+= " ";  //this is a single space
                returnPhrase += (cipherKey.get(Character.toString(phraseToCipher.charAt(i))));
            }
        }
        return returnPhrase;
    }
}
