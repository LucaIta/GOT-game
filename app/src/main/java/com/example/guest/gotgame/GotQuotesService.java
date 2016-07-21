package com.example.guest.gotgame;

import android.util.Log;
import com.example.guest.gotgame.model.Quote;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GotQuotesService {

    private static String url = "https://got-quotes.herokuapp.com/quotes";
    private static OkHttpClient client = new OkHttpClient.Builder().build();
    private static Request request = new Request.Builder().url(url).build();
    private Quote quote;

    public static void retrieveQuotes(Callback callback) { // this is the callback will execute when our API request receives a readable response from Yelp

        Call call = client.newCall(request);
        call.enqueue(callback); // here the callback will be executed when I receive the response
    }

    public Quote processResults(String jsonData) { // I pass the process Results, as the callback, in the game activity

        try {
                JSONObject quoteJSON = new JSONObject(jsonData);
                String quoteString = quoteJSON.getString("quote");
                String characterString = quoteJSON.getString("character");
                quote = new Quote(quoteString, characterString);
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
      return quote;
    }

}
