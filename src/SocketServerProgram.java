import java.io.*;
import java.net.Socket;

public class SocketServerProgram implements Runnable {
    private Socket serverSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String clientMessage;

    public SocketServerProgram(Socket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.outputStream = serverSocket.getOutputStream();
        this.inputStream = serverSocket.getInputStream();
    }

    @Override
    public void run() {
        System.out.println("Server have got a new connection!");
        readClientMessage();
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sending massage to client...");
        writeMessageToClient();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readClientMessage() {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            clientMessage = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server already has got message: " + clientMessage);
    }

    private void writeMessageToClient() {
        PrintWriter out = new PrintWriter(outputStream, true);
        String messageOut;
        if (clientMessage.equals("")) {
            messageOut = "Your message is empty, is it ok?";
        } else {
            messageOut = "Hi, from server! Server has got your message: " + clientMessage;
        }
        out.println(messageOut);
        out.flush();
    }
}
