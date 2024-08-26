import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    int canvasWidth = 600;
    int canvasHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe");

    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel canvasPanel = new JPanel();

    JButton[][] canvas = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer =  playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(canvasWidth, canvasHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Ariel", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        canvasPanel.setLayout(new GridLayout(3,3));
        canvasPanel.setBackground(Color.darkGray);
        frame.add(canvasPanel);

        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                canvas[r][c] = tile;
                canvasPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Ariel", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();

                            if(!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (canvas[r][0].getText() == "") continue;
            if (canvas[r][0].getText() == canvas[r][1].getText() &&
                    canvas[r][1].getText() == canvas[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(canvas[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for(int c = 0; c < 3; c++) {
            if(canvas[0][c].getText() == "") continue;
            if(canvas[0][c].getText() == canvas[1][c].getText() &&
                canvas[1][c].getText() == canvas[2][c].getText()) {
                for(int i = 0; i < 3; i++) {
                    setWinner(canvas[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonally - top left to bottom right
        if(canvas[0][0].getText() == canvas[1][1].getText() &&
                canvas[1][1].getText() == canvas[2][2].getText() &&
                canvas[0][0].getText() != "") {
            for(int i = 0; i < 3; i++) {
                setWinner(canvas[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally - top right to bottom left
        if(canvas[0][2].getText() == canvas[1][1].getText() &&
                canvas[1][1].getText() == canvas[2][0].getText() &&
                canvas[0][2].getText() != "") {
            setWinner(canvas[0][2]);
            setWinner(canvas[1][1]);
            setWinner(canvas[2][0]);

            gameOver = true;
            return;
        }

        if(turns == 9) {
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    setTie(canvas[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("It's a tie!");
    }
}
