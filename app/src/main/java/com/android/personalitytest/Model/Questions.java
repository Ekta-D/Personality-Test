package com.android.personalitytest.Model;

import java.util.ArrayList;

public class Questions {

    private String mQues;


    private String mQueType;

    public String getmQues() {
        return mQues;
    }

    public void setmQues(String mQues) {
        this.mQues = mQues;
    }


    public String getmQueType() {
        return mQueType;
    }

    public void setmQueType(String mQueType) {
        this.mQueType = mQueType;
    }

    public ArrayList<String> getmOptions() {
        return mOptions;
    }

    public void setmOptions(ArrayList<String> mOptions) {
        this.mOptions = mOptions;
    }

    private ArrayList<String> mOptions;
}
