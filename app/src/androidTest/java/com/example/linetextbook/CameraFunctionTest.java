package com.example.linetextbook;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CameraFunctionTest {
    private CameraFunction cameraFunction;
    private Context appContext;

    @Before
    public void init() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        cameraFunction = new CameraFunction(appContext);
    }

    @Test
    public void testCreateImageFile() {
        assertNotNull(cameraFunction.createImageFile());
    }
}
