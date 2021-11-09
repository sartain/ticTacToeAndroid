package com.example.tictactoe

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

class MainActivityRobot {

    fun checkTitleAppears() {
        Espresso.onView(withId(R.id.title))  //Find id
            .check(matches(withText("Hello World!")))
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

    //Takes in a function from main activity robot any return type
    //Then creates main activity instance and applies any given function
    fun startupMainActivity(func: MainActivityRobot.() -> Unit) = MainActivityRobot().apply {
        func()
    }
}