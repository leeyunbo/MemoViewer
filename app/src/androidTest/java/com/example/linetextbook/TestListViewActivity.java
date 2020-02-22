package com.example.linetextbook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoDatabase;
import com.example.linetextbook.view.ListViewActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TestListViewActivity {
    private ListViewActivity mActivity;
    private MemoDAO mMemoDAO;
    private MemoDatabase mMemoDatabase;
    private RecyclerView mRecyclerView;

    @Rule
    public final ActivityTestRule<ListViewActivity> mActivityRule =
            new ActivityTestRule<>(ListViewActivity.class);

    @Before
    public void createDataBase() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        this.mMemoDatabase = Room.inMemoryDatabaseBuilder(appContext, MemoDatabase.class).build();
        this.mMemoDAO = mMemoDatabase.memoDAO();
        this.mActivity = mActivityRule.getActivity();
        this.mRecyclerView = mActivity.findViewById(R.id.memoRecyclerView);
    }
    @Test
    public void getMemoDataToRecyclerView() {
        mActivityRule.launchActivity(null);
        onView(withId(R.id.memoCount)).check(matches(isDisplayed()));
    }

    @Test
    public void testIntentAlbum() {

    }

    @Test
    public void testIntentCamera() {

    }
}
