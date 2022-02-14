public class VOLLKREIS {
    private int xMitte, yMitte, radius, farbnr;

    public VOLLKREIS(int xStart, int yStart, int radiusStart, int farbnrStart) {
        xMitte = xStart;
        yMitte = yStart;
        radius = radiusStart;
        farbnr = farbnrStart;
    }

    public void setzeMitte(int xNeu, int yNeu) {
        xMitte = xNeu;
        yMitte = yNeu;
    }

    public void zeichne() {
        ZEICHENFENSTER.gibFenster().fuelleKreis(xMitte, yMitte, radius, farbnr);
    }

    public void setzeFarbe(int farbnrNeu) {
        farbnr = farbnrNeu;
    }

    public void verschiebe(int nachRechts, int nachUnten) {
        xMitte = xMitte + nachRechts;
        yMitte = yMitte + nachUnten;
    }
}