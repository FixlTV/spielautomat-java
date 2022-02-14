public class Ampel {

    public int status, time;
    private SPIELWALZE o, m, u;
    public boolean running = false;
    private AutomaticMode thread;

    public static void main(String[] args) {
        new Ampel(10, 10, 10, 200);
    }

    public Ampel(int _time, int x, int y, int width) {
        status = 0;
        time = _time;
        if(ZEICHENFENSTER.singleton == null) new ZEICHENFENSTER("Ampel", 2 * x + width, 2 * y + width * 3, true, false);
        o = new SPIELWALZE(x, y, width, 7);
        m = new SPIELWALZE(x, y + width, width, 7);
        u = new SPIELWALZE(x, y + 2 * width, width, 7);
        thread = new AutomaticMode(this);
        zeichne();
        startAutomaticMode();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {}
        endAutomaticMode();
        setStatus(0);
    }

    public void zeichne() {
        o.zeichne();
        m.zeichne();
        u.zeichne();
    }

    public void setStatus(int _status) {
        status = _status;
        if(status == 5) status = 1;
        switch(status) {
            case 0: o.faerbeUm(7); m.faerbeUm(7); u.faerbeUm(7); break;
            case 1: o.faerbeUm(4); m.faerbeUm(7); u.faerbeUm(7); break;
            case 2: o.faerbeUm(4); m.faerbeUm(6); u.faerbeUm(7); break;
            case 3: o.faerbeUm(7); m.faerbeUm(7); u.faerbeUm(2); break;
            case 4: o.faerbeUm(7); m.faerbeUm(6); u.faerbeUm(7); break;
        }
        zeichne();
    }

    public void startAutomaticMode() {
        if(running) return;
        running = true;
        thread.start();
        return;
    }
    
    public void endAutomaticMode() {
        if(!running) return;
        running = false;
    }
}