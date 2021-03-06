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

import butterknife.ButterKnife;

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
        TextView tvQuestionText = ButterKnife.findById(activity, R.id.question_text_view);
        assertThat(tvQuestionText).isNotNull();
        assertThat(tvQuestionText).containsText(R.string.question_oceans);
    }

    @Test
    public void validateTrueButtonContent() {
        Button trueButton = ButterKnife.findById(activity, R.id.true_button);
        assertThat(trueButton)
                .isNotNull()
                .containsText(R.string.true_button);
    }

    @Test
    public void testTrueButtonBehavior() {
        Button trueButton = ButterKnife.findById(activity, R.id.true_button);
        boolean[] answers = {true, false, false, true, true};
        for(boolean answer : answers) {
            trueButton.performClick();
            ShadowLooper.idleMainLooper();
            assertEquals(activity.getString((answer == true) ? R.string.correct_toast : R.string.incorrect_toast), ShadowToast.getTextOfLatestToast());
        }
    }

    @Test
    public void validateFalseButtonContent() {
        Button falseButton = ButterKnife.findById(activity, R.id.false_button);
        assertThat(falseButton)
                .isNotNull()
                .containsText(R.string.false_button);
    }

    @Test
    public void testFalseButtonBehavior() {
        Button falseButton = ButterKnife.findById(activity, R.id.false_button);
        boolean[] answers = {true, false, false, true, true};
        for(boolean answer : answers) {
            falseButton.performClick();
            ShadowLooper.idleMainLooper();
            assertEquals(activity.getString((answer == false) ? R.string.correct_toast : R.string.incorrect_toast), ShadowToast.getTextOfLatestToast());
        }
    }

    @Test
    public void validateNextButtonContent() {
        Button nextButton = ButterKnife.findById(activity, R.id.next_button);
        assertThat(nextButton)
                .isNotNull()
                .containsText(R.string.next_button);
    }

    @Test
    public void testNextButtonBehavior() {
        Button nextButton = ButterKnife.findById(activity, R.id.next_button);
        TextView tvQuestionText = ButterKnife.findById(activity, R.id.question_text_view);

        assertThat(tvQuestionText).containsText(R.string.question_oceans);
        nextButton.performClick();
        assertThat(tvQuestionText).containsText(R.string.question_mideast);
        nextButton.performClick();
        assertThat(tvQuestionText).containsText(R.string.question_africa);
        nextButton.performClick();
        assertThat(tvQuestionText).containsText(R.string.question_americas);
        nextButton.performClick();
        assertThat(tvQuestionText).containsText(R.string.question_asia);
        nextButton.performClick();
        assertThat(tvQuestionText).containsText(R.string.question_oceans);
    }
}
