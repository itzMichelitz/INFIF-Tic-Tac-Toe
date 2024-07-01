import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test implements ActionListener {
    private JFrame frame;
    private JPanel cardPanel;
    private JPanel gamePanel;
    private JPanel startMenu;
    private JButton[] buttons = new JButton[9];
    private JButton startButton;
    private JButton exitButton;



    public test() {
        frame = new JFrame("Infini-TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        // Initialize CardLayout container
        cardPanel = new JPanel(new CardLayout());

        // Initialize start menu
        startMenu = new JPanel();
        startMenu.setLayout(new GridLayout(2, 1, 10, 10));
        startMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(this);
        startMenu.add(startButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.addActionListener(this);
        startMenu.add(exitButton);

        // Initialize game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60)); // Text is for winner checking
            buttons[i].addActionListener(this);
            gamePanel.add(buttons[i]);
        }

        // Add panels to card panel
        cardPanel.add(startMenu, "StartMenu");
        cardPanel.add(gamePanel, "GamePanel");

        // Add card panel to frame
        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);

        // Show start menu first
        showCard("StartMenu");
    }

    private void showCard(String card) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, card);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Switch to game panel
            showCard("GamePanel");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else {
            // Handle game button clicks
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    buttons[i].setText("X"); // Example action
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}
