import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class EarthTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private JLabel label = new JLabel();
    private char[][] board = new char[3][3];
    private boolean player1 = true;
    private boolean gameEnded = false;
    private String p1;
    private String p2;
    private ImageIcon earth;
    private ImageIcon tree;


    public EarthTacToeGUI() {

        setTitle("Tic Tac Toe Earth Day REMIX");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
             // Initialize earth and tree icons
    try {
        URL earthUrl = getClass().getResource("resources/Earth.png");
        URL treeUrl = getClass().getResource("resources/Tree.jpg");
        earth = new ImageIcon(ImageIO.read(earthUrl));
        tree = new ImageIcon(ImageIO.read(treeUrl));
    } catch (IOException e) {
        e.printStackTrace();
    }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.blue);
        buttonPanel.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("-");
                final int finalRow = row;
                final int finalCol = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(finalRow, finalCol);
                    }
                });
                buttonPanel.add(buttons[row][col]);
            }
        }
        add(buttonPanel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        setVisible(true);
        initGame();
    }

   public void initGame() {
    // Add dashes
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            board[i][j] = '-';
        }
    }

    // Player Names
    p1 = JOptionPane.showInputDialog("Earthling 1, Name: ");
    p2 = JOptionPane.showInputDialog("Earthling 2, Name: ");

    // Set the initial players' turn label font size
    label.setFont(new Font("Arial", Font.BOLD, 36));
    label.setForeground(Color.GREEN);
    label.setText(p1 + "'s Turn (E)");
}

    public void buttonClicked(int row, int col) {
        if (gameEnded) {
            JOptionPane.showMessageDialog(this, "The game has ended. Please start a new game.");
            return;
        }

        if (board[row][col] != '-') {
            JOptionPane.showMessageDialog(this, "Someone has already made a move at this position. Try again.");
            return;
        }

        char c = '-';
        if (player1) {
            c = 'E';
            label.setText(p2 + "'s Turn (T)");
            label.setForeground(Color.GREEN);
            board[row][col] = c;
            buttons[row][col].setForeground(Color.BLUE);
            buttons[row][col].setText("");
            buttons[row][col].setIcon(earth);
            validate();
        } else {
            c = 'T';
            label.setText(p1 + "'s Turn (E)");
            label.setForeground(Color.BLUE);
            board[row][col] = c;
            buttons[row][col].setForeground(Color.GREEN);
            buttons[row][col].setText("");
            buttons[row][col].setIcon(tree);
            validate();

        }




 if (playerHasWon(board) == 'E') {
            label.setText(p1 + " HAS WON!");
            label.setFont(new Font("Arial", Font.BOLD, 36)); 
            gameEnded = true;
        } else if (playerHasWon(board) == 'T') {
            label.setText(p2 + " HAS WON!");
            label.setFont(new Font("Arial", Font.BOLD, 36)); 
            gameEnded = true;
        } else if (boardIsFull(board)) {
            label.setText("OHHHHHHHHHHH It's a tie! OHHHHHHHHHHHH");
            label.setFont(new Font("Arial", Font.BOLD, 36)); 
            gameEnded = true;
        }
    
        player1 = !player1;
    }

    public static void main(String[] args) {
        new EarthTacToeGUI();
    }

    public char playerHasWon(char[][] board) {
    // Check rows
    for (int row = 0; row < 3; row++) {
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '-') {
            return board[row][0];
        }
    }

    // Check columns
    for (int col = 0; col < 3; col++) {
        if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '-') {
            return board[0][col];
        }
    }

    // Check diagonals
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
        return board[0][0];
    }

    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
        return board[0][2];
    }

    // If no winner yet
    return '-';
}

public static boolean boardIsFull(char[][] board) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j] == '-') {
                return false;
            }
        }
    }
    return true;
} 
}
