package Server;

import java.net.Socket;

public class Interaction implements Runnable {
    private Socket socket;
    private Thread t;

    public Interaction(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Response response = Response.getInstance();
        try {
            response.slove(this.socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if(t == null) {
            t = new Thread(this);
            t.start();
        }
    }

}
