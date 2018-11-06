package br.com.wnascimento.exchange

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.wnascimento.exchange.features.exchange.ExchangeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExchangeActivityTest {

    @get:Rule
    val testRule: ActivityTestRule<ExchangeActivity> =
        ActivityTestRule(ExchangeActivity::class.java, false, false)

    @Test
    fun should_open_activity_and_view_the_title() {
        testRule.launchActivity(null)
        onView(withId(R.id.appName)).check(matches(withText(R.string.app_name)))
    }


    @Test
    fun should_request_exchange_value_and_show_in_screen() {
        testRule.launchActivity(null)

        onView(withId(R.id.enterCountry)).perform(typeText("BRL"), closeSoftKeyboard())
        onView(withId(R.id.execute)).perform(click())
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.result)).check(matches(withText("1.0")))
        onView(withId(R.id.appName)).check(matches(withText(R.string.app_name)))
    }

}