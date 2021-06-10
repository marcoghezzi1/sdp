package NetworkTopology;

import REST.Drone;

import java.util.Scanner;

public class QuitDroneThread extends Thread {
    public QuitDroneThread() {
    }

    @Override
    public void run() {
        /*
        condizioni di chiusura del drone:
        - quit da console
        - batteria è minore del 15%

         */
        Scanner in = new Scanner(System.in);
        String quit = "";
        do {
            quit = in.nextLine();
        }
        while (!quit.equals("quit"));
    }
}
