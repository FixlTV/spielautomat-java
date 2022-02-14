public class SPIELWALZE {
    private VOLLKREIS lampe;
    private KASTEN rahmen;
    private int farbnummer;

    public SPIELWALZE(int linksStart, int obenStart, int breite, int farbnrStart) {
        lampe = new VOLLKREIS(linksStart + breite / 2, obenStart + breite / 2, breite / 3, farbnrStart);
        rahmen = new KASTEN(linksStart, obenStart, breite, breite);
        farbnummer = farbnrStart;
    }

    public void zeichne() {
        rahmen.zeichne();
        lampe.zeichne();
    }

    public void faerbeUm(int neueFarbe) {
        farbnummer = neueFarbe;
        lampe.setzeFarbe(farbnummer);
        zeichne();
    }
}
