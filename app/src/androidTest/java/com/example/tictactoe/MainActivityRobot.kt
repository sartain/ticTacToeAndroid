package com.example.tictactoe

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import scenario.GameScenarioHelper

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
        Espresso.onView(withId(R.id.gameProgress))
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

    fun clickBox(x: Int, y: Int) {
        when (x) {
            0 -> when (y) {
                0 -> R.id.box00.clickView()
                1 -> R.id.box01.clickView()
                2 -> R.id.box02.clickView()
                else -> return
            }
            1 -> when (y) {
                0 -> R.id.box10.clickView()
                1 -> R.id.box11.clickView()
                2 -> R.id.box12.clickView()
                else -> return
            }
            2 -> when (y) {
                0 -> R.id.box20.clickView()
                1 -> R.id.box21.clickView()
                2 -> R.id.box22.clickView()
                else -> return
            }
            else -> return
        }
    }

    fun checkGameProgressText(currentResult: String) {
        when (currentResult) {
            "x" -> R.id.gameProgress.checkText("X plays next")
            "o" -> R.id.gameProgress.checkText("O plays next")
            "draw" -> R.id.gameProgress.checkText("draw")
            "x win" -> R.id.gameProgress.checkText("X wins")
            "o win" -> R.id.gameProgress.checkText("O wins")
        }
    }

    fun checkBoxText(x: Int, y: Int, text: String) {
        when (x) {
            0 -> when (y) {
                0 -> R.id.box00.checkText(text)
                1 -> R.id.box01.checkText(text)
                2 -> R.id.box02.checkText(text)
                else -> return
            }
            1 -> when (y) {
                0 -> R.id.box10.checkText(text)
                1 -> R.id.box11.checkText(text)
                2 -> R.id.box12.checkText(text)
                else -> return
            }
            2 -> when (y) {
                0 -> R.id.box20.checkText(text)
                1 -> R.id.box21.checkText(text)
                2 -> R.id.box22.checkText(text)
                else -> return
            }
            else -> return
        }
    }

    fun playGame(XIndexArray: IntArray, YIndexArray: IntArray) {
        for(i in 0..XIndexArray.lastIndex) {
            clickBox(XIndexArray[i], YIndexArray[i])
        }
    }

    fun playDrawGame() {
        playGame(GameScenarioHelper.draw_X_Index, GameScenarioHelper.draw_Y_Index)
    }

    fun playXWinGame() {
        playGame(GameScenarioHelper.XWin_X_Index, GameScenarioHelper.XWin_Y_Index)
    }

    fun playOWinGame() {
        playGame(GameScenarioHelper.OWin_X_Index, GameScenarioHelper.OWin_Y_Index)
    }

    fun Int.checkText(text: String) {
        Espresso.onView(withId(this))
            .check(matches(withText(text)))
    }

    fun Int.clickView() {
        Espresso.onView(withId(this))
            .perform(click())
    }
}