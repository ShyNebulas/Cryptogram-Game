package src;

import java.util.ArrayList;
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
    public ArrayList<String> generateCipheredArray (String phraseToCipher) {
        Map<String,String> cipherKey;
        cipherKey = getLetterMap();
        ArrayList<String> returnArray = new ArrayList<>();
        phraseToCipher= phraseToCipher.toUpperCase();

        for (int i=0; i<phraseToCipher.length(); i++){
            returnArray.add((cipherKey.getOrDefault(Character.toString(phraseToCipher.charAt(i)), ("-1"))));
        }
        return returnArray;
    }

    @Override
    public ArrayList<String> getPlayingArray (ArrayList<String> cipheredArray) {
        ArrayList<String> playingArray = new ArrayList<>();
            for (String eachLetter : cipheredArray) {
                if (eachLetter.matches("[a-zA-Z]")) {
                    playingArray.add("_");
                }
                else {
                    playingArray.add("-1");  // -1 means a split between words.
                }
            }
            return playingArray;
    }
}
