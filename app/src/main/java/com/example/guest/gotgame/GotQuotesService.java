package com.example.guest.gotgame;

import android.util.Log;

import com.example.guest.gotgame.model.Quote;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GotQuotesService { // some of this code might be moved outside the service since I'm calling it several times

    private static String url = "https://got-quotes.herokuapp.com/quotes";
    private static OkHttpClient client = new OkHttpClient.Builder().build();
    private static Request request = new Request.Builder().url(url).build();


    public static void retrieveQuotes(Callback callback) { // this is the callback will execute when our API request receives a readable response from Yelp

        Call call = client.newCall(request);
        call.enqueue(callback); // here the callback will be executed when I receive the response
    }

    public ArrayList<Quote> processResults(String jsonData) { // I pass the process Results, as the callback, in the game activity
        ArrayList<Quote> quotes = new ArrayList<>();

        try {
                JSONObject quoteJSON = new JSONObject(jsonData);
                String quoteString = quoteJSON.getString("quote");
                String characterString = quoteJSON.getString("character");
                Quote quote = new Quote(quoteString, characterString);
                quotes.add(quote);

//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quotes;
    }
}
