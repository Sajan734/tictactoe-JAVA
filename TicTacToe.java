import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class TicTacToe {
  int boardwidth = 700;
  int boardheight = 800;

  JFrame frame = new JFrame("Space Tac Toe");
  JLabel textLabel = new JLabel();
  JLabel turnLabel = new JLabel();
  JPanel textPanel = new JPanel();
  JPanel turnPanel = new JPanel();
  JPanel boardPanel = new JPanel();
  JPanel spritePanel = new JPanel();

  JFrame starting_frame = new JFrame("Space Tac Toe Opening Menu");
  JLabel titleLabel = new JLabel();
  JPanel titlepanel = new JPanel();
  JPanel optionspanel = new JPanel();
  Color background_colour = Color.black;

  JButton[] options = new JButton[3];
  JButton exit = new JButton();
  JButton start = new JButton();
  JButton customize = new JButton();

  // Set Customize Frame
  String[] colours = { "Red", "Orange", "Yellow", "Green", "Purple", "Pink" };
  JFrame customize_frame = new JFrame("Customize your Avatar");
  JTextField player1Field = new JTextField("Player 1's Name");
  JComboBox<String> player1colour = new JComboBox<String>(colours);
  JPanel playerpanel = new JPanel();
  JTextField player2Field = new JTextField("Player 2's Name");
  JComboBox<String> player2colour = new JComboBox<String>(colours);
  JPanel player2panel = new JPanel();
  JButton customize_to_home = new JButton();
  Dimension size = customize_to_home.getPreferredSize();
  Insets insets = player2panel.getInsets();
  DefaultListCellRenderer listrender = new DefaultListCellRenderer();

  JTextField[] name_fields = { player1Field, player2Field };
  Dimension fields = new Dimension(300, 100);
  JComboBox[] colour_comboboxes = { player1colour, player2colour };

  JPanel userInfoPanel = new JPanel();
  JLabel[][] userInfo = new JLabel[2][2];

  JButton[][] board = new JButton[3][3];
  String playerX = "X";
  String playerO = "O";
  String currentPlayer = playerX;

  String usernameX = "Player 1";
  String usernameO = "Player 2";
  String currentPlayerUsername = usernameX;

  Integer scoreX = 0;
  Integer scoreO = 0;

  Color redC = Color.red;
  Color orangeC = Color.orange;
  Color yellowC = Color.yellow;
  Color greenC = Color.green;
  Color blueC = Color.blue;
  Color purpleC = Color.magenta;
  Color pinkC = Color.pink;

  // SET THE APPROPRIATE VARIABLE HERE
  Color colourX = greenC;
  Color colourO = redC;

  boolean gameOver = false;
  int turns = 0;

  TicTacToe() {

    // Starting Frame
    starting_frame.setVisible(true);
    starting_frame.setSize(boardwidth, boardheight);
    starting_frame.setLocationRelativeTo(null);
    starting_frame.setResizable(false);
    starting_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    starting_frame.setLayout(new BorderLayout());
    starting_frame.setBackground(background_colour);

    // Starting Frame Title
    titleLabel.setBackground(Color.darkGray);
    titleLabel.setForeground(Color.white);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    titleLabel.setText("Space Tac Toe Main Menu");
    titleLabel.setOpaque(true);

    titlepanel.setLayout(new BorderLayout());
    titlepanel.add(titleLabel);
    starting_frame.add(titlepanel, BorderLayout.NORTH);

    options[0] = start;
    options[1] = customize;
    options[2] = exit;

    for (int i = 0; i < 3; i++) {
      options[i].setBackground(Color.darkGray);
      options[i].setForeground(Color.white);
      options[i].setFont(new Font("Arial", Font.BOLD, 30));
      options[i].setSize(new Dimension(200, 100));
      options[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      options[i].setAlignmentY(Component.CENTER_ALIGNMENT);

    }
    ;

    start.setText("Start the Game");
    customize.setText("Customize");
    exit.setText("Exit the Game");

    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
        starting_frame.setVisible(false);
      }
    });
    customize.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        customize_frame.setVisible(true);
        starting_frame.setVisible(false);
      }
    });
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    optionspanel.setLayout(new BoxLayout(optionspanel, BoxLayout.PAGE_AXIS));
    optionspanel.setAlignmentY(Component.CENTER_ALIGNMENT);
    optionspanel.setBackground(background_colour);
    optionspanel.add(start);
    optionspanel.add(Box.createRigidArea(new Dimension(0, 20)));
    optionspanel.add(customize);
    optionspanel.add(Box.createRigidArea(new Dimension(0, 20)));
    optionspanel.add(exit);
    optionspanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(200, 50, 0, 50), new EtchedBorder()));

    starting_frame.add(optionspanel, BorderLayout.CENTER);

    // Customize Frame Customization
    customize_frame.setVisible(false);
    customize_frame.setSize(boardwidth, boardheight);
    customize_frame.setLocationRelativeTo(null);
    customize_frame.setResizable(false);
    customize_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    customize_frame.setLayout(new BoxLayout(customize_frame.getContentPane(), BoxLayout.Y_AXIS));
    customize_frame.setBackground(background_colour);

    for (int i = 0; i < 2; i++) {
      playerpanel.setLayout(new BoxLayout(playerpanel, BoxLayout.Y_AXIS));
      playerpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
      playerpanel.setAlignmentY(Component.CENTER_ALIGNMENT);
      playerpanel.setBackground(background_colour);
      name_fields[i].setFont(new Font("Arial", Font.BOLD, 30));
      name_fields[i].setPreferredSize(fields);
      name_fields[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      name_fields[i].setAlignmentY(Component.CENTER_ALIGNMENT);
      name_fields[i].setHorizontalAlignment(JTextField.CENTER);
      name_fields[i].setMargin(null);

      colour_comboboxes[i].setFont(new Font("Arial", Font.BOLD, 30));
      listrender.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
      colour_comboboxes[i].setRenderer(listrender);

      playerpanel.add(name_fields[i]);
      playerpanel.add(colour_comboboxes[i]);
      playerpanel.add(Box.createRigidArea(new Dimension(0, 40)));
    }
    ;
    customize_to_home.setText("<");
    customize_to_home.setFont(new Font("Arial", Font.BOLD, 40));
    customize_to_home.setBounds(25 + insets.left, 5 + insets.top,
        size.width, size.height);

    playerpanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(100, 50, 100, 50), new EtchedBorder()));

    customize_frame.add(playerpanel);
    customize_frame.add(playerpanel);

    frame.setVisible(false);
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

    turnLabel.setBackground(Color.darkGray);
    turnLabel.setForeground(Color.white);
    turnLabel.setFont(new Font("Arial", Font.BOLD, 30));
    turnLabel.setHorizontalAlignment(JLabel.CENTER);
    turnLabel.setText("Player 1's Turn");
    turnLabel.setOpaque(true);

    turnPanel.setLayout(new BorderLayout());
    turnPanel.add(turnLabel);
    // turnPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(50, 0,
    // 0, 0), new EtchedBorder()));

    userInfoPanel.setLayout(new GridLayout(2, 2));
    userInfoPanel.setBackground(Color.yellow);

    frame.add(textPanel, BorderLayout.NORTH);
    frame.add(spritePanel);
    frame.add(userInfoPanel);
    frame.add(turnPanel, BorderLayout.PAGE_END);

    // JLabel sprite1 = new JLabel("Testing");
    // sprite1.setLocation(10, 100);
    // boardPanel.add(sprite1);

    for (int r = 0; r < 2; r++) {
      for (int c = 0; c < 2; c++) {
        JLabel info = new JLabel();
        userInfo[r][c] = info;
        userInfoPanel.add(info);
        // Setting the properties of each grid square
        info.setBackground(Color.black);
        info.setForeground(Color.white);
        info.setFont(new Font("Arial", Font.BOLD, 50));
        info.setFocusable(false);
        info.setText("hi");
      }
    }

    boardPanel.setLayout(new GridLayout(3, 3));
    boardPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 50, 0, 50), new EtchedBorder()));
    boardPanel.setBackground(Color.black);
    frame.add(boardPanel);

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        JButton tile = new JButton();
        board[r][c] = tile;
        boardPanel.add(tile);
        // Setting the properties of each grid square
        tile.setBackground(Color.black);
        tile.setForeground(Color.white);
        tile.setFont(new Font("Arial", Font.BOLD, 120));
        tile.setFocusable(false);

        // tile.setText(currentPlayer);

        tile.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (gameOver)
              return;

            JButton tile = (JButton) e.getSource();
            if (tile.getText() == "") {
              tile.setText(currentPlayer);
              turns++;

              if (tile.getText() == "X") {
                tile.setForeground(colourX);
              }
              if (tile.getText() == "O") {
                tile.setForeground(colourO);
              }

              checkWinner();
              if (!gameOver) {
                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                currentPlayerUsername = currentPlayerUsername == usernameX ? usernameO : usernameX;
                turnLabel.setText(currentPlayerUsername + "'s turn.");

              }

            }

          }

        });

      }
    }

  }

  void checkWinner() {
    // horizontal
    for (int r = 0; r < 3; r++) {
      if (board[r][0].getText() == "")
        continue;

      if (board[r][0].getText() == board[r][1].getText() &&
          board[r][1].getText() == board[r][2].getText()) {
        for (int i = 0; i < 3; i++) {
          setWinner(board[r][i]);
        }
        gameOver = true;
        return;
      }
    }

    // vertical
    for (int c = 0; c < 3; c++) {
      if (board[0][c].getText() == "")
        continue;

      if (board[0][c].getText() == board[1][c].getText() &&
          board[1][c].getText() == board[2][c].getText()) {
        for (int i = 0; i < 3; i++) {
          setWinner(board[i][c]);
        }
        gameOver = true;
        return;
      }
    }

    // diagonally
    if (board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != "") {
      for (int i = 0; i < 3; i++) {
        setWinner(board[i][i]);
      }
      gameOver = true;
      return;
    }
    // anti-diagonally
    if (board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != "") {
      setWinner(board[0][2]);
      setWinner(board[1][1]);
      setWinner(board[2][0]);
      gameOver = true;
      return;
    }

    if (turns == 9) {
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
          setTie(board[r][c]);
        }
      }
      gameOver = true;
    }

  }

  void setWinner(JButton tile) {
    tile.setBackground(Color.green);
    tile.setForeground(Color.white);
    turnLabel.setText(currentPlayerUsername + " is the winner!");

  }

  void setTie(JButton tile) {
    tile.setBackground(Color.gray);
    turnLabel.setText("It's a tie!");
  }
}
