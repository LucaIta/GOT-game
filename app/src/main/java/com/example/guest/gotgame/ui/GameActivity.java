package com.example.guest.gotgame.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.R;
import com.example.guest.gotgame.model.Quote;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.nextQuoteButton) Button mNextQuoteButton;
    public static final String TAG = GameActivity.class.getSimpleName();
//    public ArrayList<Quote> mQuotes = new ArrayList<>(); I comment It out because I want to put the quotes in the service
    int quoteNumber = 0;
    QuoteFragment quoteFragment = new QuoteFragment();
    GotQuotesService gotQuotesService = new GotQuotesService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadFragment(quoteFragment, "quote fragment");
//        getQuotes(); // here I call the method which will use the GotQuotesService comment out because I want it to happen in the main activity
        ButterKnife.bind(this);
        ArrayList<Quote> mQuotes = Parcels.unwrap(getIntent().getParcelableExtra("quotes"));
        Log.v("TAG", "the quote in the GameActivity is: "  + mQuotes.get(0).getQuote());
//        mRestaurants = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));

    }

//    private void getQuotes() {  // comment out because I want it to happen in them ain activity
//        final GotQuotesService gotQuotesService = new GotQuotesService();
//        for (int i = 0; i < 5; i++) {
//
//            gotQuotesService.retrieveQuotes(new Callback() { // this is the callback which I pass to the service
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        String jsonData = response.body().string();
//                        if (response.isSuccessful()) {
//                            Log.v(TAG, jsonData);
//                            mQuotes.add(gotQuotesService.processResults(jsonData)); get RID of this because now it's the Service that holdes the quotes
//                            // I could load the fragment or the button in main activity only when I = 4...
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        loadFragment(quoteFragment, "quote fragment"); // this method is created later and takes a fragment and a TAG string // loading it ad the end of the for loop makes the app crash because it tries to access the array which is still empty
//    }




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
                Log.v (TAG, gotQuotesService.getCurrentQuote().getQuote());
            }
        }
    }


// if I connected the next quote button to the loadFragment() I should be able to keep loading fragments
// I should probably load the fragment onResponse