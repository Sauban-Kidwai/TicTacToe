package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
            // Initial view
        } else {
            setContentView(R.layout.activity_main);
            // Initial view
        }

        Button singleplayer_button = findViewById(R.id.singleplayer_button);
        Button multiplayer_button = findViewById(R.id.multiplayer_button);

        multiplayer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNameEnterView = true;
                setContentView(R.layout.name_enter); // Switch to name_enter.xml
                Button back_button = findViewById(R.id.back_button);

                back_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isNameEnterView = false;
                        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                            setContentView(R.layout.activity_main_landscape); // Switch back to activity_main_landscape.xml
                        } else {
                            setContentView(R.layout.activity_main); // Switch back to activity_main.xml
                        }
                    }
                });
            }
        });
    }
}
