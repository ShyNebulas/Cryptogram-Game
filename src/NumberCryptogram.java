package src;
import java.util.*;

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
    public  ArrayList<String>  generateCipheredArray (String phraseToCipher) {
       ArrayList<String> cipheredArray = new ArrayList<>();
        phraseToCipher= phraseToCipher.toUpperCase();
        Map<String, Integer> cipherKey;
        cipherKey = getNumberMap();

        for (int i=0; i<phraseToCipher.length(); i++){

            cipheredArray.add(String.valueOf(cipherKey.getOrDefault(Character.toString(phraseToCipher.charAt(i)), -1)));

            // 0 spaces words, so 0 in this list means a space between words.
        }
        return cipheredArray;
    }


    @Override

    public ArrayList<String> getPlayingArray (ArrayList<String> cipheredArray) {

        ArrayList<String> playingList = new ArrayList<>();

        for (String eachNum : cipheredArray) {
            if (Objects.equals(eachNum, "-1")){
                playingList.add("-1");
            }
             else {
                 playingList.add("_");
            }
        }
        return playingList;
    }
}
