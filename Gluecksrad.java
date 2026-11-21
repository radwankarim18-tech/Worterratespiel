import java.util.Random;

public class Gluecksrad {
    
    private static final Random random = new Random();
    private static final int[] PUNKTE_WERTE = {0, 10, 20, 50, 100, 200};
    
    public static int dreheGluecksrad() {
        int index = random.nextInt(PUNKTE_WERTE.length);
        return PUNKTE_WERTE[index];
    }

}
