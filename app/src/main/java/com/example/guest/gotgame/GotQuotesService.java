package com.example.guest.gotgame;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GotQuotesService {

    public static void retrieveQuotes(Callback callback) { // this is the callback will execute when our API request receives a readable response from Yelp
        String url = "https://got-quotes.herokuapp.com/quotes";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback); // here the callback will be executed when I receive the response
    }
}
