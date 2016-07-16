package com.example.guest.gotgame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.ui.GameActivity;
import com.example.guest.gotgame.R;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    GotQuotesService gotQuotesService = new GotQuotesService(); // to get rid of
    @Bind(R.id. playButton) Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getQuotes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Log.v("GotQuoteService", Integer.toString(gotQuotesService.mQuotes.size()));
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("quotes", Parcels.wrap(gotQuotesService.mQuotes));
        startActivity(intent);
    }

    private void getQuotes() {  // comment out because I want it to happen in them ain activity
//    final GotQuotesService gotQuotesService = new GotQuotesService(); // to restore
        for (int i = 0; i < 5; i++) {

            gotQuotesService.retrieveQuotes(new Callback() { // this is the callback which I pass to the service
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            Log.v(TAG, jsonData);
                            gotQuotesService.processResults(jsonData); // get RID of this because now it's the Service that holdes the quotes
                            // I could load the fragment or the button in main activity only when I = 4...
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
