package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private ServerSocket serverSocket;

    Server(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while(true) {
            try {
                Socket server = serverSocket.accept();
                new Interaction(server).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        Server server = new Server(6060);
        server.run();
    }

}