import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Benutzerverwaltung.readFromTxt();
        Wortverwaltung.readFromWoerterTxt();

        System.out.println("------Anmeldung------");
        System.out.println();
        System.out.print("Name eingeben: ");
        String eingabeName = sc.nextLine();

        System.out.println();
        System.out.print("Passwort eingeben: ");
        String eingabePasswort = sc.nextLine();

        boolean erfolgreichLogIn = Benutzerverwaltung.anmeldung(eingabeName, eingabePasswort);

        if (erfolgreichLogIn == true) {
            Benutzer aktuellerSpieler = Benutzerverwaltung.getBenutzer(eingabeName);
            Spiel aktuellesSpiel = new Spiel();
            aktuellesSpiel.spielen(aktuellerSpieler);

        }

        sc.close();

    }
}
