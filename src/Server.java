import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4999);
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String data = bufferedReader.readLine();
            System.out.println(data);
            if(data.equals("run")) {
                SQLiteTest sqLiteTest = new SQLiteTest();
                new BouncingSprites(sqLiteTest).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
