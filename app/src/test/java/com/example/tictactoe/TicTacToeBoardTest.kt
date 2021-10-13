package com.example.tictactoe

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

/**
 * ToDo:
 *   - Has 9 empty spaces: Done
 *   - Play a 'X' or 'O' in an empty space:
 *      -Can play 'X' at (0,0) first move: Done
 *      -Cannot play 'O' at (0,0) second move: Done
 *      -Can play 'O' at (2,2) second move: Done
 *   - Alternate between 'X' and 'O' player:
 *      -Can play 'X' at (1,1) third move: Done
 *   - Shows either draw / victor:
 *      -When board is not full and no same three characters in a row game is playable:
 *      -When board is full and no same three characters in a row call game a draw:
 *      -Three of same characters in a row call game a win:
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
        assertEquals("playable", gameState)
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
        assertEquals("playable", result1)
        assertEquals("playable", result2)
    }

    @Test
    fun `given played 'x' at (0,0) then can play 'O' at (2,2)`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        val result1 = testSubject.playMoveAtPosition(0, 0) //'X'
        val result2 = testSubject.playMoveAtPosition(2, 2) //'O'
        //then:
        assertEquals('X', testSubject.getCharacterAtPosition(0,0))
        assertEquals('O', testSubject.getCharacterAtPosition(2,2))
        assertEquals("playable", result1)
        assertEquals("playable", result2)
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
        assertEquals('X', testSubject.getCharacterAtPosition(0,0))
        assertEquals('O', testSubject.getCharacterAtPosition(2,2))
        assertEquals('X', testSubject.getCharacterAtPosition(1,0))
        assertEquals('O', testSubject.getCharacterAtPosition(0,1))
        assertEquals("playable", result1)
        assertEquals("playable", result2)
        assertEquals("playable", result3)
        assertEquals("playable", result4)
    }
    //Test for not playable (draw result) FULL BOARD

    @Test
    fun `given full board of values then get 'draw' as game result`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        var finalResult = ""
        (0..2).forEach { r ->
            (0..2).forEach { c ->
                if(r == 2 && c == 2)
                    finalResult = testSubject.playMoveAtPosition(r, c)
                testSubject.playMoveAtPosition(r, c)
            }
        }
        //then:
        assertEquals("draw", finalResult)
    }
}

class TicTacToeBoard {
    val board = arrayOf(arrayOf(BLANK, BLANK, BLANK), arrayOf(BLANK, BLANK, BLANK), arrayOf(BLANK, BLANK, BLANK))
    val emptySpaces = board[0].size + board[1].size + board[2].size
    var playX = true

    fun playMoveAtPosition(x: Int, y: Int) :String {
        if(board[x][y] != BLANK) return "playable"
        board[x][y] = if(playX) X else O
        playX = !playX
        return "playable"
    }

    fun getCharacterAtPosition(x: Int, y: Int) = board[x][y]

    companion object{
        private const val BLANK = ' '
        private const val X = 'X'
        private const val O = 'O'
    }
}
