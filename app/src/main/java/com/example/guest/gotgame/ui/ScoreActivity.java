package com.example.guest.gotgame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guest.gotgame.R;

public class ScoreActivity extends AppCompatActivity {
    public final String TAG = ScoreActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        String score = intent.getStringExtra("score");
        Log.v("TAG", "the score retrieved from the intent is" + score);
    }
}
