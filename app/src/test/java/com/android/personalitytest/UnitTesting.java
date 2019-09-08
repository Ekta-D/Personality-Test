package com.android.personalitytest;

import com.android.personalitytest.Core.Presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;


public class UnitTesting {

    private Presenter presenter;
    MainActivity activity;

    @Before
    public void setUpPresenter() {
        activity = new MainActivity();
        presenter = new Presenter(activity);
        assertNotNull(presenter);
    }

    @Test
    public void checkPresenterResponse() throws Exception {

    }
}
