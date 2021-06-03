import producerConsumer.Producer;
import producerConsumer.Queue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class QueueSocket {

    public ArrayList<String> bufferFromClient = new ArrayList<String>();
    public ArrayList<String> bufferOtherClient = new ArrayList<String>();
    Socket conn;
    public QueueSocket(Socket clientSocket) throws IOException {
        conn = clientSocket;
        new ConsumerServer(conn, this).start();
        new ProducerServer(conn, this).start();

    }

    public synchronized void put(String message) {
        bufferFromClient.add(message);
        notify();
    }

    public synchronized void putFromOther(String message) {
        bufferOtherClient.add(message);
        notify();
    }

    public synchronized String take() {
        String messaggio = null;
        while(bufferOtherClient.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(bufferOtherClient.size()>0){
            messaggio = bufferOtherClient.get(0);
            bufferOtherClient.remove(0);
        }
        return messaggio;
    }

    public synchronized String takeFromThis() {
        String messaggio = null;
        while(bufferFromClient.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(bufferFromClient.size()>0){
            messaggio = bufferFromClient.get(0);
            bufferFromClient.remove(0);
        }
        return messaggio;
    }
    private static class ProducerServer extends Thread{
        private final DataOutputStream outToServer;
        private final QueueSocket queue;
        public ProducerServer(Socket s, QueueSocket q) throws IOException {
            outToServer = new DataOutputStream(s.getOutputStream());
            queue = q;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    outToServer.writeBytes(queue.takeFromThis()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ConsumerServer extends Thread {
        private final BufferedReader in;
        private final QueueSocket queue;
        public ConsumerServer(Socket conn, QueueSocket queueSocket) throws IOException {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            queue = queueSocket;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    queue.putFromOther(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
