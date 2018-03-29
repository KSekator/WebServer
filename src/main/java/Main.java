import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(88);
        while (true){
            Socket s = ss.accept();
            handleRequest(s);
        }

    }

    private static void handleRequest(Socket s) throws IOException {
        Scanner sc = new Scanner(s.getInputStream(), "US-ASCII");
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            System.out.println(line);
            if (line.isEmpty())
                break;
        }
        String reply = "HTTP/1.1 200 OK \r\n" + "Content-Type: text/plain; charset=UTF-8\r\n" + "\r\n" + "Привет, мир!";
        s.getOutputStream().write(reply.getBytes("UTF-8"));
        s.close();
    }
}
