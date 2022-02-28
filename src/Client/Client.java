package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    private static int port = 6060;
    private static Scanner scanner = new Scanner(System.in);
    // 访问的ip地址
//    private static String Servername = "182.92.216.59";

    public static void main(String args[]) {
        InetAddress inetAddress = null;
        Socket client = null;
        FileInputStream fileInputStream = null;
        try {
//            本地测试
            inetAddress = InetAddress.getLocalHost();
            client = new Socket(inetAddress.getHostName(),port);
//            远程服务器测试，需要配置内网穿透
//            Socket client = new Socket(Servername,port);
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            File file = null;
            String path = null;
            do {
                System.out.print("输入正确的图片路径（exit退出）：");
                // 输入文件路径
                path = scanner.nextLine();
                file = new File(path);
                // 是文件才处理，是目录等不处理
                if(file.isFile()) {
                    fileInputStream = new FileInputStream(file);
                    out.writeUTF("submit");
                    out.writeUTF(file.getName());
                    // 创建文件IO流进行字节传输
                    int b;
                    do {
                        b = fileInputStream.read();
                        // 为了处理到文件末尾，直接传int
                        out.writeInt(b);
                    }while(b != -1);
                    System.out.println("传输成功");
                } else if(!path.equals("exit")) {
                    System.out.println("Error File name or is directory");
                }
            }while(!path.equals("exit"));
            out.writeUTF("exit");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}