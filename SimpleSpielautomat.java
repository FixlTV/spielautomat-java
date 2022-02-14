import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SimpleSpielautomat {
    private SPIELWALZE spielwalze1, spielwalze2, spielwalze3;
    private int z1, z2, z3;
    private static int guthaben = 100;
    private static int highscore = 100;
    private static int pointswon = 0;
    private static int games_played = 0;
    public static ZEICHENFENSTER z;
    
    public SimpleSpielautomat() throws IOException {
        // main(null);
        z1 = (int)(Math.random() * 9);
        z2 = (int)(Math.random() * 9);
        z3 = (int)(Math.random() * 9);
        spielwalze1 = new SPIELWALZE(20, 20, 100, z3);
        spielwalze2 = new SPIELWALZE(120, 20, 100, z2);
        spielwalze3 = new SPIELWALZE(220, 20, 100, z1);
        if(ZEICHENFENSTER.singleton == null) z = new ZEICHENFENSTER("Spielautomat - vereinfachte Version", 340, 140, true, false);
        zeichne();
    }
    
    public SimpleSpielautomat(int x, int y, int groesse) {
        z1 = (int)(Math.random() * 9);
        z2 = (int)(Math.random() * 9);
        z3 = (int)(Math.random() * 9);
        spielwalze1 = new SPIELWALZE(x, y, groesse, z1);
        spielwalze2 = new SPIELWALZE(x + groesse, y, groesse, z2);
        spielwalze3 = new SPIELWALZE(x + 2 * groesse, y, groesse, z3);
        if(ZEICHENFENSTER.singleton == null) z = new ZEICHENFENSTER("Spielautomat - vereinfachte Version", 2 * x + 3 * groesse, 2 * y + groesse, true, false);
        zeichne();
    }
    
    public void zeichne() {
        spielwalze1.zeichne();
        spielwalze2.zeichne();
        spielwalze3.zeichne();
    }
    
    public void aktualisiere(int z1Neu, int z2Neu, int z3Neu) {
        for (int i = 0; i < 15; i++) {
            z1++;
            if(z1 == 9) z1 = 0;
            spielwalze1.faerbeUm(z1);
            z2++;
            if(z2 == 9) z2 = 0;
            spielwalze2.faerbeUm(z2);
            z3++;
            if(z3 == 9) z3 = 0;
            spielwalze3.faerbeUm(z3);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
        while(z1 != z1Neu || z2 != z2Neu || z3 != z3Neu) {
            if(z1 != z1Neu) {
                z1++;
                if(z1 == 9) z1 = 0;
                spielwalze1.faerbeUm(z1);
            }
            if(z2 != z2Neu) {
                z2++;
                if(z2 == 9) z2 = 0;
                spielwalze2.faerbeUm(z2);
            }
            if(z3 != z3Neu) {
                z3++;
                if(z3 == 9) z3 = 0;
                spielwalze3.faerbeUm(z3);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
    
    // public void spiele() {
    //     aktualisiere((int)(Math.random() * 9), (int)(Math.random() * 9), (int)(Math.random() * 9));
    // }
        
    public void spiele() throws IOException {
        aktualisiere((int)(Math.random() * 9), (int)(Math.random() * 9), (int)(Math.random() * 9));
        guthaben += guthaben();
        switch(guthaben()) {
            case 9: pointswon += 7;
            case 2: pointswon += 3;
            case -1: games_played += 1;
        }
        if(guthaben > highscore) highscore = guthaben;
        speichern("data/scores.data", String.valueOf(guthaben) + " " + String.valueOf(highscore) + " " + String.valueOf(pointswon) + " " + String.valueOf(games_played));
    }
    
    public int guthaben() {
        if(z1 == z2 && z1 == z3) {
            return 9;
        } else if(z1 == z2 || z1 == z3 || z2 == z3) {
            return 2;
        } else return -1;
    }
    
    public static synchronized void speichern(String file, String value) throws IOException {
        Writer w = new FileWriter(file);
        w.write(value);
        w.close();
    }
}