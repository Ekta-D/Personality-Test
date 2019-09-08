package com.android.personalitytest.Core;

import android.content.Context;

import com.android.personalitytest.Model.Questions;

import java.util.ArrayList;
import java.util.HashMap;

public class Presenter implements GetDataContract.onGetDataListener,GetDataContract.Presenter {
    private GetDataContract.View mGetDataView;
    private Intractor mIntractor;

    public  Presenter()
    {

    }
    public Presenter(GetDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mIntractor = new Intractor(this);
    }

    @Override
    public void onSuccess(String message, ArrayList<String> mCategories, HashMap<String, ArrayList<Questions>> mQues) {
        mGetDataView.onGetDataSuccess(mCategories, mQues);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);

    }

    @Override
    public void getDatafromServer(Context context) {
        mIntractor.initFirebaseConnection(context);
    }
}
