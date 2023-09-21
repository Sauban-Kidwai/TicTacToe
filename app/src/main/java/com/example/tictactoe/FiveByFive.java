package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import android.os.Handler;

public class FiveByFive extends AppCompatActivity {
    private char[][] board; // 5x5 Tic-Tac-Toe board
    private char currentPlayer; // Current player (either 'X' or 'O')
    private char playerOneMarker;
    private char playerTwoMarker;
    private int markersToWin;
    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private TextView playerOneScoreTextView;
    private TextView playerTwoScoreTextView;
    private int playerOneScore;
    private int playerTwoScore;
    private TextView countdownTextView; // Added for the countdown timer
    private CountDownTimer countDownTimer; // Added for the countdown timer
    private long timeLeftInMillis = 70000; // 70 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_game_board_5x5_land);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_game_board_5x5);
        }

        // Initialize the game board
        board = new char[5][5];
        char initialPlayerMarker = getIntent().getCharExtra("startingPlayer", 'X'); // Get the starting player
        playerOneMarker = getIntent().getCharExtra("playerOneElement", 'X');
        playerTwoMarker = getIntent().getCharExtra("playerTwoElement", 'O');
        // Initialize currentPlayer based on the starting player
        currentPlayer = initialPlayerMarker;

        // Initialize how many markers to win the game
        markersToWin = getIntent().getIntExtra("markersToWin", 3); // Default to 3 in case of missing intent extra

        // Initialize the buttons for each grid cell and set click listeners
        Button[][] buttons = new Button[5][5];
        buttons[0][0] = findViewById(R.id.Button1);
        buttons[0][1] = findViewById(R.id.Button2);
        buttons[0][2] = findViewById(R.id.Button3);
        buttons[0][3] = findViewById(R.id.Button4);
        buttons[0][4] = findViewById(R.id.Button5);
        buttons[1][0] = findViewById(R.id.Button6);
        buttons[1][1] = findViewById(R.id.Button7);
        buttons[1][2] = findViewById(R.id.Button8);
        buttons[1][3] = findViewById(R.id.Button9);
        buttons[1][4] = findViewById(R.id.Button10);
        buttons[2][0] = findViewById(R.id.Button11);
        buttons[2][1] = findViewById(R.id.Button12);
        buttons[2][2] = findViewById(R.id.Button13);
        buttons[2][3] = findViewById(R.id.Button14);
        buttons[2][4] = findViewById(R.id.Button15);
        buttons[3][0] = findViewById(R.id.Button16);
        buttons[3][1] = findViewById(R.id.Button17);
        buttons[3][2] = findViewById(R.id.Button18);
        buttons[3][3] = findViewById(R.id.Button19);
        buttons[3][4] = findViewById(R.id.Button20);
        buttons[4][0] = findViewById(R.id.Button21);
        buttons[4][1] = findViewById(R.id.Button22);
        buttons[4][2] = findViewById(R.id.Button23);
        buttons[4][3] = findViewById(R.id.Button24);
        buttons[4][4] = findViewById(R.id.Button25);

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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
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

        // Initialize and start the countdown timer
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

                // Check if it's AI's turn (playerTwo)
                if (currentPlayer == playerTwoMarker) {
                    makeAIMove(); // Call the AI move function
                }
            }
        }
    }

    // AI Move (Random)
    private void makeAIMove() {
        // Disable all grid cell buttons
        disableAllButtons();

        // Check for empty cells and make a random move
        Random random = new Random();
        final int[] move = new int[2]; // Declare final array to store row and col

        do {
            move[0] = random.nextInt(5);
            move[1] = random.nextInt(5);
        } while (board[move[0]][move[1]] != '\0');

        // Simulate a delay to make the AI move visible
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int aiRow = move[0]; // Extract row from the final array
                int aiCol = move[1]; // Extract col from the final array
                Button aiButton = findViewById(getResources().getIdentifier("Button" + (aiRow * 5 + aiCol + 1), "id", getPackageName()));
                onCellClick(aiRow, aiCol, aiButton); // Simulate AI's click

                // Check if no one has won yet, then re-enable buttons
                if (!checkWin(currentPlayer)) {
                    enableAllButtons();
                }
            }
        }, 1000); // Adjust the delay duration as needed
    }

    // Update the score text views
    private void updateScores() {
        playerOneScoreTextView.setText("Score: " + playerOneScore);
        playerTwoScoreTextView.setText("Score: " + playerTwoScore);
    }

    // Check if the current player has won
    private boolean checkWin(char player) {
        boolean winner = false;
        int diagonal = 0;
        int antidiagonal = 0;

        // Check rows
        for (int i = 0; i < board.length; i++) {
            int m = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == player) {
                    m++;
                }
            }
            if (m == markersToWin) {
                winner = true; // Player has won in this row
            }
        }

        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            int m = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == player) {
                    m++;
                }
            }
            if (m == markersToWin) {
                winner = true; // Player has won in this column
            }
        }

        // Check diagonals (for square grids)
        if (board.length == board[0].length) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] == player) {
                    diagonal++;
                }
                if (board[i][board.length - 1 - i] == player) {
                    antidiagonal++;
                }
            }
            if (diagonal == markersToWin || antidiagonal == markersToWin) {
                winner = true; // Player has won in a diagonal
            }
        }

        // Check for 3-char diagonal win (anywhere on the grid)
        for (int i = 0; i <= board.length - markersToWin; i++) {
            for (int j = 0; j <= board[0].length - markersToWin; j++) {
                int primaryDiagonalCount = 0;
                int secondaryDiagonalCount = 0;

                for (int k = 0; k < markersToWin; k++) {
                    if (board[i + k][j + k] == player) {
                        primaryDiagonalCount++;
                    }
                    if (board[i + k][j + markersToWin - 1 - k] == player) {
                        secondaryDiagonalCount++;
                    }
                }

                if (primaryDiagonalCount == markersToWin || secondaryDiagonalCount == markersToWin) {
                    winner = true; // Player has won in a diagonal
                }
            }
        }

        return winner; // No win detected
    }

    // Check if the board is full (a draw)
    private boolean isBoardFull() {
        boolean full = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == '\0') {
                    full = false; // There's an empty cell, the board is not full
                }
            }
        }
        return full; // All cells are filled, it's a draw
    }

    // Show the game result (you can customize this)
    private void showGameResult(String message) {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText(message);
    }

    // Disable all grid cell buttons
    private void disableAllButtons() {
        for (int i = 1; i <= 25; i++) {
            int buttonId = getResources().getIdentifier("Button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setEnabled(false);
        }
    }

    // Enable all grid cell buttons
    private void enableAllButtons() {
        for (int i = 1; i <= 25; i++) {
            int buttonId = getResources().getIdentifier("Button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setEnabled(true);
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
    protected void onDestroy() {
        super.onDestroy();
        stopCountdownTimer();
    }
}
