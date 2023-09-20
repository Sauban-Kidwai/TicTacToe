package com.example.tictactoe;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MultiPlayerActivity extends AppCompatActivity {

    MainActivity main;
    private boolean gameType;
    private int marks;
    private int gridSize;
    private char playerOneElement;

    private char playerTwoElement;
    private int markerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings_screen_2player);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_settings_screen_2player_land);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_settings_screen_2player);
        }

        // Find the 3x3 button by its ID
        Button three = findViewById(R.id.board_3x3_button);
        Button four = findViewById(R.id.board_4x4_button);
        Button five = findViewById(R.id.board_5x5_button);

        // Buttons for markers
        Button markThree = findViewById(R.id.threemarker);
        Button markFour = findViewById(R.id.fourmarker);
        Button markFive = findViewById(R.id.fivemarker);

        // Buttons for player 1 x or o, the second player will get what's left
        Button playerOneX = findViewById(R.id.PlayeroneX);
        Button playerOneO = findViewById(R.id.PlayeroneO);


        // OnClickListener button
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGridSize(3);
                markerCount = 3; //marker count for 3x3 board

                // marker four and five will be invisible
                four.setVisibility(View.INVISIBLE);
                five.setVisibility(View.INVISIBLE);
                markFour.setVisibility(View.INVISIBLE);
                markFive.setVisibility(View.INVISIBLE);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGridSize(4);

                // marker three and five will be invisible
                three.setVisibility(View.INVISIBLE);
                five.setVisibility(View.INVISIBLE);
                markFive.setVisibility(View.INVISIBLE);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGridSize(5);

                // marker three and four will be invisible
                three.setVisibility(View.INVISIBLE);
                four.setVisibility(View.INVISIBLE);
            }
        });

        markThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMarker(3);
                markerCount = 3;
                // set market to do something
                markFour.setVisibility(View.INVISIBLE);
                markFive.setVisibility(View.INVISIBLE);
            }
        });

        markFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMarker(4);
                markerCount = 4;
                // set market to do something
                markThree.setVisibility(View.INVISIBLE);
                markFive.setVisibility(View.INVISIBLE);
            }
        });

        markFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMarker(5);
                markerCount = 5;
                // set market to do something
                markThree.setVisibility(View.INVISIBLE);
                markFour.setVisibility(View.INVISIBLE);
            }
        });

        playerOneX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayerElements('X', 'O');
                startGame('X');
            }
        });


        playerOneO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayerElements('O', 'X');
                startGame('O');
            }
        });

    }


    private void setPlayerElements(char playerOneMarker, char playerTwoMarker) {
        playerOneElement = playerOneMarker;
        playerTwoElement = playerTwoMarker;
    }

    public void setMarker(int m) {
        marks = m;
    }

    public char getPlayerOneElement() {
        return playerOneElement;
    }

    public void setGridSize(int s) {
        gridSize = s;
    }

    private void startGame(char startingPlayer) {
        Intent intent;
        if (markerCount == 3) {
            intent = new Intent(this, ThreeByThreeMulti.class);
        } else if (markerCount == 4) {
            intent = new Intent(this, FourByFourMulti.class);
        } else if (markerCount == 5) {
            intent = new Intent(this, FiveByFiveMulti.class);
        } else {
            return;
        }

        intent.putExtra("gridSize", gridSize);
        intent.putExtra("playerOneElement", playerOneElement);
        intent.putExtra("playerTwoElement", playerTwoElement);
        intent.putExtra("markerCount", markerCount);
        intent.putExtra("startingPlayer", startingPlayer);
        startActivity(intent);
    }
}