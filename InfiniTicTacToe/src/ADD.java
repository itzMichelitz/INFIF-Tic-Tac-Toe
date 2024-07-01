import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ADD {
    private JFrame frame;
    private JPanel cardPanel;
    private JPanel gamePanel;
    private JPanel startMenu;
    private JPanel player1Panel;
    private JPanel player2Panel;
    private JButton[] buttons = new JButton[9];
    private JButton startButton;
    private JButton exitButton;
    private boolean xTurn = true;
    private ArrayList<Integer> memory = new ArrayList<>();

    private ImageIcon bg;
    private JLabel myLabel;

    ImageIcon dark = new ImageIcon(getClass().getResource("assets/LIGHT_OFF.png"));
    ImageIcon p1 = new ImageIcon(getClass().getResource("assets/LIGHT_RED.png"));
    ImageIcon p2 = new ImageIcon(getClass().getResource("assets/LIGHT_BLUE.png"));

    public ADD() {
        frame = new JFrame("INFINITic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize panels
        cardPanel = new JPanel(new CardLayout()); // Initialize cardPanel with CardLayout

        startMenu = new JPanel();
        startMenu.setLayout(new BorderLayout(5, 0));

        gamePanel = new JPanel(new GridLayout(3, 3, 5, 5));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        player1Panel = new JPanel();
        player1Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        player1Panel.add(new JLabel("Player 1"));

        player2Panel = new JPanel();
        player2Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        player2Panel.add(new JLabel("Player 2"));

        // Initialize buttons and components
        bg = new ImageIcon(getClass().getResource("assets/INFINI_MENU.png"));
        myLabel = new JLabel(bg);
        myLabel.setSize(370, 430);
        startMenu.add(myLabel, BorderLayout.CENTER);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(e -> showCard("GamePanel"));
        startMenu.add(startButton, BorderLayout.NORTH);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.addActionListener(e -> System.exit(0));
        startMenu.add(exitButton, BorderLayout.SOUTH);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(dark);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 0));
            buttons[i].addActionListener(new ButtonClickListener(i));
            gamePanel.add(buttons[i]);
        }

        // Add components to cardPanel
        cardPanel.add(startMenu, "StartMenu");
        cardPanel.add(gamePanel, "GamePanel");

        // Add components to frame
        frame.add(player1Panel, BorderLayout.WEST);
        frame.add(player2Panel, BorderLayout.EAST);
        frame.add(cardPanel, BorderLayout.CENTER); // Add cardPanel to the center of the frame

        // Show start menu first
        showCard("StartMenu");

        frame.setSize(700, 700);
        frame.setVisible(true);
    }

    private void showCard(String card) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, card);
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                resetGame();
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                resetGame();
                return;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[0].getText() + " wins!");
            resetGame();
            return;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[2].getText() + " wins!");
            resetGame();
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            resetGame();
        }
    }

    public void resetGame() {
        int result = JOptionPane.showConfirmDialog(null,"Continue?","Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
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
        }else {
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



