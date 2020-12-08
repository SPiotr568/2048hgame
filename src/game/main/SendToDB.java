package game.main;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendToDB extends Thread{
    private String nick;
    private String score;

    public SendToDB(String nick, String score) {
        this.nick = nick;
        this.score = score;
    }

    @Override
    public void run() {
        //sending data to database
        System.out.println("Sending data to database!");

        String hostname = "localhost";
        int port = 2761;

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            Date nowDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String date = sdf.format(nowDate);
            writer.println("PUT " + nick + " " + score + " " + date);
            String response = reader.readLine();
            if(response.equals("OK")){
                System.out.println("Server response OK");
            }
            else{
                System.out.println("Something goes wrong with sending data to db");
            }
            writer.println("STOP");
            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found... " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error... " + ex.getMessage());
        }
    }
}
