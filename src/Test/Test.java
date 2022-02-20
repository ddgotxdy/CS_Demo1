package Test;

import java.io.File;
import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        File file = null;
        do {
            String path = scanner.nextLine();
            file = new File(path);
            if(file.isFile()) break;
            else System.out.println("Error File name or is directory");
        }while(true);
    }
}
