
package com.example.tictactoe;

    import androidx.appcompat.app.AppCompatActivity;
    import android.content.res.Configuration;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

public class ThreeByThreeMulti extends AppCompatActivity {
    private char[][] board; // 3x3 Tic-Tac-Toe board
    private char currentPlayer; // Current player (either 'X' or 'O')
    private char playerOneMarker;
    private char playerTwoMarker;
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
            if (checkWin(currentPlayer)) {
                // Handle the game result (player wins)
                showGameResult("Player " + (currentPlayer == playerOneMarker ? "1" : "2") + " wins!");
                disableAllButtons();
            } else if (isBoardFull()) {
                // (draw)
                showGameResult("It's a draw!");
            } else {
                // Switch to the next player
                currentPlayer = (currentPlayer == playerOneMarker) ? playerTwoMarker : playerOneMarker;
            }
        }
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
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;// No win condition
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
}
