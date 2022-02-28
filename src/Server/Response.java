package Server;

import java.io.*;
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


    /**
     * 传入Socket，与客户端进行交互，处理客户端发来的信号
     * @param socket
     */
    public void slove(Socket socket) {
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String query = null;
            do {
                query = in.readUTF();
                if(query.equals("submit")) submit(socket);
            } while(!query.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 传输图片
     * @param socket
     */
    private void submit(Socket socket) {
        DataInputStream in = null;
        DataOutputStream out = null;
        FileOutputStream fileOutputStream = null;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            //        String path = File.separator + "root" + File.separator + "java" + File.separator
//                + "CS_demo1_tmp" + socket.getInetAddress().toString() + "_" + in.readUTF();
            // 存放的位置
            String path = "G:" + File.separator + socket.getInetAddress().toString() + "_" + in.readUTF();
            File file = new File(path);
            File fileParent = file.getParentFile();
            // 判断父目录是否存在，不存在则创建
            if(!fileParent.exists()) {
                fileParent.mkdirs();
            }
            // 判断文件是否存在，不存在则创建
            if(!file.exists()) {
                file.createNewFile();
            }
            // 向文件里面输入内容
            fileOutputStream = new FileOutputStream(file);
            char b;
            do {
                b = in.readChar();
                // 只要低8位
                if((int) b < 256) fileOutputStream.write(b & 0xff);
            } while((int) b < 256);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭IO流
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
