package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

public class FiveByFive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_board_5x5);
    }
}