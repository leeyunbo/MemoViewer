package com.example.linetextbook.uitest;

import androidx.test.rule.ActivityTestRule;

import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.view.EditViewActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * EditViewActivity 대한 UI Test
 * 만약 UI Test 진행시 IsUiTestCheck.isRunningUiThread = true
 * 만약 UI Test 진행을 하지 않는 다면 IsUiTestCheck.isRunningUiThread = false;
 * com.example.linetextbook.IsUiTestCheck
 *
 * @author 이윤복
 * @version 1.0
 */

public class EditViewActivityTest {
    private MemoEntity testMemo;
    @Rule
    public final ActivityTestRule<EditViewActivity> mActivityRule =
            new ActivityTestRule<>(EditViewActivity.class,false,true);

    @Before
    public void init() {
        testMemo = new MemoEntity("제목","내용","시간",null);
    }

    @Test
    public void EditViewIsShown() {
        onView(withId(R.id.edit_album_image_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_camera_image_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_content_edit)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_finish_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_imageList)).check(matches(isDisplayed()));
        onView(withText(testMemo.getContent())).check(matches(isDisplayed()));
        onView(withText(testMemo.getTitle())).check(matches(isDisplayed()));
    }
}
