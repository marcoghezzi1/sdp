package veterinario;//Classe che implementa tramite wait e notify un Semaforo.
//Se non vi ricordate cosa sia un semaforo, google is your best friend.

public class WaitingRoom {
    private final int maxCani; //numero massimo di cani
    private int cani; //conteggio dei cani
    private int gatti; //conteggio dei gatti

    public WaitingRoom(int max_dogs) {
        maxCani = max_dogs;
        cani = 0;
        gatti = 0;
    }

    public synchronized void enterRoom(int animale) {
        System.out.println("" + cani + " cani in sala d'attesa...");
        System.out.println("" + gatti + " gatti in sala d'attesa...");

        //quando abbiamo raggiunto il numero massimo di thread, chi vuole entrare aspetta
        while ((isCat(animale) && (cani>0 || gatti >0) ) || (isDog(animale) && (cani>=maxCani || gatti>0) ) ) {
            try {
                this.wait();
            }
            catch(InterruptedException ie) {ie.printStackTrace();}
        }
        // enter into room allowed

        if (isDog(animale))
            cani++;
        if (isCat(animale))
            gatti++;
    }

    public synchronized void exitRoom(int animale) {
        if (isDog(animale))
            cani--;
        if (isCat(animale))
            gatti--;
        //quando un thread esce dall'area critica, sveglia qualcuno in attesa di entrare (se presente)
        this.notify();
    }

    private boolean isDog(int animal){
        return animal==0;
    }
    private boolean isCat(int animal){
        return animal==1;
    }

}

