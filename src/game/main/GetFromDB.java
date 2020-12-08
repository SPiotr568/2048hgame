package game.main;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class GetFromDB implements Runnable{
    private final CountDownLatch start;
    private final CountDownLatch stop;
    private int i;
    private String[][] tab;
    private Socket socket;
    private PrintWriter writer;

    public GetFromDB(CountDownLatch start, CountDownLatch stop, int i, String[][] tab, Socket socket, PrintWriter writer) {
        this.start = start;
        this.stop = stop;
        this.i = i;
        this.tab = tab;
        this.socket = socket;
        this.writer = writer;
    }

    @Override
    public void run() {
        System.out.println("start " + i);
        writer.println("GET " + i);
        InputStream input = null;
        try {
            input = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String a = null;
        try {
            a = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(a);
        System.out.println("stop " + i);


        //getting data from database
        try {
            start.await();
        } catch (InterruptedException e) {
            System.out.println("Error GetFromDB");
            return;
        }
        System.out.println("Getting data from database!");
        for(int x=0;x<3;x++){
            tab[i][x]=a;
        }
        stop.countDown();
    }
}
