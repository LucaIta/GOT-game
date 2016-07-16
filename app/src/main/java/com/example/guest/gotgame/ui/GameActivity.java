package com.example.guest.gotgame.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.R;
import com.example.guest.gotgame.model.Quote;
import org.parceler.Parcels;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.nextQuoteButton) Button mNextQuoteButton;
    public static final String TAG = GameActivity.class.getSimpleName();
    public ArrayList<Quote> mQuotes = new ArrayList<>();
    QuoteFragment quoteFragment = new QuoteFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadFragment(quoteFragment, "quote fragment");
        ButterKnife.bind(this);
        mNextQuoteButton.setOnClickListener(this);
        mQuotes = Parcels.unwrap(getIntent().getParcelableExtra("quotes"));
        Log.v(TAG, "This is the quote in the GameActivity: " + mQuotes.get(0).getQuote());

    }

//    Fragment frag = getFragmentManager().findFragmentById(R.id.yourFragment);
//    ((TextView) frag.getView().findViewById(R.id.textView)).setText(s);

    // here starts the method which should allow me to load a fragment:

        public void loadFragment(Fragment frag, String tag) {  // I will pass it a fragment when I use it
            // Interaction with fragments is done through FragmentManager, which can be obtained via Activity.getFragmentManager() and Fragment.getFragmentManager().
            // The FragmentManager class and the FragmentTransaction class allow you to add, remove and replace fragments in the layout of your activity at runtime.
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = getFragmentManager().findFragmentById((R.id.quoteLayout)); // here I retrieve the current load fragment to check if one has already been loaded

            if (fragment == null) {
                ft.add(R.id.quoteLayout, frag, tag); // I load the fragment which I passed to the method -- the syntax here goes: (container where I want to add a fragment, fragment to add, tag passed to the method)
            } else {
                ft.replace(R.id.quoteLayout, frag, tag); // here if there already is a fragment, I just replace it
            }
            ft.addToBackStack(null); // dunno
            ft.commit(); // here I suppose I’m just committing
        }

        public void onClick(View v) {
            if (v == mNextQuoteButton) {
                Log.v(TAG, "TEST");
                Fragment frag = getFragmentManager().findFragmentById((R.id.quoteLayout));
                TextView mQuoteView = (TextView) frag.getView().findViewById(R.id.quoteView);
                mQuoteView.setText("If I see this it means that it works");
            }
        }
    }


// if I connected the next quote button to the loadFragment() I should be able to keep loading fragments
// I should probably load the fragment onResponse