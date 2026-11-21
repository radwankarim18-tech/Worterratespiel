import java.util.Scanner;

public class Spiel {

    private final int ANZAHL_FEHLVERSUCHE = 15;
    private int counterVersuche = 0;
    private String randomWort;
    private char buchstabe;

    // Konstruktor
    public Spiel() {

    }

    public void spielen(Benutzer spieler) {
        Scanner sc = new Scanner(System.in);
        boolean newGame = false;

        do {
            try {
                System.out.println("Dein Highscore beträgt: " + spieler.getHighscore());

                randomWort = Wortverwaltung.randomWort().toUpperCase();
                char[] verdecktesArray = new char[randomWort.length()];

                // Hier wird Wort verdeckt
                wortVerdecken(randomWort, verdecktesArray);

                int radPunkte = Gluecksrad.dreheGluecksrad();
                System.out.println("Mögliche Punktwerte zu erreichen: " +
                        radPunkte);

                // Eingabe Buchstabe
                System.out.println("Anzahl Versuche: " + ANZAHL_FEHLVERSUCHE);
                System.out.print("Gebe einen Buchstaben ein: ");
                buchstabe = sc.next().toUpperCase().charAt(0);

                int aktuellePunkte = 0;
                boolean arrayFull = false;
                while (counterVersuche < ANZAHL_FEHLVERSUCHE && arrayFull == false) {

                    boolean buchstabeRichtig = eingabeBuchstabe(randomWort, buchstabe);
                    if (buchstabeRichtig == true) {
                        radPunkte = Gluecksrad.dreheGluecksrad();
                        System.out.println("Mögliche Punkte: " + radPunkte);
                        aktuellePunkte = aktuellePunkte + radPunkte;
                        arrayFull = buchstabeAufdecken(randomWort, buchstabe, verdecktesArray);

                    } else {
                        counterVersuche++;
                        System.out.println("Fehlversuche: " + counterVersuche);
                    }

                    // Wenn alles richtig erraten
                    if (arrayFull == true) {
                        System.out.println("Gewonnen!");
                        System.out.println("Deine Versuche: " + counterVersuche);
                        Benutzerverwaltung.newHighscore(spieler.getBenutzername(), aktuellePunkte);
                        break; // raus aus schleife

                    } else if (arrayFull == false && counterVersuche >= ANZAHL_FEHLVERSUCHE) {
                        System.out.println("Leider verloren. Das Wort war: " + randomWort);
                        break;
                    }

                    System.out.print("Nächster Buchstabe: ");
                    buchstabe = sc.next().toUpperCase().charAt(0);
                    
                }

                // Input validation newGame
                System.out.print("Nochmal spielen?: ");
                String eingabeNewGame = sc.next();
                inputValidation(eingabeNewGame);
                newGame = inputValidation(eingabeNewGame);

            } catch (Exception e) {
                System.out.println("Falsche Eingabe");
            }
        } while (newGame == true);

    }

    // Verdeckt Wort
    private void wortVerdecken(String wort, char[] array) {
        // Neu
        for (int i = 0; i < wort.length(); i++) {
            array[i] = '_';
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }

    // Überprüft Buchstabe
    private boolean eingabeBuchstabe(String randomWort, char buchstabe) {
        boolean contains = false;
        boolean validBuchstabe = false;
        do {
            try {
                if (randomWort.contains(String.valueOf(buchstabe))) {
                    contains = true;
                    validBuchstabe = true;
                } else if (!randomWort.contains(String.valueOf(buchstabe))) {
                    contains = false;
                    validBuchstabe = true;
                } else {
                    validBuchstabe = false;
                }
            } catch (Exception e) {
                System.out.println("Ungültiger Buchstabe!");
            }
        } while (validBuchstabe == false);
        return contains;

    }

    // Buchstabe aufdecken wenn richtiger buchstabe
    private boolean buchstabeAufdecken(String randomWort, char buchstabe, char[] arrayWort) {
        boolean treffer = false;
        boolean allesAufgedeckt = true;

        for (int i = 0; i < arrayWort.length; i++) {
            if (randomWort.charAt(i) == buchstabe) {
                arrayWort[i] = buchstabe;
                treffer = true;
            }
            if (arrayWort[i] == '_') {
                System.out.print(arrayWort[i] + "  ");
                allesAufgedeckt = false;
            } else {
                System.out.print(arrayWort[i] + "  ");
            }
        }
        System.out.println();
        return allesAufgedeckt;

        // mit for schleife über wort iterieren wenn buchstabe.equals i dann sysout
        // buchstabe und else sysout ---
    }

    // Für neues Spiel
    private boolean inputValidation(String newGameInput) {
        boolean richtigEingegeben = false;
        do {
            try {
                if (newGameInput.equalsIgnoreCase("ja")) {
                    richtigEingegeben = true;
                    return true;
                } else if (newGameInput.equalsIgnoreCase("nein")) {
                    richtigEingegeben = true;
                    return false;
                } else {
                    richtigEingegeben = false;
                }
            } catch (Exception e) {
                System.out.println("Nochmal eingeben! (Ja/Nein)");
            }
        } while (richtigEingegeben == false);
        return false;
    }

}
