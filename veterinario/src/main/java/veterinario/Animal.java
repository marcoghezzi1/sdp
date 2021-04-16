package veterinario;

import java.util.Random;

public class Animal extends Thread {
    private final int animale;
    private final int id;
    private final WaitingRoom w;
    private final Random ran = new Random();

    Animal(int r, int i, WaitingRoom waiting) {
        animale = r;
        id = i;
        w = waiting;
    }

    public void run() {
        wasteSomeTime(); //Simulate the thread is doing something else
        if (animale == 0)
            System.out.println("Gatto " + id + " vuole entrare in sala d'attesa");
        else
            System.out.println("Cane " + id + " vuole entrare in sala d'attesa");
        w.enterRoom(animale);
        System.out.println("L'animale " + id + " è entrato in sala d'attesa!");
        wasteSomeTime(); //it takes some times to compleate the work in the critical region
        System.out.println("L'animale " + id + " è uscito dalla sala d'attesa");
        w.exitRoom();
    }//end run

    private void wasteSomeTime() {
        int seconds = ran.nextInt(10) + 1;
        try {Thread.sleep(seconds*1000);}
        catch(Exception ex) {ex.printStackTrace();}
    }
} //end class

