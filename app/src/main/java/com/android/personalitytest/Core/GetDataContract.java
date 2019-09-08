package com.android.personalitytest.Core;

import android.content.Context;

import com.android.personalitytest.Model.Questions;

import java.util.ArrayList;
import java.util.HashMap;

public interface GetDataContract {

    interface View {
        void onGetDataSuccess(ArrayList<String> mCategories, HashMap<String, ArrayList<Questions>> mQues);

        void onGetDataFailure(String message);
    }


    interface Presenter{
        void getDatafromServer(Context context);
    }
    interface Interactor{
        void initFirebaseConnection(Context context);

    }

    interface onGetDataListener {
        void onSuccess(String message, ArrayList<String> mCategories, HashMap<String, ArrayList<Questions>> mQues);

        void onFailure(String message);
    }

    interface onDataChange{
        void onDataupdate(HashMap<String,String>ques_ans);
    }
}
