package com.ronstruempf.ronstruempfgreeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Test MainActivity
 *
 * Created by Ron on 3/1/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testWhenSendStringSyncShouldSetString() {
        MainActivity activity = getActivity();
        final EditText nameEditText = (EditText)activity.findViewById(R.id.greet_edit_text);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");
        String actualText = nameEditText.getText().toString();
        assertEquals("Jake", actualText);
    }

    public void testGreet() {
        MainActivity activity = getActivity();
        //
        // set the value of the name edit field
        //
        final EditText nameEditText = (EditText)activity.findViewById(R.id.greet_edit_text);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        //
        // Tap the Greet button
        //
        Button greetButton = (Button)activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
        //
        // The display field should now display a greeting to Jake
        //
        TextView greetMessage = (TextView)activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }

    public void testWhenCreatedReverseShouldBeDisabled() {
        MainActivity activity = getActivity();
        Button reverseButton = (Button)activity.findViewById(R.id.reverse_button);
        assertEquals(reverseButton.isEnabled(),false);
    }

    public void testWhenGreetClickedReverseShouldBeEnabled() {
        MainActivity activity = getActivity();
        Button reverseButton = (Button)activity.findViewById(R.id.reverse_button);
        //
        // Tap the Greet button
        //
        Button greetButton = (Button)activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);
        assertEquals(reverseButton.isEnabled(),true);
    }

    public void testWhenGreetAndReverseShouldHaveReverseGreeting() {
        MainActivity activity = getActivity();

        // set name field to "Jake"
        final EditText nameEditText = (EditText)activity.findViewById(R.id.greet_edit_text);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        // simulate a click to the Greet button
        Button greetButton = (Button)activity.findViewById(R.id.greet_button);
        TouchUtils.clickView(this, greetButton);

        // then click the Reverse button
        Button reverseButton = (Button)activity.findViewById(R.id.reverse_button);
        TouchUtils.clickView(this, reverseButton);

        // greeting should now be "Hello, Jake!" backwards ("!ekaJ ,olleH")
        TextView greetMessage = (TextView)activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);
    }
}
