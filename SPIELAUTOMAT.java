import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.io.*;

public class SPIELAUTOMAT
{
    private SPIELWALZE spielwalze1, spielwalze2, spielwalze3;
    private int z1, z2, z3;
    private Random zufall;
    private static int guthaben = 100;
    
    public SPIELAUTOMAT()
    {
        zufall = new Random();
     
        z1 = 7;
        z2 = 7;
        z3 = 7;
        spielwalze1 = new SPIELWALZE(10, 10, 50, z3);
        spielwalze2 = new SPIELWALZE(60, 10, 50, z2);
        spielwalze3 = new SPIELWALZE(110, 10, 50, z1);
    }

    public SPIELAUTOMAT(int breite, int höhe) {
        int länge = breite / 7;

        zufall = new Random();

        z1 = 7;
        z2 = 7;
        z3 = 7;
        spielwalze1 = new SPIELWALZE(breite / 2 - länge / 2 * 3, 10, länge, z3);
        spielwalze2 = new SPIELWALZE(breite / 2 - länge / 2, 10, länge, z2);
        spielwalze3 = new SPIELWALZE(breite / 2 + länge / 2, 10, länge, z1);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("data/score.txt");
        if(!file.exists()) {
            Writer w = new FileWriter("data/score.txt");
            w.write(String.valueOf(guthaben));
            w.close();
        }
        Scanner score = new Scanner(file);
        score.useDelimiter(" ");
        guthaben = Integer.parseInt(score.next());
        score.close();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int heigth = (int)size.getHeight();
        new ZEICHENFENSTER("ClydeStore®️ MoneyWaste™️", width, heigth, true, true);
        SPIELAUTOMAT spiel = new SPIELAUTOMAT(width, heigth);
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
        z1 = z1Neu;
        z2 = z2Neu;
        z3 = z3Neu;
        spielwalze1.faerbeUm(z1);
        spielwalze2.faerbeUm(z2);
        spielwalze3.faerbeUm(z3);
        this.zeichne();
    }
    
    public void spiele() throws IOException
    {
        aktualisiere(zufall.nextInt(9), zufall.nextInt(9), zufall.nextInt(9));
        guthaben += guthaben();
        Writer w = new FileWriter("data/score.txt");
        w.write(String.valueOf(guthaben));
        w.close();
    }
    
    
    
    
    
    
    
    
    
    // public void schreibeWerte()
    // {
        
    // }
    
    public int guthaben()
    {
        if(z1 == z2 && z1 == z3) {
            return 9;
        } else if(z1 == z2 || z1 == z3 || z2 == z3) {
            return 2;
        } else return -1;
    }
    
    // public int w (int g)
    // {
       
        
    // }   
            
    // public int f(int g)
    
    // {
        
         
    // }   
    
    
    
    // public void w1 (int g)
    // {
        
       
    // }   
    
    // public int f1(int g)
    
    // {
        
            
        
         
    // }   
            
    

}
