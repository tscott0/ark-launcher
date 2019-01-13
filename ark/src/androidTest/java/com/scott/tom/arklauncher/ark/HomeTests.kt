package com.scott.tom.arklauncher.ark


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTests {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun givenUserIsOnHomeScreen_whenTappingDrawerButton_thenAppDrawerOpens() {
        onView(withId(R.id.show_drawer_button)).check(matches(isDisplayed()))
        onView(withId(R.id.show_drawer_button)).perform(click())

        onView(withId(R.id.drawer_recycler_view))
    }
}
