import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.io.*;

public class Spielautomat 
{
    private SPIELWALZE spielwalze1, spielwalze2, spielwalze3;
    private int z1, z2, z3;
    private Random zufall;
    private static int guthaben = 100;
    private static int startcode = -1;
    private static int highscore = 100;
    private static int pointswon = 0;
    private static int games_played = 0;
    
    public Spielautomat() throws IOException
    {
        main(null);
        // zufall = new Random();
     
        // z1 = 7;
        // z2 = 7;
        // z3 = 7;
        // spielwalze1 = new SPIELWALZE(10, 10, 50, z3);
        // spielwalze2 = new SPIELWALZE(60, 10, 50, z2);
        // spielwalze3 = new SPIELWALZE(110, 10, 50, z1);
    }

    public Spielautomat(int breite, int hoehe) throws IOException {
        if(startcode == -1) {
            System.out.println("BlueJ Startschutz ausgelöst.");
            System.out.println("Zur Verwendung des Automaten bitte die Main Methode aufrufen.");
            System.out.println("");
            main(null);
            return;
        }
        int laenge = breite / 7;

        zufall = new Random();

        z1 = 7;
        z2 = 7;
        z3 = 7;
        spielwalze1 = new SPIELWALZE(breite / 2 - laenge / 2 * 3, 10, laenge, z3);
        spielwalze2 = new SPIELWALZE(breite / 2 - laenge / 2, 10, laenge, z2);
        spielwalze3 = new SPIELWALZE(breite / 2 + laenge / 2, 10, laenge, z1);
    }

    public static void main(String[] args) throws IOException  {
        File file = new File("data/scores.txt");
        if(!file.exists()) {
            speichern("data/scores.txt", String.valueOf(guthaben) + " " + String.valueOf(highscore) + " " + String.valueOf(pointswon) + " " + String.valueOf(games_played));
        }
        Scanner filescanner = new Scanner(file);
        filescanner.useDelimiter(" ");
        guthaben = Integer.parseInt(filescanner.next());
        highscore = Integer.parseInt(filescanner.next());
        pointswon = Integer.parseInt(filescanner.next());
        games_played = Integer.parseInt(filescanner.next());
        filescanner.close();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int heigth = (int)size.getHeight();
        new ZEICHENFENSTER("test", width, heigth, true, true);
        startcode = 1;
        Spielautomat spiel = new Spielautomat(width, heigth);
        spiel.zeichne();
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(" ");
        String command;
        while(true) {
            command = sc.nextLine();
            String[] commandOptions = command.split(" ");
            if(commandOptions.length == 0) commandOptions[0] = "";
            if(command.startsWith("rerun")) {
                spiel.spiele();
            } else if(commandOptions[0].equals("stop")) {
                sc.close();
                System.exit(1);
            } else if(commandOptions[0].startsWith("score")) {
                if(commandOptions.length == 1) commandOptions[1] = "";
                if(commandOptions[1].equals("add")) {

                } else if(commandOptions[1].equals("remove")) {

                } else if(commandOptions[1].equals("set")) {

                } else if(commandOptions[1].equals("reset")) {

                } else {
                    System.out.println("System Command score:");
                    System.out.println("score add <Integer>        | Fügt Score Punkte hinzu");
                    System.out.println("score remove <Integer>     | Entfernt Score Punkte");
                    System.out.println("score set <Integer>        | Setzt die Score Punkte auf einen absoluten Wert");
                    System.out.println("score reset                | Setzt die Score Punkte auf den Standardwert zurück");
                }
            } else {
                System.out.println("Unknown Command.");
                System.out.println("Try 'help' for a list of all available commands.");
            }
        }
    }
    
    public void zeichne()
    {
        spielwalze1.zeichne();
        spielwalze2.zeichne();
        spielwalze3.zeichne();
    } 
    
    public void aktualisiere(int z1Neu, int z2Neu, int z3Neu)
    {
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
    
    public void spiele() throws IOException
    {
        aktualisiere(zufall.nextInt(9), zufall.nextInt(9), zufall.nextInt(9));
        guthaben += guthaben();
        switch(guthaben()) {
            case 9: pointswon += 7;
            case 2: pointswon += 3;
            case -1: games_played += 1;
        }
        if(guthaben > highscore) highscore = guthaben;
        speichern("data/scores.txt", String.valueOf(guthaben) + " " + String.valueOf(highscore) + " " + String.valueOf(pointswon) + " " + String.valueOf(games_played));
    }
    
    public int guthaben()
    {
        if(z1 == z2 && z1 == z3) {
            return 9;
        } else if(z1 == z2 || z1 == z3 || z2 == z3) {
            return 2;
        } else return -1;
    }
    
    public static void speichern(String file, String value) throws IOException 
    {
        Writer w = new FileWriter(file);
        w.write(value);
        w.close();
    }
}