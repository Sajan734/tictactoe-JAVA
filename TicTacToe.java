import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
  int boardwidth = 400;
  int boardheight = 600;

  JFrame frame = new JFrame("Tic Tac Toe");
  JLabel textLabel = new JLabel();
  JPanel textPanel = new JPanel();

  TicTacToe() {
    frame.setVisible(true);
    frame.setSize(boardwidth, boardheight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    textLabel.setBackground(Color.darkGray);
    textLabel.setForeground(Color.white);
    textLabel.setFont(new Font("Arial", Font.BOLD, 30));
    textLabel.setHorizontalAlignment(JLabel.CENTER);
    textLabel.setText("Space Tac Toe");
    textLabel.setOpaque(true);

    textPanel.setLayout(new BorderLayout());
    textPanel.add(textLabel);
    frame.add(textPanel);

  }

}