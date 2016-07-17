package com.example.guest.gotgame.ui;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {
    @Bind(R.id.quoteView) TextView mQuoteView; // here I retrieve the view in the fragment

    public QuoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GameActivity gameActivity = new GameActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, view);
//        mQuoteView.setText("Test");
        return view;
    }

//    public void setQuote() { // doesn't work
//        View view = inflater.inflate(R.layout.fragment_quote, container, false);
//        ButterKnife.bind(this, view);
//        mQuoteView.setText("Test");
//    }
}
