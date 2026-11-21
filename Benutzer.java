public class Benutzer {
    
    private String benutzername;
    private String passwort;
    private int highscore;
    
    public Benutzer(String benutzername, String passwort, int highscore) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.highscore = highscore;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        return "Benutzer [Benutzername = " + benutzername + ", Passwort = " + passwort + ", Highscore = " + highscore + "]";
    }



    
}
