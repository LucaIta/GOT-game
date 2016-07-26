package com.example.guest.gotgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guest.gotgame.adapters.FirebaseScoreViewHolder;
import com.example.guest.gotgame.model.Score;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScoreListActivity extends AppCompatActivity {
    private DatabaseReference mScoreReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    public ArrayList<String> mScoreList = new ArrayList();
    private ValueEventListener mScoreReferenceEventListener;

//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        ButterKnife.bind(this);

        mScoreReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SCORE);
        mScoreReferenceEventListener = mScoreReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mScoreList = new ArrayList<>();
                for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren()) {
                    Score score = scoreSnapshot.getValue(Score.class);
                    String mScore = score.getScore();
                    String mTime = score.getTime();
                    String listElement = "Score: " + mScore + " -- Date: " + mTime;
//                    String listElement = scoreSnapshot.getValue().toString();
                    mScoreList.add(listElement); // is it correct? // should I create an array of Strings?
                    ArrayAdapter adapter = new ArrayAdapter(ScoreListActivity.this, android.R.layout.simple_list_item_1, mScoreList);
                    mListView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        setupFirebaseAdapter();

//        mScoreReferenceEventListener = mScoreReference.addValueEventListener(new ValueEventListener() {
        // with this code I iterate through the loop correctly, as many times as the number of objects I have, so the refence is correct...
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                scores = new ArrayList<>();
//                for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren()) {
//                    Log.v("ScoreListActivity", "Loop in scoreListActivity");
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//
//        });

    }


//    private void setupFirebaseAdapter() {
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Score, FirebaseScoreViewHolder>(Score.class, R.layout.score_list_item, FirebaseScoreViewHolder.class, mScoreReference) {
//            @Override
//            protected void populateViewHolder(FirebaseScoreViewHolder viewHolder, Score model, int position) {
//                viewHolder.bindScore(model); // here I bind the score to the viewHolder
//
//            }
//        };
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter); // here we are using the FirebaseAdapter
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }


}
//
//public class SavedRestaurantListActivity extends AppCompatActivity {
//    private DatabaseReference mRestaurantReference;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;
//
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_restaurants);
//        ButterKnife.bind(this);
//
//        mRestaurantReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS);
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter() {
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Restaurant, FirebaseRestaurantViewHolder>
//                (Restaurant.class, R.layout.restaurant_list_item, FirebaseRestaurantViewHolder.class,
//                        mRestaurantReference) {
//
//            @Override
//            protected void populateViewHolder(FirebaseRestaurantViewHolder viewHolder,
//                                              Restaurant model, int position) {
//                viewHolder.bindRestaurant(model);
//            }
//        };
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }
//
//    @Override
//
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//}