package com.example.guest.gotgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.R;
import com.example.guest.gotgame.model.Quote;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameActivity extends AppCompatActivity {
    public static final String TAG = GameActivity.class.getSimpleName();
    public ArrayList<Quote> mQuotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getQuotes(); // here I call the method which will use the GotQuotesService
    }

    private void getQuotes() {
        final GotQuotesService gotQuotesService = new GotQuotesService();
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
                        mQuotes = gotQuotesService.processResults(jsonData);
                        Log.v(TAG, mQuotes.get(0).getQuote());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
