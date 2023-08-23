package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isNameEnterView = false;
    private int currentLayoutId = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            currentLayoutId = R.layout.activity_main_landscape;
        }

        setContentView(currentLayoutId);

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        Button singleplayer_button = findViewById(R.id.singleplayer_button);
        Button multiplayer_button = findViewById(R.id.multiplayer_button);

        multiplayer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNameEnterView = true;
                setContentView(R.layout.name_enter);
                currentLayoutId = R.layout.name_enter;
                setupBackButtonListener();
            }
        });
    }

    private void setupBackButtonListener() {
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNameEnterView = false;
                currentLayoutId = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        ? R.layout.activity_main_landscape : R.layout.activity_main;
                setContentView(currentLayoutId);
                setupButtonListeners();
            }
        });
    }
}
