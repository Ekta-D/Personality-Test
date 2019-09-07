package com.android.personalitytest.Core;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.android.personalitytest.Model.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intractor implements GetDataContract.Interactor {
    private GetDataContract.onGetDataListener mOnGetDatalistener;
    private DatabaseReference databaseReference;
    HashMap<String, ArrayList<Questions>> mQues = new HashMap<>();
    ArrayList<String> header;
    ProgressDialog progressDialog;

    public Intractor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;

    }


    @Override
    public void initFirebaseConnection(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("questions");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> questionsArrayList = new ArrayList<>();
                questionsArrayList.add(dataSnapshot.getValue());
                System.out.println(questionsArrayList);
                mQues = retrieveData(questionsArrayList);
                if (mQues.size() > 0) {
                    progressDialog.hide();
                    header = new ArrayList<>();
                    header.add("hard_fact");
                    header.add("lifestyle");
                    header.add("introversion");
                    header.add("passion");
                    mOnGetDatalistener.onSuccess("Success", header, mQues);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("firebaserror" + databaseError);
            }
        });


    }

    private HashMap<String, ArrayList<Questions>> retrieveData(ArrayList<Object> data) {
        HashMap<String, ArrayList<Questions>> mQuesMap = new HashMap<>();
        ArrayList<HashMap<Object, Object>> objects = (ArrayList<HashMap<Object, Object>>) data.get(0);
        System.out.println(objects);
        ArrayList<Questions> mPassonList = new ArrayList<>();
        ArrayList<Questions> mLifestylelist = new ArrayList<>();
        ArrayList<Questions> mIntroversionList = new ArrayList<>();
        ArrayList<Questions> mHardFacts = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            HashMap<String, ArrayList<Questions>> map = new HashMap<>();
            String headerKey = (String) objects.get(i).get("category");
            Questions questions = new Questions();
            questions.setmQues((String) objects.get(i).get("question"));
            HashMap<Object, Object> type = (HashMap<Object, Object>) objects.get(i).get("question_type");
            questions.setmQueType((String) type.get("type"));
            questions.setmOptions((ArrayList<String>) type.get("options"));
            if (headerKey.equals("hard_fact"))
                mHardFacts.add(questions);
            else if (headerKey.equals("lifestyle"))
                mLifestylelist.add(questions);
            else if (headerKey.equals("passion"))
                mPassonList.add(questions);
            else
                mIntroversionList.add(questions);

            if (headerKey.equals("hard_fact"))
                mQuesMap.put(headerKey, mHardFacts);
            else if (headerKey.equals("lifestyle"))
                mQuesMap.put(headerKey, mLifestylelist);
            else if (headerKey.equals("passion"))
                mQuesMap.put(headerKey, mPassonList);
            else
                mQuesMap.put(headerKey, mIntroversionList);
        }

        System.out.println(mQuesMap);

        return mQuesMap;
    }
}
