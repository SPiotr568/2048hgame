package game.main;

import java.util.concurrent.CountDownLatch;

public class GetFromDB implements Runnable{
    private final CountDownLatch start;
    private final CountDownLatch stop;
    private int i;
    private String[][] tab;

    public GetFromDB(CountDownLatch start, CountDownLatch stop, int i, String[][] tab) {
        this.start = start;
        this.stop = stop;
        this.i = i;
        this.tab = tab;
    }

    @Override
    public void run() {
        //getting data from database
        try {
            start.await();
        } catch (InterruptedException e) {
            System.out.println("Error GetFromDB");
            return;
        }
        System.out.println("Getting data from database!");
        for(int x=0;x<3;x++){
            tab[i][x]="a";
        }
        stop.countDown();
    }
}
