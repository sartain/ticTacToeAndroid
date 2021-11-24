package com.example.tictactoe

import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//SOLID principles
//open close part of code in kotlin
//Single Responsibility most important (classes and functions)

/**
 * ToDo:
 *   - Has 9 empty spaces: Done
 *   - Play a 'X' or 'O' in an empty space:
 *      -Can play 'X' at (0,0) first move: Done
 *      -Cannot play 'O' at (0,0) second move: Done
 *      -Can play 'O' at (2,2) second move: Done
 *   - Alternate between 'X' and 'O' player: Done
 *      -Can play 'X' at (1,1) third move: Done
 *   - Shows either draw / victor: Done
 *      -When board is not full and no same three characters in a row game is playable: Done
 *      -When board is full and no same three characters in a row call game a draw: Done
 *      -Three of same characters in a row call game a win: Done
 *
 */
class TicTacToeBoardTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `when created has nine empty spaces`() {
        //given
        val ticTacToeBoard = TicTacToeBoard()
        //when:
        //then:
        assertEquals(9, ticTacToeBoard.emptySpaces)
    }

    @Test
    fun `given empty board when play x at (0,0) then written to board and board is still playable`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        val gameState = testSubject.playMoveAtPosition(0, 0)
        //then:
        (0..2).forEach { r ->
            (0..2).forEach { c ->
                if (r == 0 && c == 0) {
                    assertEquals('X', testSubject.getCharacterAtPosition(0, 0))
                } else {
                    assertEquals(' ', testSubject.getCharacterAtPosition(r, c))
                }
            }
        }
        assertEquals("O plays next", gameState)
    }

    @Test
    fun `given played 'x' at (0,0) then cannot play 'o' at (0,0)`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        val result1 = testSubject.playMoveAtPosition(0, 0) //'X'
        val result2 = testSubject.playMoveAtPosition(0, 0) //'O'
        //then:
        assertEquals('X', testSubject.getCharacterAtPosition(0, 0))
        assertEquals("O plays next", result1)
        assertEquals("O plays next", result2)
    }

    @Test
    fun `given played 'x' at (0,0) then can play 'O' at (2,2)`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        val result1 = testSubject.playMoveAtPosition(0, 0) //'X'
        val result2 = testSubject.playMoveAtPosition(2, 2) //'O'
        //then:
        assertEquals('X', testSubject.getCharacterAtPosition(0, 0))
        assertEquals('O', testSubject.getCharacterAtPosition(2, 2))
        assertEquals("O plays next", result1)
        assertEquals("X plays next", result2)
    }

    @Test
    fun `alternate between 'X' and 'O'`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        val result1 = testSubject.playMoveAtPosition(0, 0) //'X'
        val result2 = testSubject.playMoveAtPosition(2, 2) //'O'
        val result3 = testSubject.playMoveAtPosition(1, 0) //'X'
        val result4 = testSubject.playMoveAtPosition(0, 1) //'O'
        //then:
        assertEquals('X', testSubject.getCharacterAtPosition(0, 0))
        assertEquals('O', testSubject.getCharacterAtPosition(2, 2))
        assertEquals('X', testSubject.getCharacterAtPosition(1, 0))
        assertEquals('O', testSubject.getCharacterAtPosition(0, 1))
        assertEquals("O plays next", result1)
        assertEquals("X plays next", result2)
        assertEquals("O plays next", result3)
        assertEquals("X plays next", result4)
    }
    //Test for not playable (draw result) FULL BOARD

    @Test
    fun `given full board of values then play another move get 'draw' as game result`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        var finalResult = ""
        var finalResult2 = ""
        playDrawGame(testSubject)
        finalResult = testSubject.playMoveAtPosition(0,0)
        finalResult2 = testSubject.playMoveAtPosition(0, 0)
        //then:
        assertEquals("draw", finalResult)
        assertEquals("draw", finalResult2)
    }

    @Test
    fun `given full board of values then get 'draw' as game result`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        var finalResult = ""
        playDrawGame(testSubject)
        finalResult = testSubject.playMoveAtPosition(0,0)
        //then:
        assertEquals("draw", finalResult)
    }

    @Test
    fun `given X plays valid winning moves then get 'x wins' as game result`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        var finalResult = playXWinGame(testSubject)
        //then:
        assertEquals("X wins", finalResult)
    }

    @Test
    fun `given O plays valid winning moves then get 'O wins' as game result`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        var finalResult = playOWinGame(testSubject)
        //then:
        assertEquals("O wins", finalResult)
    }

    private fun playDrawGame(board: TicTacToeBoard) : String{
        var result = ""
        for(i in 0..GameScenarioHelper.draw_X_Index.lastIndex) {
            result = board.playMoveAtPosition(GameScenarioHelper.draw_X_Index[i], GameScenarioHelper.draw_Y_Index[i])
            println(board.toString())
        }
        return result
    }

    private fun playXWinGame(board: TicTacToeBoard) : String {
        var result = ""
        for(i in 0..GameScenarioHelper.XWin_X_Index.lastIndex) {
            result = board.playMoveAtPosition(GameScenarioHelper.XWin_X_Index[i], GameScenarioHelper.XWin_Y_Index[i])
            println(board.toString())
        }
        return result
    }

    private fun playOWinGame(board: TicTacToeBoard) : String{
        var result = ""
        for(i in 0..GameScenarioHelper.OWin_X_Index.lastIndex) {
            result = board.playMoveAtPosition(GameScenarioHelper.OWin_X_Index[i], GameScenarioHelper.OWin_Y_Index[i])
            println(board.toString())
        }
        return result
    }

    //Attempt or make sense of board and try getting draw working
    //Can breakpoint each line
}
