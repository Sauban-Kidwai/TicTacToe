package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isNameEnterView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentOrientation = getResources().getConfiguration().orientation;
        isNameEnterView = (currentOrientation == Configuration.ORIENTATION_LANDSCAPE);

        updateLayout();

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        Button singleplayer_button = findViewById(R.id.singleplayer_button);
        Button multiplayer_button = findViewById(R.id.multiplayer_button);

        multiplayer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNameEnterView = true;
                updateLayout();
            }
        });
    }

    private void updateLayout() {
        if (isNameEnterView) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.name_enter_landscape);
            } else {
                setContentView(R.layout.name_enter);
            }
            setupBackButtonListener();
        } else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.activity_main_landscape);
            } else {
                setContentView(R.layout.activity_main);
            }
            setupButtonListeners();
        }
    }

    private void setupBackButtonListener() {
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNameEnterView = false;
                updateLayout();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayout();
    }
}
