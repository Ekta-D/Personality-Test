package com.android.personalitytest;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.personalitytest.Core.Presenter;
import com.android.personalitytest.Model.Questions;
import com.android.personalitytest.Adapter.ExpandableListAdapter;
import com.android.personalitytest.Core.GetDataContract;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GetDataContract.View {


    ExpandableListView mCategoryList;
    TextView mNoDataView;
    ExpandableListAdapter adapter;
    private static String LIST_STATE = "list_state";
    private Parcelable savedRecyclerLayoutState;
    Presenter mPresenter;
    ProgressDialog progressDialog;
    ImageView mSaveInfo;
    public HashMap<String, String> mSelectedOptions = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(MainActivity.this);
        if (savedInstanceState != null) {

        }
        initialize_ids();
        mPresenter = new Presenter(this);
        mPresenter.getDatafromServer(MainActivity.this);
        mSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Every time user save added info it will create a node to server with its selected information in users node: which you can see on firebase Db

                if (mSelectedOptions != null && mSelectedOptions.size() > 0) {
                    saveUserData(mSelectedOptions);
                    if (Utils.mSelectedOptions.size() > 0) {
                        Utils.mSelectedOptions.clear();
                    }
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please select any option!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initialize_ids() {
        mCategoryList = (ExpandableListView) findViewById(R.id.categories_list);
        mNoDataView = (TextView) findViewById(R.id.no_dataview);
        mNoDataView.setVisibility(View.GONE);
        mSaveInfo = (ImageView) findViewById(R.id.save_info);
        progressDialog = new ProgressDialog(MainActivity.this);
    }


    @Override
    public void onGetDataSuccess(ArrayList<String> mCategories, HashMap<String, ArrayList<Questions>> mQues) {


        if (mCategories.size() > 0 && mQues.size() > 0) {
            adapter = new ExpandableListAdapter(MainActivity.this, mCategories, mQues, new GetDataContract.onDataChange() {
                @Override
                public void onDataupdate(HashMap<String, String> ques_ans) {
                    mSelectedOptions = ques_ans;
                }
            });
            mCategoryList.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        } else {
            mNoDataView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onGetDataFailure(String message) {
        Toast.makeText(this, getResources().getString(R.string.failure_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("list_obj", mCategoryList.onSaveInstanceState());
    }

    Parcelable listViewState;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //savedState = savedInstanceState;
        listViewState = savedInstanceState.getParcelable("list_obj");

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listViewState != null) {
            mCategoryList.onRestoreInstanceState(listViewState);
        }
    }

    private void saveUserData(HashMap<String, String> user_map) {
        // Unique node id
        String uuid = UUID.randomUUID().toString();
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.child("users").child(uuid).setValue(user_map);
    }
}
