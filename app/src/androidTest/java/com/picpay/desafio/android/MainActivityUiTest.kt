package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.presentation.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainActivityUiTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {

        activityRule.scenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            val totalItems = recyclerView.adapter?.itemCount ?: 0

            assertEquals(50, totalItems)
        }

    }


}