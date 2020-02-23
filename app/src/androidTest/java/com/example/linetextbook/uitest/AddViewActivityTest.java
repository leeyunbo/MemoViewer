package com.example.linetextbook.uitest;

import androidx.test.rule.ActivityTestRule;

import com.example.linetextbook.R;
import com.example.linetextbook.view.AddViewActivity;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * AddViewActivity에 대한 UI Test
 *
 * @author 이윤복
 * @version 1.0
 */

public class AddViewActivityTest {
    @Rule
    public final ActivityTestRule<AddViewActivity> mActivityRule =
            new ActivityTestRule<>(AddViewActivity.class,false,true);


    @Test
    public void AddViewIsShown() {
        onView(withId(R.id.add_camera_image_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.add_album_image_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.add_content_edit)).check(matches(isDisplayed()));
        onView(withId(R.id.add_imageList)).check(matches(isDisplayed()));
        onView(withId(R.id.add_title_edit)).check(matches(isDisplayed()));
    }
}
