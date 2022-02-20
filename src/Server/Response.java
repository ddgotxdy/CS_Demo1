package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Response {
    // 单例实现
    private static class ResponseSingle {
        private static final Response response = new Response();
    }
    private Response(){}
    public static final Response getInstance() {
        return ResponseSingle.response;
    }

    public void slove(Socket socket) throws Exception{
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String query = in.readUTF();
        if(query.equals("submit")) submit(in, out);
        else if(query.equals("connection")) connection(in, out);
        else {
            out.writeUTF("Error Comand");
        }
        socket.close();
    }

    private void submit(DataInputStream in, DataOutputStream out) throws Exception {
        out.writeUTF("submit");
    }

    private void connection(DataInputStream in, DataOutputStream out) throws Exception {
        out.writeUTF("connection");
    }

}
