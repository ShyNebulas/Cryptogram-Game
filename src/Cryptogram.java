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


