package com.android.personalitytest;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.android.personalitytest.Adapter.ExpandableListAdapter;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
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

        // Check that list adapter is set and views populated
        final ExpandableListView expandableListView = (ExpandableListView) mainActivityActivityTestRule.getActivity().findViewById(R.id.categories_list);
        assertNotNull(expandableListView);
        Thread.sleep(8000);
        assertNotNull(expandableListView.getAdapter());
        assertNotSame(0, expandableListView.getAdapter().getCount());
        onData(anything()).inAdapterView(withId(R.id.categories_list)).atPosition(0).perform(click());
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
