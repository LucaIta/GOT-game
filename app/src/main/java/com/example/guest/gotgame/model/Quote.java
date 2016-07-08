package com.example.guest.gotgame.model;

public class Quote {
    private String mQuote;
    private String mCharacter;

    public Quote(String quote, String character) {
        this.mQuote = quote;
        this.mCharacter = character;
    }

    public String getQuote() {
        return mQuote;
    }

    public String getCharacter() {
        return mCharacter;
    }
}