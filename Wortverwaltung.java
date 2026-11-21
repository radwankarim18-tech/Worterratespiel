import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wortverwaltung {

    private static List<String> woerter = new ArrayList<>();

    // Wörter von Txt lesen, in ArrayList speichern und anzeigen
    public static void readFromWoerterTxt() {

        FileReader file = null;
        Scanner sc = null;

        try {
            file = new FileReader("woerter.txt");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Datei nicht gefunden!");
        }

        woerter.clear();

        while (sc.hasNext()) {
            woerter.add(sc.next());
        }

        try {
            file.close();
        } catch (IOException e) {
            System.err.println("Probleme mit Datei");
        }

    }

    // Random Wort
    public static String randomWort() {
        final Random random = new Random();

        int index = random.nextInt(woerter.size());
        return woerter.get(index);

    }

}
