// START MENU

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class START extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel startMenu;
    private JButton startButton;
    private JButton exitButton;
    private ImageIcon bg;
    private JLabel myLabel;


    START(){

        frame = new JFrame("Infini-TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        startMenu = new JPanel();
        startMenu.setBorder(BorderFactory.createEmptyBorder(275, 50, 50, 50));
        startMenu.setLayout(new BorderLayout(5, 0));

        bg = new ImageIcon(getClass().getResource("assets/INFINI_MENU.png"));
        myLabel = new JLabel(bg);
        myLabel.setSize(370,430);
        startMenu.add(myLabel);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(this);
        startMenu.add(startButton, BorderLayout.BEFORE_LINE_BEGINS);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.addActionListener(this);
        startMenu.add(exitButton, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){

        }else if (e.getSource() == exitButton){
            System.exit(0);
        }

    }


}
