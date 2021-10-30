public class SPIELWALZE
{
    private VOLLKREIS lampe;
    private KASTEN rahmen;
    private int farbnummer;

    public SPIELWALZE(int linksStart, int obenStart, int breite, int farbnrStart)
    {
        lampe = new VOLLKREIS(linksStart + breite / 2, obenStart + breite / 2, breite / 2 / 8 * 7, farbnrStart);
        rahmen = new KASTEN(linksStart, obenStart, breite, breite);
        farbnummer = farbnrStart;
    }
    
    public void zeichne()
    {
        rahmen.zeichne();
        lampe.zeichne();
    }

    public void faerbeUm(int neueFarbe)
    {
        farbnummer = neueFarbe;
        lampe.setzeFarbe(farbnummer);
        zeichne();
    }

    public static void main(String[] args) {
        SPIELWALZE s1 = new SPIELWALZE(10, 10, 80, 7);
        SPIELWALZE s2 = new SPIELWALZE(90, 10, 80, 7);
        SPIELWALZE s3 = new SPIELWALZE(170, 10, 80, 7);
        s1.zeichne();
        s2.zeichne();
        s3.zeichne();
    }
    
}
