package com.example.guest.gotgame.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.gotgame.Constants;
import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.R;
import com.example.guest.gotgame.ScoreListActivity;
import com.example.guest.gotgame.model.Quote;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public ArrayList<String> mCharacters = new ArrayList<>();
    QuoteFragment quoteFragment = new QuoteFragment();
    String currentCharacter = "";
    int quoteCounter = 0;
    int score = 0;


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
        mCharacters = Parcels.unwrap(getIntent().getParcelableExtra("characters"));
        Log.v(TAG, "the size of the character array in the main activity is" + mCharacters.size());


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

            Fragment frag = getFragmentManager().findFragmentById((R.id.quoteLayout)); // If I get rid of the code which set the score in this activity, I should move this line down in the if statement
            TextView mQuoteView = (TextView) frag.getView().findViewById(R.id.quoteView);

            if (v == mNextQuoteButton) {
                // do nothing, to remove, I used it in the first screen
            } if (v == mButton1 || v == mButton2 || v == mButton3 || v == mButton4) {
                if (!(quoteCounter == 5)) {
                    Button pressedButton = (Button) v;
                    String character = pressedButton.getText().toString();
                    if (character == currentCharacter) { // here I check wheter the correct button has been clicked
                        Toast.makeText(GameActivity.this, "CORRECT", Toast.LENGTH_LONG).show();
                        score ++;
                    } else {
                        Toast.makeText(GameActivity.this, "WRONG", Toast.LENGTH_LONG).show();
                    }
//                    Fragment frag = getFragmentManager().findFragmentById((R.id.quoteLayout));
//                    TextView mQuoteView = (TextView) frag.getView().findViewById(R.id.quoteView);
                    currentCharacter = getCurrentQuote().getCharacter(); // I should refactor here
                    mQuoteView.setText(getCurrentQuote().getQuote());
//                Log.v(TAG, getCharacters().get(0));
                    setButtons();
                    quoteCounter ++;
                } else {
//                    Intent intent = new Intent(GameActivity.this, ScoreActivity.class); // this is the correct one
                    Intent intent = new Intent(GameActivity.this, ScoreListActivity.class);
                    intent.putExtra("score", Integer.toString(score)); // here I need to put as an extra, a score object....
                    startActivity(intent);
//                    hideButtons();
//                    mQuoteView.setText("You scored " + score + "/5");
//                    mScoreReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_SCORE);
//                    saveScore(score);
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
            for (;characters.size() < 4;) { // add 3 random characters to the array
                int randomNum = rand.nextInt(4);
                characters.add(mQuotes.get(randomNum).getCharacter());
                Collections.shuffle(characters); // here I'm shuffling the array
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

        public void hideButtons() {
            mButton1.setVisibility(View.GONE);
            mButton2.setVisibility(View.GONE);
            mButton3.setVisibility(View.GONE);
            mButton4.setVisibility(View.GONE);
        }


    }


// if I connected the next quote button to the loadFragment() I should be able to keep loading fragments
// I should probably load the fragment onResponse