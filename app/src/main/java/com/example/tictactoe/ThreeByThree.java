package com.example.tictactoe;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThreeByThree extends AppCompatActivity {

    private GridLayout gridLayout;
    private char[][] board; // 3x3 Tic-Tac-Toe board
    private char currentPlayer;
    private int marksToWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_board_3x3);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_game_board_3x3_lands);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_game_board_3x3);
        }

        // Initialize the game board
        board = new char[3][3];
        currentPlayer = getIntent().getCharExtra("playerOneElement", 'X'); // Start with player one's marker

        // Initialize the buttons for each grid cell and set click listeners
        Button[][] buttons = new Button[3][3];
        buttons[0][0] = findViewById(R.id.Button1);
        buttons[0][1] = findViewById(R.id.Button2);
        buttons[0][2] = findViewById(R.id.Button3);
        buttons[1][0] = findViewById(R.id.Button4);
        buttons[1][1] = findViewById(R.id.Button5);
        buttons[1][2] = findViewById(R.id.Button6);
        buttons[2][0] = findViewById(R.id.Button7);
        buttons[2][1] = findViewById(R.id.Button8);
        buttons[2][2] = findViewById(R.id.Button9);

        gridLayout = findViewById(R.id.rightHalfgrid);
        marksToWin = getIntent().getIntExtra("marksToWin", 3);
        setContentView(R.layout.fragment_game_board_3x3);

        final TextView textView = findViewById(R.id.countdown);

        new CountDownTimer(60000, 1000) { // 60 seconds (1 minute) countdown
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                textView.setText("FINISH!!");
            }
        }.start();


        // Set click listeners for each grid cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Handle the player's move
                        onCellClick(row, col, (Button) view);
                    }
                });
            }
        }
    }
    // Handle a player's move
    private void onCellClick(int row, int col, Button button) {
        if (board[row][col] == '\0') { // Check if the cell is empty
            board[row][col] = currentPlayer;
            button.setText(String.valueOf(currentPlayer));

            // Check for a win or a draw
            if (checkWin(row, col, currentPlayer)) {
                // Handle the game result (player wins)
                showGameResult(currentPlayer + " wins!");
                disableAllButtons();
            } else if (isBoardFull()) {
                // Handle the game result (draw)
                showGameResult("It's a draw!");
            } else {
                // Switch to the next player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Check if the current player has won
    private boolean checkWin(int row, int col, char player) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != player) {
                break;
            }
            if (i == board.length - 1) {
                return true; // Player has won in this column
            }
        }

        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            if (board[row][j] != player) {
                break;
            }
            if (j == board[0].length - 1) {
                return true; // Player has won in this row
            }
        }

        // Check diagonal (top-left to bottom-right)
        if (row == col) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] != player) {
                    break;
                }
                if (i == board.length - 1) {
                    return true; // Player has won in this diagonal
                }
            }
        }

        // Check anti-diagonal (top-right to bottom-left)
        if (row + col == board.length - 1) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][board.length - 1 - i] != player) {
                    break;
                }
                if (i == board.length - 1) {
                    return true; // Player has won in this anti-diagonal
                }
            }
        }

        return false; // No win detected
    }

    // Check if the board is full >> draw
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    return false; // There's an empty cell, the board is not full
                }
            }
        }
        return true; // All cells are filled, it's a draw
    }
    // Show the game result
    private void showGameResult(String message) {
    }

    // Disable all grid cell buttons
    private void disableAllButtons() {
    }
}
