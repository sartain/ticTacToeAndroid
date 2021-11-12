package com.example.tictactoe

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

class MainActivityRobot {

    fun checkTitleAppears() {
        Espresso.onView(withId(R.id.title))  //Find id
            .check(matches(withText("")))
    }

    fun checkGridIsBlank() {
        val elements = listOf(R.id.box00, R.id.box01, R.id.box02, R.id.box10, R.id.box11, R.id.box12, R.id.box20, R.id.box21, R.id.box22)
        elements.forEach {
            element ->
                Espresso.onView(withId(element))  //Find id
                    .check(matches(withText("")))
        }
    }

    fun checkPlayerTurnTextAppears() {
        Espresso.onView(withId(R.id.playerTurn))
            .check(matches(withText(R.string.x_turn)))
    }

    fun clickBox01() {
        Espresso.onView(withId(R.id.box01))
            .perform(click())
    }

    fun checkBox01TextUpdated() {
        Espresso.onView(withId(R.id.box01))
            .check(matches(withText("X")))
    }

}