package com.example.tictactoe

class TicTacToeBoard {
    enum class PlayerType {
        X,
        O
    }

    val board = arrayOf(
        arrayOf(BLANK, BLANK, BLANK),
        arrayOf(BLANK, BLANK, BLANK),
        arrayOf(BLANK, BLANK, BLANK)
    )
    val emptySpaces = board[0].size + board[1].size + board[2].size
    var playX = true

    override fun toString(): String {
        val result = StringBuilder()
        for (i in 0..2) {
            for (j in 0..2) {
                result.append("${board[i][j]}|")
            }
            result.append("\n")
        }
        return result.toString()
    }

    fun playMoveAtPosition(x: Int, y: Int): String {
        if (board[x][y] != BLANK) return getGameState()
        board[x][y] = if (playX) X else O
        playX = !playX
        return getGameState()
    }

    fun getCurrentPlayer(): PlayerType = if (playX) PlayerType.X else PlayerType.O

    private fun getGameState(): String {
        print(this.toString() + "\n")
        for (delta in 0..2) {
            if ((board[0][delta] != BLANK) && (board[0][delta] == board[1][delta]) && (board[1][delta] == board[2][delta])) {
                return "${board[0][delta]} wins"
            }
            if ((board[delta][0] != BLANK) && (board[delta][0] == board[delta][1]) && (board[delta][1] == board[delta][2])) {
                return "${board[delta][0]} wins"
            }
        }
        if ((board[0][0] != BLANK) && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
            return "${board[0][0]} wins"
        }
        if ((board[2][0] != BLANK) && (board[2][0] == board[1][1]) && (board[1][1] == board[0][2])) {
            return "${board[2][0]} wins"
        }
        (0..2).forEach { r ->
            (0..2).forEach { c ->
                if (board[r][c] == BLANK) return "${getCurrentPlayer()} plays next"
            }
        }
        return "draw"
    }

    fun getCharacterAtPosition(x: Int, y: Int) = board[x][y]

    companion object {
        private const val BLANK = ' '
        private const val X = 'X'
        private const val O = 'O'
    }
}