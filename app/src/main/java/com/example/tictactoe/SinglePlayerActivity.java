package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SinglePlayerActivity extends AppCompatActivity {
    // Temp 2
    MainActivity main;
    private boolean gameType;
    private int marks;
    private int gridSize;
    private char playerOneElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings_screen_singleplayer);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_settings_screen_singleplayer_land);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_settings_screen_singleplayer);
        }

        // Find the 3x3 button by its ID /!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
        Button three = findViewById(R.id.board_3x3_button);
        Button four = findViewById(R.id.board_4x4_button);
        Button five = findViewById(R.id.board_5x5_button);

        // Buttons for markers
        Button markThree = findViewById(R.id.threemarker);
        Button markFour = findViewById(R.id.fourmarker);
        Button markFive = findViewById(R.id.fivemarker);

        // Buttons for player 1 x or o
        Button playerOneX = findViewById(R.id.PlayeroneX);
        Button playerOneO = findViewById(R.id.PlayeroneO);

        // OnClickListener button
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGridSize(3);

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
                // set market to do something
                markFour.setVisibility(View.INVISIBLE);
                markFive.setVisibility(View.INVISIBLE);
            }
        });

        markFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMarker(4);
                // set market to do something
                markThree.setVisibility(View.INVISIBLE);
                markFive.setVisibility(View.INVISIBLE);
            }
        });

        markFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMarker(5);
                // set market to do something
                markThree.setVisibility(View.INVISIBLE);
                markFour.setVisibility(View.INVISIBLE);
            }
        });


        playerOneX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayerOneElement('X');
            }
        });


        playerOneO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayerOneElement('O');
            }
        });

    }

    public boolean getGameType() {
        gameType = main.getSinglePlayer();
        return gameType;
    }

    public void setMarker(int m) {
        marks = m;
    }

    public int getMarker() {
        return marks;
    }

    public void setPlayerOneElement(char e) {
        playerOneElement = e;
    }

    public char getPlayerOneElement() {
        return playerOneElement;
    }

    public void setGridSize(int s) {
        gridSize = s;
    }

    public int getGridSize() {
        return gridSize;
    }

    // When gridSize != 0 && playerOneElement != null && marker != 0 >> startActivity(intent)
    /*
    five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the new activity
                Intent intent = new Intent(SinglePlayerActivity.this, FivebyFive.class);
                startActivity(intent);
            }
        });
     */
}