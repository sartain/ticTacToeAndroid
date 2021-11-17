package com.example.tictactoe

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private val mainActivityRobot = MainActivityRobot()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tictactoe", appContext.packageName)
    }

    @Test
    fun whenActivityLoadsDisplaysBoardAndGameInformation() {
        mainActivityRobot.apply {
            checkTitleAppears()
            checkGridIsBlank()
            checkPlayerTurnTextAppears()
        }
    }

    @Test
    fun givenEmptyBoardWhenClickBox01AnXAppears() {
        mainActivityRobot.apply {
            checkGridIsBlank()
            clickBox01()
            checkBox01TextUpdated()
        }
    }

    @Test
    fun givenEmptyBoardWhenClickTwoUniqueBoxesOneHasXOneHasOAppear() {
        mainActivityRobot.apply {
            checkGridIsBlank()
            clickBox(0, 0)
            checkBoxText(0, 0, "X")
            clickBox(0, 1)
            checkBoxText(0, 1, "O")
        }
    }

    @Test
    fun givenEmptyBoardWhenClickTwoSameBoxesOnlyXAppears() {
        mainActivityRobot.apply {
            checkGridIsBlank()
            clickBox(0, 0)
            checkBoxText(0, 0, "X")
            clickBox(0, 0)
            checkBoxText(0, 0, "X")
        }
    }
    //Text view for who's turn
}