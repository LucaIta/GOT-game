package com.example.guest.gotgame.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oem on 7/22/16.
 */
public class Score {
    public String score;
    public String time;

    public Score(String score) {
        this.score = score;
        Date date = new Date();
        String time = new SimpleDateFormat("dd/MM/yyyy, hh:mm").format(date);
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }
}
