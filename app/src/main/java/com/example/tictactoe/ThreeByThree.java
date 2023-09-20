package com.example.tictactoe;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThreeByThree extends AppCompatActivity {

    private GridLayout gridLayout;
    private char currentPlayer;
    private Button[][] buttons;
    private int gridSize;
    private int marksToWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_board_3x3);

        gridLayout = findViewById(R.id.rightHalfgrid);
        gridSize = getIntent().getIntExtra("gridSize", 3);
        marksToWin = getIntent().getIntExtra("marksToWin", 3);
        setContentView(R.layout.fragment_game_board_4x4);

        final TextView textView = findViewById(R.id.countdown);

        new CountDownTimer(60000, 1000) { // 60 seconds (1 minute) countdown
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                textView.setText("FINISH!!");
            }
        }.start();
        initializeBoard();
    }

    private void initializeBoard() {
        currentPlayer = 'X';

        buttons = new Button[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                String buttonID = "Button" + (i * gridSize + j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setText("");
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCellClick(v);
                    }
                });
            }
        }
    }

    public void onCellClick(View v) {
        Button cell = (Button) v;
        if (cell.getText().toString().isEmpty()) {
            cell.setText(String.valueOf(currentPlayer));
            if (checkForWin()) {
                gameOver(currentPlayer + " wins!");
            } else if (isBoardFull()) {
                gameOver("It's a draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < gridSize; i++) {
            if (checkLine(0, i, 1, 0) || checkLine(i, 0, 0, 1)) {
                return true;
            }
        }
        if (checkLine(0, 0, 1, 1) || checkLine(0, gridSize - 1, 1, -1)) {
            return true;
        }
        return false;
    }

    private boolean checkLine(int row, int col, int rowIncrement, int colIncrement) {
        char firstCell = buttons[row][col].getText().charAt(0);
        if (firstCell == ' ') {
            return false;
        }
        for (int i = 0; i < marksToWin; i++) {
            if (buttons[row][col].getText().charAt(0) != firstCell) {
                return false;
            }
            row += rowIncrement;
            col += colIncrement;
        }
        return true;
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button cell : row) {
                if (cell.getText().toString().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void gameOver(String message) {
        // You can add your game over logic here, such as showing a dialog or returning to the main menu.
    }
}
