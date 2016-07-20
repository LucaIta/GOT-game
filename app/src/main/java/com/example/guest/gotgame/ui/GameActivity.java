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
import android.widget.Toast;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.R;
import com.example.guest.gotgame.model.Quote;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.nextQuoteButton) Button mNextQuoteButton;

    @Bind(R.id.button1) Button mButton1;
    @Bind(R.id.button2) Button mButton2;
    @Bind(R.id.button3) Button mButton3;
    @Bind(R.id.button4) Button mButton4;

    public static final String TAG = GameActivity.class.getSimpleName();
    public ArrayList<Quote> mQuotes = new ArrayList<>();
    QuoteFragment quoteFragment = new QuoteFragment();
    String currentCharacter = "";
    int quoteCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//        quoteFragment.setQuote();
        loadFragment(quoteFragment, "quote fragment");
        ButterKnife.bind(this);
        mNextQuoteButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        mQuotes = Parcels.unwrap(getIntent().getParcelableExtra("quotes"));
        Log.v(TAG, "This is the quote in the GameActivity: " + mQuotes.get(0).getQuote());
//        Fragment frag = getFragmentManager().findFragmentById((R.id.quoteLayout)); // here It doesn't work though it works in the button
//        TextView mQuoteView = (TextView) frag.getView().findViewById(R.id.quoteView);
//        mQuoteView.setText("If I see this it means that it works");
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
//          Fragment frag2 = getFragmentManager().findFragmentById((R.id.quoteLayout)); // here It doesn't work though it works in the button
//            TextView mQuoteView = (TextView) frag2.getView().findViewById(R.id.quoteView);// the one which is null is the fragment that I retrieve
//            mQuoteView.setText("If I see this it means that it works");
            ft.addToBackStack(null); // dunno
            ft.commit(); // here I suppose I’m just committing
        }

        public void onClick(View v) {
            if (v == mNextQuoteButton) {
                Fragment frag = getFragmentManager().findFragmentById((R.id.quoteLayout));
                TextView mQuoteView = (TextView) frag.getView().findViewById(R.id.quoteView);
                currentCharacter = getCurrentQuote().getCharacter(); // I should refactor here
                mQuoteView.setText(getCurrentQuote().getQuote());
//                Log.v(TAG, getCharacters().get(0));
                setButtons();
                quoteCounter ++;
            } if (v == mButton1 || v == mButton2 || v == mButton3 || v == mButton4) {
                Button pressedButton = (Button) v;
                String character = pressedButton.getText().toString();
                if (character == currentCharacter) { // here I check wheter the correct button has been clicked
                    Log.v(TAG, "CORRECT");
                } else {
                    Log.v(TAG, "WRONG");
                }
            }
        }

        public Quote getCurrentQuote() {
            return mQuotes.get(quoteCounter);
        }

        public ArrayList<String> getCharacters() {
            ArrayList<String> characters = new ArrayList<>();
            characters.add(getCurrentQuote().getCharacter());
            Random rand = new Random(); // generates an Instance of Random to use it later
            for (int i = 1; i < 4; i++) { // add 3 random characters to the array
                int randomNum = rand.nextInt(4);
                characters.add(mQuotes.get(randomNum).getCharacter());
                Collections.shuffle(characters); // here I'm shuffling the array // doesn't work anymore, it used to work...
            }
            return characters;
        }

        public void setButtons() {
            ArrayList<String> characters = getCharacters();
            Log.v(TAG, "the size of the character array is" + Integer.toString(characters.size()));
            mButton1.setText(characters.get(0));
            mButton2.setText(characters.get(1));
            mButton3.setText(characters.get(2));
            mButton4.setText(characters.get(3));
        }

    }


// if I connected the next quote button to the loadFragment() I should be able to keep loading fragments
// I should probably load the fragment onResponse