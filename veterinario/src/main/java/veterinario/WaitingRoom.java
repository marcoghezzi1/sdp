package veterinario;//Classe che implementa tramite wait e notify un Semaforo.
//Se non vi ricordate cosa sia un semaforo, google is your best friend.

public class WaitingRoom {
    private final int maxCani; //numero massimo di cani
    private int cani; //conteggio dei cani
    private int gatti; //conteggio dei gatti

    WaitingRoom(int max) {
        maxCani = max;
        cani = 0;
        gatti = 0;
    }

    public synchronized void enterRoom(int animale) {
        System.out.println("" + cani + " cani in sala d'attesa...");
        System.out.println("" + gatti + " gatti in sala d'attesa...");
        //quando abbiamo raggiunto il numero massimo di thread, chi vuole entrare aspetta
        while (cani > maxCani) {
            try {this.wait();
            }
            catch(InterruptedException ie) {ie.printStackTrace();}
        }
        if (animale == 1)
            cani++;
        else
            gatti++;
    }

    public synchronized void exitRoom() {
        cani--;
        //quando un thread esce dall'area critica, sveglia qualcuno in attesa di entrare (se presente)
        this.notify();
    }
}

