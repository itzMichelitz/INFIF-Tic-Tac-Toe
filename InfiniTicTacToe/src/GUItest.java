// Code for GUI ONLY, Work in progress


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUItest implements ActionListener {
    private JFrame frame;
    private JPanel cardPanel;
    private JPanel gamePanel;
    private JPanel startMenu;
    private JButton[] buttons = new JButton[9];
    private JButton startButton;
    private JButton exitButton;
    private String P1,P2;


    private ImageIcon bg;
    private JLabel myLabel;


    public GUItest() {
        frame = new JFrame("Infini-TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());


        // Initialize CardLayout container
        cardPanel = new JPanel(new CardLayout());


        // Initialize start menu

        startMenu = new JPanel();
        startMenu.setBorder(BorderFactory.createEmptyBorder(275, 50, 50, 50));
        startMenu.setLayout(new BorderLayout(5, 0));

        bg = new ImageIcon(getClass().getResource("assets/INFINI_MENU.png"));
        myLabel = new JLabel(bg);
        myLabel.setSize(370,430);
        startMenu.add(myLabel);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(e -> showCard("GamePanel"));
        startMenu.add(startButton, BorderLayout.BEFORE_LINE_BEGINS);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.addActionListener(e -> System.exit(0));
        startMenu.add(exitButton, BorderLayout.CENTER);

        // Initialize game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 3));
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
            P1 = JOptionPane.showInputDialog("Player 1(X):");
            P2 = JOptionPane.showInputDialog("Player 2(O):");
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
        new GUItest();
    }
}
