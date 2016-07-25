package com.example.guest.gotgame.ui;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guest.gotgame.Constants;
import com.example.guest.gotgame.R;
import com.example.guest.gotgame.model.Score;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ScoreActivity extends AppCompatActivity {
    public final String TAG = ScoreActivity.class.getSimpleName();
    private DatabaseReference mScoreReference;
    public String score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        String score = intent.getStringExtra("score");
//        getCurrentTime();
        Score newScore = new Score(score);
        saveScore(newScore);
    }

//    public String getCurrentTime() {
//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date now = new Date();
//        String strDate = sdfDate.format(now);
//        return strDate;
//    }
//
    public void saveScore(Score newScore) {
        mScoreReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_SCORE);
        mScoreReference.push().setValue(newScore);
    }

}
