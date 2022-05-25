import javax.swing.*;
import java.net.*;
import java.io.*;


public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("Localhost", 4999);
            String data = JOptionPane.showInputDialog("Write your data");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(data);
            printWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
