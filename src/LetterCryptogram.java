package src;

import java.util.HashMap;
import java.util.Map;

public class LetterCryptogram extends Cryptogram {

    public Map<String, String> getLetterMap() {
        Map<String, String> letterCrypt = new HashMap<>();
        shuffle(numbers);
        for (int i = 0; i < alphabet.length; i++) {
            letterCrypt.put(alphabet[i], alphabet[numbers[i] - 1]);
        }
        return letterCrypt;
    }

    @Override
    public String generateCipheredPhrase (String phraseToCipher) {
        Map<String,String> cipherKey;
        cipherKey = getLetterMap();
        String returnPhrase = "";
        phraseToCipher= phraseToCipher.toUpperCase();

        for (int i=0; i<phraseToCipher.length(); i++){
            returnPhrase += (cipherKey.getOrDefault(Character.toString(phraseToCipher.charAt(i)), (" ")));
        }
        return returnPhrase;
    }
}