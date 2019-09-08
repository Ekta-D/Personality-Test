package com.android.personalitytest;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

import com.android.personalitytest.Adapter.ExpandableListAdapter;
import com.android.personalitytest.Core.GetDataContract;
import com.android.personalitytest.Core.Intractor;
import com.android.personalitytest.Core.Presenter;
import com.android.personalitytest.Model.Questions;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class UnitTesting {

    private Presenter presenter;
    MainActivity activity;
    ExpandableListView expandableListView;

    @Before
    public void setUpPresenter() {
        MockitoAnnotations.initMocks(this);
        expandableListView = Mockito.mock(ExpandableListView.class);
        activity = Mockito.mock(MainActivity.class);
        presenter = new Presenter(activity);
        assertNotNull(presenter);
    }


    @Test
    public void checkView() {
        GetDataContract.View mGetView = mock(GetDataContract.View.class);
        assertNotNull(mGetView);


    }


}
