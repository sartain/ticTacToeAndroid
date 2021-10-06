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
 *      -Cannot play 'O' at (0,0) second move:
 *      -Can play 'O' at (2,2) second move:
 *   - Alternate between 'X' and 'O' player:
 *   - Shows either draw / victor:
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
    fun `given empty board when play x at (0,0) then written to board`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        testSubject.playMoveAtPosition(0, 0)
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

    }

    @Test
    fun `given played 'x' at (0,0) then cannot play 'o' at (0,0)`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        testSubject.playMoveAtPosition(0, 0) //'X'
        testSubject.playMoveAtPosition(0, 0) //'O'
        //then:
        assertEquals('X', testSubject.getCharacterAtPosition(0, 0))
    }

    @Test
    fun `given played 'x' at (0,0) then can play 'O' at (2,2)`() {
        //given:
        val testSubject = TicTacToeBoard()
        //when:
        testSubject.playMoveAtPosition(0, 0) //'X'
        testSubject.playMoveAtPosition(2, 2) //'O'
        //then:
        assertEquals('X', testSubject.getCharacterAtPosition(0,0))
        assertEquals('O', testSubject.getCharacterAtPosition(2,2))
    }
}

class TicTacToeBoard {
    val board = arrayOf(arrayOf(' ', ' ', ' '), arrayOf(' ', ' ', ' '), arrayOf(' ', ' ', ' '))
    val emptySpaces = board[0].size + board[1].size + board[2].size

    fun playMoveAtPosition(x: Int, y: Int) {
        board[x][y] = 'X'
    }

    fun getCharacterAtPosition(x: Int, y: Int) = board[x][y]
}
