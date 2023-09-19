package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SinglePlayerActivity extends AppCompatActivity {

    private Button[] buttons;
    private boolean playerTurn = true; //true is X, false is O

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_board_3x3);

        buttons = new Button[9];
        buttons[0] = findViewById(R.id.Button1);
        buttons[1] = findViewById(R.id.Button2);
        buttons[2] = findViewById(R.id.Button3);
        buttons[3] = findViewById(R.id.Button4);
        buttons[4] = findViewById(R.id.Button5);
        buttons[5] = findViewById(R.id.Button6);
        buttons[6] = findViewById(R.id.Button7);
        buttons[7] = findViewById(R.id.Button8);
        buttons[8] = findViewById(R.id.Button9);

    }
}