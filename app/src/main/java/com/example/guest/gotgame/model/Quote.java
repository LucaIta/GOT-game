package com.example.guest.gotgame.model;

import org.parceler.Parcel;

@Parcel
public class Quote {
    public String mQuote;
    public String mCharacter;

    public Quote(String quote, String character) {
        this.mQuote = quote;
        this.mCharacter = character;
    }

    // empty constructor needed by the Parceler library
    public Quote() {
    }

    public String getQuote() {
        return mQuote;
    }

    public String getCharacter() {
        return mCharacter;
    }
}