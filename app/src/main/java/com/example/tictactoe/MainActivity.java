package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.res.Configuration;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_menu);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_main_menu_landscape);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_main_menu);
        }

        // Find the Single Player button by its ID
        Button singlePlayerButton = findViewById(R.id.singleplayer_button);
        Button multiPlayerButton = findViewById(R.id.multiplayer_button);

        // OnClickListener button
        singlePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the new activity
                Intent intent = new Intent(MainActivity.this, SinglePlayerActivity.class);
                startActivity(intent);
            }
        });

        multiPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the new activity
                Intent intent = new Intent(MainActivity.this, MultiPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}
