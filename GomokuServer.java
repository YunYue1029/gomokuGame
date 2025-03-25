
import java.io.*;
import java.net.*;

public class GomokuServer {
    private ServerSocket serverSocket;
    private Socket player1 = null;
    private Socket player2 = null;

    public GomokuServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
    }

    public void listen() {
        try {
            System.out.println("Waiting for players...");
            player1 = serverSocket.accept();
            System.out.println("Player 1 connected.");

            player2 = serverSocket.accept();
            System.out.println("Player 2 connected.");

            new PlayerHandler(player1, player2).start();
            new PlayerHandler(player2, player1).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private class PlayerHandler extends Thread {
        private Socket playerSocket;
        private Socket opponentSocket;
        private BufferedReader in;
        private PrintWriter out;

        public PlayerHandler(Socket playerSocket, Socket opponentSocket) {
            this.playerSocket = playerSocket;
            this.opponentSocket = opponentSocket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
                out = new PrintWriter(opponentSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Player moved: " + inputLine); // 打印玩家移動信息
                    out.println(inputLine); // 轉發移動信息給對手
                    

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    playerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GomokuServer server = new GomokuServer(12346);
        server.listen();
    }
}
