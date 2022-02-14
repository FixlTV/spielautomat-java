public class AutomaticMode implements Runnable {
    Thread automatic;
    private Ampel ampel;

    public AutomaticMode(Ampel a) {
        ampel = a;
    }

    public void start() {
        if(automatic == null) {
            automatic = new Thread(this);
            automatic.start();
        }
    }

    @Override
    public void run() {
        while(ampel.running) {
            ampel.setStatus(ampel.status + 1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            if(ampel.status == 1 || ampel.status == 3) {
                try {
                    Thread.sleep(ampel.time * 1000 - 3000);
                } catch (InterruptedException e) {}
            }
        } 
        return;
    }
}
