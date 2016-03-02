package com.ronstruempf.ronstruempfgreeter;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Test MainActivity
 *
 * Created by Ron on 3/1/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() throws Exception {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

}
