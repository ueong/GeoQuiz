package com.bignerdranch.android.geoquiz;

import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContext;
import org.robolectric.shadows.ShadowHandler;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ueong on 2015-10-21.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class QuizActivityTest {
    private QuizActivity activity;
    private ActivityController<QuizActivity> controller;
    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(QuizActivity.class);
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateTextViewContent() {
        TextView tvQuestionText = (TextView) activity.findViewById(R.id.question_text);
        assertNotNull("TextView could not be found", tvQuestionText);
        assertTrue("TextView contains incorrect text",
                "question".equals(tvQuestionText.getText().toString()));
    }

    @Test
    public void validateTrueButtonContent() {
        Button trueButton = (Button) activity.findViewById(R.id.true_button);
        assertNotNull("True Button could not be found", trueButton);
        assertTrue("True button contains incorrect text", "True".equals(trueButton.getText()));

    }

    @Test
    public void testTrueButtonBehavior() {
        Button trueButton = (Button) activity.findViewById(R.id.true_button);
        trueButton.performClick();
        ShadowLooper.idleMainLooper();
        assertEquals("True Button should display Correct! toast when clicked", activity.getString(R.string.correct_toast), ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void validateFalseButtonContent() {
        Button falseButton = (Button) activity.findViewById(R.id.false_button);
        assertNotNull("False Button could not be found", falseButton);
        assertTrue("False button contains incorrect text", "False".equals(falseButton.getText()));
    }

    @Test
    public void testFalseButtonBehavior() {
        Button falseButton = (Button) activity.findViewById(R.id.false_button);
        falseButton.performClick();
        ShadowLooper.idleMainLooper();
        assertEquals("False Button should display Incorrect! toast when clicked", activity.getString(R.string.incorrect_toast), ShadowToast.getTextOfLatestToast());
    }

}
