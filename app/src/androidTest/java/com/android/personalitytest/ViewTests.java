package com.android.personalitytest;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ViewTests {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void ensureListIsPresent() throws Exception {
        View view = mainActivity.findViewById(R.id.categories_list);
        assertThat(view, notNullValue());
        assertThat(view, instanceOf(ExpandableListView.class));

    }

    @Test
    public void checkSaveButton() throws Throwable {
        View view = mainActivity.findViewById(R.id.save_info);
        assertThat(view, notNullValue());
    }

    @After
    public void teardown() throws Exception {
        mainActivity = null;
    }
}
