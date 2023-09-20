package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class FivebyFive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_board_5x5);

        final TextView textView = findViewById(R.id.countdown);

        new CountDownTimer(60000, 1000) { // 60 seconds (1 minute) countdown
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                textView.setText("FINISH!!");
            }
        }.start();
    }
}
