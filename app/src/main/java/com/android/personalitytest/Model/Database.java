package com.android.personalitytest.Model;

import java.util.ArrayList;

public class Database {

    private ArrayList<Questions> mQuestions;
    private ArrayList<String> mCategories;


    public ArrayList<String> getmCategories() {
        return mCategories;
    }

    public void setmCategories(ArrayList<String> mCategories) {
        this.mCategories = mCategories;
    }

    public ArrayList<Questions> getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(ArrayList<Questions> mQuestions) {
        this.mQuestions = mQuestions;
    }


}
