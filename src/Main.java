import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        new Thread(new Connection()).start();

        try (ServerSocket serverSocket = new ServerSocket(9999)) {

            System.out.println("Server start and waiting for connection...");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new SocketServerProgram(socket)).start();
            }
        }
    }
}

