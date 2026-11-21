import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Benutzerverwaltung {

    private static List<Benutzer> alleBenutzer = new ArrayList<>();

    // Getter für ArrayList
    public static List<Benutzer> getAllBenutzer() {
        return alleBenutzer;
    }

    // Sucht den richtigen Benutzer für Spiel
    public static Benutzer getBenutzer(String eingabeName) {
        for (Benutzer benutzer : alleBenutzer) {
            if (benutzer.getBenutzername().equals(eingabeName)) {
                return benutzer;
            }
        }
        return null;
    }

    // Anmeldung
    public static boolean anmeldung(String name, String password) {
        for (Benutzer benutzer : alleBenutzer) {
            if (benutzer.getBenutzername().equals(name)) {
                if (benutzer.getPasswort().equals(password)) {
                    System.out.println();
                    System.out.println("Willkommmen " + benutzer.getBenutzername());
                    System.out.println("--------------------");
                    return true;

                } else {
                    System.out.println("Falsches Passwort!");
                    return false;
                }
            }
        }
        System.out.println("Benutzer existiert nicht!");
        return false;
    }

    // Benutzer in txt speichern/schreiben
    public static void writeInTxt() {

        PrintWriter wfile = null;

        try {
            wfile = new PrintWriter("benutzer.txt");

            for (Benutzer b : alleBenutzer) {
                wfile.println(b.getBenutzername() + " " + b.getPasswort() + " " + b.getHighscore());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Datei nicht gefunden!");
        } finally {
            if (wfile != null) {
                wfile.close();
            }
        }
    }

    // Benutzer aus txt lesen und in ArrayList speichern
    public static void readFromTxt() {

        FileReader file = null;
        Scanner sc = null;

        try {
            file = new FileReader("benutzer.txt");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Datei nicht gefunden!");
        }

        alleBenutzer.clear();

        while (sc.hasNext()) {
            alleBenutzer.add(
                    new Benutzer(
                            sc.next(),
                            sc.next(),
                            sc.nextInt()));
        }

        try {
            file.close();
        } catch (IOException e) {
            System.err.println("Probleme mit Datei!");
        }

    }

    // Highscore Methode
    public static void newHighscore(String spielerName, int score) {
        for (Benutzer benutzer : alleBenutzer) {
            if (benutzer.getBenutzername().equals(spielerName)) {
                if (score > benutzer.getHighscore()) {
                    int newHighscore = score;
                    benutzer.setHighscore(newHighscore);
                    Benutzerverwaltung.writeInTxt();
                    System.out.println("Highscore beträgt jetzt: " + benutzer.getHighscore());
                    return;

                } else {
                    System.out.println("Highscore nicht aktualisiert");
                    return;
                }
            }
        }
    }
}
