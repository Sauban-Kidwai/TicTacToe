
package com.example.tictactoe;

    import androidx.appcompat.app.AppCompatActivity;
    import android.content.Intent;
    import android.content.res.Configuration;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

public class ThreeByThreeMulti extends AppCompatActivity {
    private char[][] board; // 3x3 Tic-Tac-Toe board
    private char currentPlayer; // Current player (either 'X' or 'O')
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        // Implement your win condition logic here
        // Check rows, columns, and diagonals
        // Return true if the player has won, otherwise return false
        // Example: check rows
        return (board[row][0] == player && board[row][1] == player && board[row][2] == player);
    }
    // Check if the board is full (a draw)
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
    // Show the game result (you can customize this)
    private void showGameResult(String message) {
        // You can display the result in a TextView or customize it as per your UI
        // For example:
        // TextView resultTextView = findViewById(R.id.resultTextView);
        // resultTextView.setText(message);
    }

    // Disable all grid cell buttons
    private void disableAllButtons() {

        for (int i = 1; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("Button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setEnabled(false);
        }
    }

    // Start the countdown timer
    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountdownText();
                // Handle timer finish, e.g., show a message or perform an action
            }
        }.start();
    }

    // Stop the countdown timer
    private void stopCountdownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // Update the countdown timer text
    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        String timeLeft = String.format("%02d:%02d", seconds / 60, seconds % 60);
        countdownTextView.setText(timeLeft);
    }

    @Override
    // destroy function
    protected void onDestroy() {
        super.onDestroy();
        stopCountdownTimer();
=======
        // Disable all buttons to prevent further moves
        // You can implement this based on your button IDs

    }
}
