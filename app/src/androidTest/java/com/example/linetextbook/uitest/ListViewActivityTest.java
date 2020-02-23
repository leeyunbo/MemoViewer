package com.example.linetextbook.uitest;


import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;


import com.example.linetextbook.R;
import com.example.linetextbook.view.ListViewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * ListViewActivity에 대한 UI Test
 * 만약 UI Test 진행시 IsUiTestCheck.isRunningUiThread = true
 * 만약 UI Test 진행을 하지 않는 다면 IsUiTestCheck.isRunningUiThread = false;
 * com.example.linetextbook.IsUiTestCheck
 *
 * @author 이윤복
 * @version 1.0
 */

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ListViewActivityTest {
    @Rule
    public final ActivityTestRule<ListViewActivity> mActivityRule =
            new ActivityTestRule<>(ListViewActivity.class,false,true);


    @Test
    public void ListViewIsShown() {
        int testDataCount = 1;
        onView(withId(R.id.list_memoList)).check(matches(isDisplayed()));
        onView(withId(R.id.list_memoCount)).check(matches(isDisplayed()));
        onView(withText("현재 " +testDataCount+ "개의 메모")).check(matches(isDisplayed()));
        onView(withId(R.id.list_add_Btn)).check(matches(isDisplayed()));
    }
}
