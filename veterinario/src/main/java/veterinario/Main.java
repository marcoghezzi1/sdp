package veterinario;

import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static void main(String arg[]) throws Exception {
        Random r = new Random();
        ArrayList<Thread> threads = new ArrayList<Thread>();
        WaitingRoom waiting = new WaitingRoom(4);

        //create some threads
        for (int i=0; i<10; i++) {
            Animal a = new Animal(r.nextInt(2), i, waiting);
            threads.add(a);
        }

        //start all the threads
        for (Thread t: threads) {
            t.start();
        }
        //System.out.println(r.nextInt(2));
    }
}

