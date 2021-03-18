package com.example.uktask


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.uktask.R
import com.example.uktask.feature.splash.SplashActivity
import kotlinx.coroutines.delay

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class WeatherAppTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun weatherAppTest() {
        val recyclerView = onView(
allOf(withId(R.id.rv_weather),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        Thread.sleep(5000)
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        
        val appCompatImageButton = onView(
allOf(withContentDescription("Navigate up"),
childAtPosition(
allOf(withId(R.id.toolbar_layout),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
2),
isDisplayed()))
        appCompatImageButton.perform(click())
        
        val recyclerView2 = onView(
allOf(withId(R.id.rv_weather),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
