package com.example.android.aeroherb;

/**
 * Created by Arrow on 26-03-2018.
 */

public class Answer {
    String name;
    String ans;

    public Answer(){

    }

    public Answer(String name, String ans) {
        this.name = name;
        this.ans = ans;
    }

    public String getName() {
        return name;
    }

    public String getAns() {
        return ans;
    }
}
