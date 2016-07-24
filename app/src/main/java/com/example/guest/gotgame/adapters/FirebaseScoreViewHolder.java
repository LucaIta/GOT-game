package com.example.guest.gotgame.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.guest.gotgame.R;
import com.example.guest.gotgame.model.Score;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by oem on 7/22/16.
 */
public class FirebaseScoreViewHolder extends RecyclerView.ViewHolder {  // I might not need the click listener
//    public static final int MAX_WIDTH = 200;  // this are for Picasso
//    public static final int MAX_HEIGHT = 200;

    // member Variable to hold View and Context which we set in the constructor
    View mView;
    Context mContext;

    public FirebaseScoreViewHolder(View itemView) { // costructor
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindScore(Score score) {

        // bind and set Views

        TextView scoreTextView = (TextView) mView.findViewById(R.id.scoreTextView);
//        TextView dateTextView = (TextView) mView.findViewById(R.id.dateTextView); // textView for the date here
//        Log.v("FirebaseViewHolder", "The return for the getScore is " + score.getScore());
//        scoreTextView.setText(score.getScore());
        scoreTextView.setText("The score is: " + score.getScore());
//        dateTextView.setText(score.getTime());

    }

}