package veterinario;

import java.util.Random;

public class Animal extends Thread {
    private final int animale;
    private final int id;
    private final WaitingRoom waitingRoom;
    private final Random ran = new Random();

    Animal(int animale, int idThread, WaitingRoom waiting) {
        this.animale = animale;
        id = idThread;
        waitingRoom = waiting;
    }

    public void run() {
        wasteSomeTime(); //Simulate the thread is doing something else
        String tipo = "";
        if (isCat(animale)) {
            System.out.println("Gatto " + id + " vuole entrare in sala d'attesa");
            tipo = "gatto";
        }
        if (isDog(animale)){
            System.out.println("Cane " + id + " vuole entrare in sala d'attesa");
            tipo = "cane";
        }


        waitingRoom.enterRoom(animale);
        System.out.println("L'animale " + id + " ("+tipo+") "+"è entrato in sala d'attesa!");

        wasteSomeTime(); //it takes some times to complete the work in the critical region

        System.out.println("L'animale " + id + " ("+tipo+") "+ " è uscito dalla sala d'attesa");
        waitingRoom.exitRoom(animale);
    }//end run

    private void wasteSomeTime() {
        int seconds = ran.nextInt(10) + 1;
        try {Thread.sleep(seconds*1000);}
        catch(Exception ex) {ex.printStackTrace();}
    }
    private boolean isDog(int animal){
        return animal==0;
    }
    private boolean isCat(int animal){
        return animal==1;
    }
} //end class

