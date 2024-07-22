import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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

  JButton[][] board = new JButton[3][3];
  String playerX = "X";
  String playerO = "O";
  String currentPlayer = playerX;

  String usernameX = "Player 1";
  String usernameO = "Player 2";
  String currentPlayerUsername = usernameX;

  Integer scoreX = 0;
  Integer scoreO = 0;

  String redC = "RED";
  String orangeC = "ORANGE";
  String yellowC = "YELLOW";
  String greenC = "GREEN";
  String blueC = "BLUE";
  String purpleC = "PURPLE";
  String pinkC = "PINK";

  // SET THE APPROPRIATE VARIABLE HERE
  String colourX = purpleC;
  String colourO = pinkC;

  boolean gameOver = false;
  int turns = 0;

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

    frame.add(textPanel, BorderLayout.NORTH);
    frame.add(turnPanel, BorderLayout.PAGE_END);

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
                if (colourX == redC) {
                  tile.setForeground(Color.red);
                }
                if (colourX == orangeC) {
                  tile.setForeground(Color.orange);
                }
                if (colourX == yellowC) {
                  tile.setForeground(Color.yellow);
                }
                if (colourX == greenC) {
                  tile.setForeground(Color.green);
                }
                if (colourX == blueC) {
                  tile.setForeground(Color.blue);
                }
                if (colourX == purpleC) {
                  tile.setForeground(Color.magenta);
                }
                if (colourX == pinkC) {
                  tile.setForeground(Color.pink);
                }

              }
              if (tile.getText() == "O") {
                if (colourO == redC) {
                  tile.setForeground(Color.red);
                }
                if (colourO == orangeC) {
                  tile.setForeground(Color.orange);
                }
                if (colourO == yellowC) {
                  tile.setForeground(Color.yellow);
                }
                if (colourO == greenC) {
                  tile.setForeground(Color.green);
                }
                if (colourO == blueC) {
                  tile.setForeground(Color.blue);
                }
                if (colourO == purpleC) {
                  tile.setForeground(Color.magenta);
                }
                if (colourO == pinkC) {
                  tile.setForeground(Color.pink);
                }
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
