package NetworkTopology;


import java.util.Scanner;

public class QuitDroneThread extends Thread {
    public QuitDroneThread() {
    }

    @Override
    public void run() {

        Scanner in = new Scanner(System.in);
        String quit = "";
        do {
            quit = in.nextLine();
        }
        while (!quit.equals("quit"));
    }
}
