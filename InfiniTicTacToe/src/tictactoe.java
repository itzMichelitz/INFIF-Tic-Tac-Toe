// INFINI Tic-Tac-Toe
// A game by Michel Eirene Catienza and Amiel Ed Faller

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class tictactoe {
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;
    private ArrayList<Integer> memory= new ArrayList<>();

    private String P1, P2, score;
    private int scoreP1 = 0, scoreP2 = 0;

    ImageIcon dark = new ImageIcon(getClass().getResource("assets/LIGHT_OFF.png"));
    ImageIcon p1 = new ImageIcon(getClass().getResource("assets/LIGHT_RED.png"));
    ImageIcon p2 = new ImageIcon(getClass().getResource("assets/LIGHT_BLUE.png"));

    public tictactoe() {
        frame = new JFrame("INFINI Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        gamePanel = new JPanel();
        gamePanel.setSize(50,50);
        gamePanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 10, 100));

        // TicTacToe Play Area - 3x3 Grid
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(dark); // set to dark
            buttons[i].setSize(new Dimension(500, 500));
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 0));
            buttons[i].addActionListener(new ButtonClickListener(i));
            gamePanel.add(buttons[i]);
        }

        P1 = JOptionPane.showInputDialog(null, "Player 1:", "INFINI Tic-Tac-Toe", JOptionPane.PLAIN_MESSAGE);
        P2 = JOptionPane.showInputDialog(null, "Player 2:", "INFINI Tic-Tac-Toe", JOptionPane.PLAIN_MESSAGE);

        // Player Display
        player1Panel = new JPanel();
        player1Panel.setSize(200,50);
        player1Panel.setFont(new Font("Arial", Font.PLAIN, 70));
        player1Panel.add(new JLabel(P1));
        player1Panel.setLocation(20,550);

        player2Panel = new JPanel();
        player2Panel.setSize(500,50);
        player2Panel.setFont(new Font("Arial", Font.PLAIN, 70));
        player2Panel.add(new JLabel(P2));
        player2Panel.setLocation(300,550);

        frame.add(player1Panel);
        frame.add(player2Panel);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && !buttons[i].isEnabled()) {
                if (buttons[i].getText() == "X") {
                    JOptionPane.showMessageDialog(frame, P1 + " wins!");
                    scoreP1++;
                } else if (buttons[i].getText() == "O") {
                    JOptionPane.showMessageDialog(frame, P2 + " wins!");
                    scoreP2++;
                }
                resetGame();
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && !buttons[i].isEnabled()) {
                if (buttons[i].getText() == "X") {
                    JOptionPane.showMessageDialog(frame, P1 + " wins!");
                    scoreP1++;
                } else if (buttons[i].getText() == "O") {
                    JOptionPane.showMessageDialog(frame, P2 + " wins!");
                    scoreP2++;
                }
                resetGame();
                return;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
            if (buttons[0].getText() == "X") {
                JOptionPane.showMessageDialog(frame, P1 + " wins!");
                scoreP1++;
            } else if (buttons[0].getText() == "O") {
                JOptionPane.showMessageDialog(frame, P2 + " wins!");
                scoreP2++;
            }
            resetGame();
            return;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
            if (buttons[2].getText() == "X") {
                JOptionPane.showMessageDialog(frame, P1 + " wins!");
                scoreP1++;
            } else if (buttons[2].getText() == "O") {
                JOptionPane.showMessageDialog(frame, P2 + " wins!");
                scoreP2++;
            }
            resetGame();
            return;
        }

    }

    public void resetGame() {
        int result = JOptionPane.showConfirmDialog(null,"Continue?","INFINI Tic-Tac-Toe",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
            Icon dark = new ImageIcon(getClass().getResource("assets/LIGHT_OFF.png"));
            for (int i = 0; i < 9; i++) {
                buttons[i].setText("");
                buttons[i].setEnabled(true);
                buttons[i].setBackground(null);
                buttons[i].setIcon(dark);
                memory.clear();
            }
            xTurn = true;
        }else { // Saves score when game ends
            score = JOptionPane.showInputDialog(null, "Score: \n\n" + P1 + ": " + scoreP1 + "\n" +
                    P2 + ": " + scoreP2 + "\n\nFile name:", "INFINI Tic-Tac-Toe", JOptionPane.PLAIN_MESSAGE);
            File file = new File(score);
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("Score:\n" + P1 + ": " + scoreP1 + "\n" + P2 + ": " + scoreP2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new tictactoe();
    }

    private class ButtonClickListener implements ActionListener {
        private int index;

        public ButtonClickListener(int index) {
            this.index = index;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button click
            JButton button = (JButton) e.getSource();

            // Change the appearance of the button depending on the current turn.
            if (xTurn) {
                button.setText("X");
                button.setDisabledIcon(p1);
                button.setIcon(p1);
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setVerticalTextPosition(JButton.CENTER);
            } else {
                button.setText("O");
                button.setDisabledIcon(p2);
                button.setIcon(p2);
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setVerticalTextPosition(JButton.CENTER);
            }
            button.setEnabled(false);
            xTurn = !xTurn;

            if (memory.size() >= 6) {
                int first = memory.getFirst();
                buttons[first].setText("");
                buttons[first].setEnabled(true);
                buttons[first].setBackground(null);
                buttons[first].setIcon(dark);
                memory.removeFirst();
            }
            memory.add(index);

            // Check the winner ONLY after modifying button state memory.
            checkForWinner();

        }
    }
}



