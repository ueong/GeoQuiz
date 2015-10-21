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
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;

import static org.assertj.android.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by ueong on 2015-10-21.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class QuizActivityTest {
    private QuizActivity activity;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.buildActivity(QuizActivity.class).create().get();
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateQuestionTextViewContent() {
        TextView tvQuestionText = (TextView) activity.findViewById(R.id.question_text_view);
        assertThat(tvQuestionText).isNotNull();

    }

    @Test
    public void validateTrueButtonContent() {
        Button trueButton = (Button) activity.findViewById(R.id.true_button);
        assertThat(trueButton)
                .isNotNull()
                .containsText(R.string.true_button);
    }

    @Test
    public void testTrueButtonBehavior() {
        Button trueButton = (Button) activity.findViewById(R.id.true_button);
        trueButton.performClick();
        ShadowLooper.idleMainLooper();
        assertEquals(activity.getString(R.string.correct_toast), ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void validateFalseButtonContent() {
        Button falseButton = (Button) activity.findViewById(R.id.false_button);
        assertThat(falseButton)
                .isNotNull()
                .containsText(R.string.false_button);
    }

    @Test
    public void testFalseButtonBehavior() {
        Button falseButton = (Button) activity.findViewById(R.id.false_button);
        falseButton.performClick();
        ShadowLooper.idleMainLooper();
        assertEquals(activity.getString(R.string.incorrect_toast), ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void validateNextButtonContent() {
        Button nextButton = (Button) activity.findViewById(R.id.next_button);
        assertThat(nextButton)
                .isNotNull()
                .containsText(R.string.next_button);
    }
}
