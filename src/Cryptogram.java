package src;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Cryptogram {
    public String phrase = readCryptFromFile("phrases.txt");
    public ArrayList<String> cipheredArray;
    static String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    int getFrequencies () {
        return 0;
    }

    public String readCryptFromFile(String filename) {
        List<String> lines = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            sc.close();  //closes the scanner

        } catch (Exception e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }

    static void shuffle(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    public String toString() {
        return this.phrase + ";" + this.cipheredArray;
    }

    public abstract ArrayList<String> getPlayingArray (ArrayList<String> cipheredArray);
    public abstract ArrayList<String>  generateCipheredArray (String phraseToCipher);
}


//    Live Laugh Love :)
//        I am looking right at the other half of me
//        I am just a man not a hero
//        Let us love like there are no goodbyes
//        Every little thing gonna be alright
//        You are Beautiful no matter what they say
//        It is a beautiful day Dont let it get away
//        love for all hatred for none
//        change the world by being yourself
//        Change the world by being yourself
//        Every moment is a fresh beginning
//        Never regret anything that made you smile
//        Everything you can imagine is real
//        Whatever you do do it well
//        Simplicity is the ultimate sophistication
//        Die with memories not dreams
//        What we think we become