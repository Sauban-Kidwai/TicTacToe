package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreeByThreeMulti extends AppCompatActivity {
    private char[][] board; // 3x3 Tic-Tac-Toe board
    private char currentPlayer; // Current player (either 'X' or 'O')
    private char playerOneMarker;
    private char playerTwoMarker;
    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private TextView countdownTextView; // Added for the countdown timer

    private TextView playerOneScoreTextView;
    private TextView playerTwoScoreTextView;

    private int playerOneScore;
    private int playerTwoScore;

    private CountDownTimer countDownTimer; // Added for the countdown timer
    private long timeLeftInMillis = 70000; // 70 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;

        // orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_game_board_3x3_lands);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_game_board_3x3);
        }

        // Initialize the game board
        board = new char[3][3];
        char initialPlayerMarker = getIntent().getCharExtra("startingPlayer", 'X'); // Get the starting player
        playerOneMarker = getIntent().getCharExtra("playerOneElement", 'X');
        playerTwoMarker = getIntent().getCharExtra("playerTwoElement", 'O');
        // Initialize currentPlayer based on the starting player
        currentPlayer = initialPlayerMarker;

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

        // Initialize player text views
        playerOneTextView = findViewById(R.id.P1);
        playerTwoTextView = findViewById(R.id.P2);

        // Initialize score text views
        playerOneScoreTextView = findViewById(R.id.P1score);
        playerTwoScoreTextView = findViewById(R.id.P2score);

        // Initialize countdown timer TextView
        countdownTextView = findViewById(R.id.countdown);

        // Set initial player text color
        playerOneTextView.setTextColor(getResources().getColor(R.color.red));
        playerTwoTextView.setTextColor(getResources().getColor(R.color.white));

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

        // Reset and NewMatch initialise
        Button resetButton = findViewById(R.id.Reset);
        Button newMatchButton = findViewById(R.id.NewMatch);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the reset action
                resetGame();
            }
        });
        newMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the newMatch action
                newMatch();
            }
        });
        // Initialize and start the countdown timer
        updateScores();
        startCountdownTimer();
    }

    // Handle a player's move
    private void onCellClick(int row, int col, Button button) {
        if (board[row][col] == '\0') { // Check if the cell is empty
            board[row][col] = currentPlayer;
            button.setText(String.valueOf(currentPlayer));

            // Check for a win or a draw
            if (checkWin(currentPlayer)) {
                // Handle the game result (player wins)
                int winningPlayer = (currentPlayer == playerOneMarker) ? 1 : 2;
                showGameResult("Player " + winningPlayer + " wins!");

                // Update the score and display it
                if (winningPlayer == 1) {
                    playerOneScore++;
                } else {
                    playerTwoScore++;
                }
                updateScores();

                disableAllButtons();
                stopCountdownTimer(); // Stop the timer on game over
            } else if (isBoardFull()) {
                // (draw)
                showGameResult("It's a draw!");
                stopCountdownTimer(); // Stop the timer on game over
            } else {
                // Switch to the next player
                currentPlayer = (currentPlayer == playerOneMarker) ? playerTwoMarker : playerOneMarker;

                // Update text color based on the current player
                if (currentPlayer == playerOneMarker) {
                    playerOneTextView.setTextColor(getResources().getColor(R.color.red));
                    playerTwoTextView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    playerOneTextView.setTextColor(getResources().getColor(R.color.white));
                    playerTwoTextView.setTextColor(getResources().getColor(R.color.red));
                }
            }
        }
    }

    // Update the score text views
    private void updateScores() {
        playerOneScoreTextView.setText("Score: " + playerOneScore);
        playerTwoScoreTextView.setText("Score: " + playerTwoScore);
    }

    // Check if the current player has won
    private boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player; // No win condition
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

    // Show the game result
    private void showGameResult(String message) {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText(message);
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

                if (!checkWin(currentPlayer)) {
                    // Handle game over when time runs out
                    showGameResult("It's a draw!");
                }
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

        if (timeLeftInMillis <= 0) {
            // Handle game over when time runs out
            showGameResult("It's a draw!");
            disableAllButtons();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCountdownTimer();
    }

    //Resets game
    private void resetGame() {
        // Clear the game board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '\0';
                Button button = findViewById(getResources().getIdentifier("Button" + (i * board.length + j + 1), "id", getPackageName()));
                button.setText(""); // Clear the button text
                button.setEnabled(true); // Enable all buttons
            }
        }

        // Reset player and scores
        currentPlayer = playerOneMarker;
        playerOneScore = 0;
        playerTwoScore = 0;
        updateScores();

        // Stop the existing countdown timer before starting a new one
        stopCountdownTimer();

        // Start the countdown timer again
        timeLeftInMillis = 70000; // Reset the timer duration
        startCountdownTimer();

        // Reset text color
        playerOneTextView.setTextColor(getResources().getColor(R.color.red));
        playerTwoTextView.setTextColor(getResources().getColor(R.color.white));

        // Clear the game result message
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText("");
    }

    //Keeps the score, and continues the current game
    private void newMatch() {
        // Clear the game board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '\0';
                Button button = findViewById(getResources().getIdentifier("Button" + (i * board.length + j + 1), "id", getPackageName()));
                button.setText(""); // Clear the button text
                button.setEnabled(true); // Enable all buttons
            }
        }

        // Stop the existing countdown timer before starting a new one
        stopCountdownTimer();


        // Start the countdown timer again
        timeLeftInMillis = 70000; // Reset the timer duration
        startCountdownTimer();

        // Reset text color
        playerOneTextView.setTextColor(getResources().getColor(R.color.red));
        playerTwoTextView.setTextColor(getResources().getColor(R.color.white));

        // Clear the game result message
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText("");
    }
}
