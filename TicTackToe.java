
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TicTackToe extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel label;

    public TicTackToe() {
        setTitle("Tic-Tac-Toe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 100));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        label = new JLabel("Player " + currentPlayer + "'s turn");
        label.setHorizontalAlignment(JLabel.CENTER);

        add(boardPanel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            clickedButton.setText(String.valueOf(currentPlayer));
            clickedButton.setEnabled(false);

            if (checkWin()) {
                label.setText("Player " + currentPlayer + " wins!");
                disableAllButtons();
            } else if (isBoardFull()) {
                label.setText("It's a draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                label.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer)) ||
                    buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        return buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer)) ||
                buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTackToe());
    }
}
