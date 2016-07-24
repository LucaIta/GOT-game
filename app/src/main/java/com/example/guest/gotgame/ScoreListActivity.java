package com.example.guest.gotgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.gotgame.adapters.FirebaseScoreViewHolder;
import com.example.guest.gotgame.model.Score;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScoreListActivity extends AppCompatActivity {
    private DatabaseReference mScoreReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        ButterKnife.bind(this);

        mScoreReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SCORE);
        setupFirebaseAdapter();
    }

    private void setupFirebaseAdapter() {
        /// here I'm missing the layout
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Score, FirebaseScoreViewHolder>(Score.class, R.layout.score_list_item, FirebaseScoreViewHolder.class, mScoreReference) {
            @Override
            protected void populateViewHolder(FirebaseScoreViewHolder viewHolder, Score model, int position) {
                viewHolder.bindScore(model); // here I bind the score to the viewHolder

            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter); // here we are using the FirebaseAdapter
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

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