package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
    private static int port = 6060;
    private int id;

    Client(int id){
        this.id = id;
    }

    public static void main(String args[]) throws Exception {
        for(int i=1;i<=10;i++) {
            new Client(i).start();
        }
    }

    @Override
    public void run() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket client = new Socket(inetAddress.getHostName(),port);
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            if(id % 2 == 0) out.writeUTF("connection");
            else out.writeUTF("submit");
            System.out.println(in.readUTF());
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}