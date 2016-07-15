package com.example.guest.gotgame.ui;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, view);
        mQuoteView.setText("test"); // what if here I set something like "current Quote", I could have a service taking care of it
        return view;
    }

    // Also, when I pulled your project, I had a red squiggly line in the onCreate for your QuoteFragment: ButterKnife.bind(this); I think this is due to the fact that you're not binding a standard view here, but a view contained inside a fragment, and butterknife can't access it as a result.
    // Perry's code'
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //        View rootView = inflater.inflate(R.layout.fragment_quote, container, false);
    //        TextView mQuoteView = (TextView) rootView.findViewById(R.id.quoteView); // Inflate the layout for this fragment
    //        mQuoteView.setText("test");
    //        return rootView;
}
