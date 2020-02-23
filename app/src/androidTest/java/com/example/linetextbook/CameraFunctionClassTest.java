package com.example.linetextbook;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
/**
 * CameraFunction Class의 메서드에 대한 테스트 코드
 *
 * @author 이윤복
 * @version 1.0
 */

@RunWith(AndroidJUnit4ClassRunner.class)
public class CameraFunctionClassTest {
    private CameraFunction mCameraFunction;
    private Context mContext;

    @Before
    public void init() {
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mCameraFunction = new CameraFunction(mContext);
    }

    @Test
    public void testCreateImageFile() {
        assertNotNull(mCameraFunction.createImageFile());
    }
}
