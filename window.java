import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class window extends JFrame {
    private GomokuGame game;
    private JButton[][] buttons;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean myTurn = true; // 初始設置為 true 或 false，視乎玩家是第一個還是第二個連接的
    private int WIN=0;

    public window() {
        game = new GomokuGame();
        buttons = new JButton[game.SIZE][game.SIZE];
        initUI();
        connectToServer();
        startGameListener();
    }

    private void connectToServer() {
        try {
            clientSocket = new Socket("localhost", 12346); // Replace with your server's IP and port
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        setLayout(new GridLayout(game.SIZE, game.SIZE));
        for (int i = 0; i < game.SIZE; i++) {
            for (int j = 0; j < game.SIZE; j++) {
                buttons[i][j] = new JButton();
                JButton button = buttons[i][j];
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    if (myTurn && button.getText().equals("")) {
                        button.setText("O"); // 或 "O"，視乎玩家
                        game.setBoard(finalI, finalJ, 2); // 或 2，視乎玩家
                        myTurn = false;
                        if(game.checkWin(finalI,finalJ))
                        {
                            WIN=1;
                        }
                        out.println(finalI + "," + finalJ+","+WIN); // Send move to server
                        if(WIN==1)
                        { 
                            System.out.println("YOU WIN !");
                            JOptionPane.showMessageDialog(null, "player WIN !");
                            System.exit(0);
                        }
                    }
                });
                add(button);
            }
        }
    }

    private void startGameListener() {
        new Thread(() -> {
            String response;
            try {
                while ((response = in.readLine()) != null) {
                    String[] parts = response.split(",");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    int win = Integer.parseInt(parts[2]);
                    if(win!=0)
                    {
                        System.out.println("player"+win+" WIN !");
                        JOptionPane.showMessageDialog(null, "player"+win+" WIN !");
                        System.exit(0);
                    }
                    SwingUtilities.invokeLater(() -> {
                        buttons[x][y].setText("X"); // 或 "X"，視乎對手
                        game.setBoard(x, y, 1); // 或 1，視乎對手
                        myTurn = true;
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            window window = new window();
            window.setPreferredSize(new Dimension(600, 600));
            window.pack();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
