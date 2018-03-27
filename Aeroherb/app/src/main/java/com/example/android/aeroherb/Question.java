package com.example.android.aeroherb;

/**
 * Created by Arrow on 23-03-2018.
 */

public class Question {
    public String name;
    public String que;
    public String key;

    public Question(String name, String que, String key) {
        this.name = name;
        this.que = que;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getQue() {
        return que;
    }

    public String getKey() {
        return key;
    }

    public Question(){

    }



}
