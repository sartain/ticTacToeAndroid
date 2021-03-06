package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

//Discuss synthetics next week research.
/*
Synthetics aims to replace findViewById with synthetic bindings.
This has since been replaced by view bindings.
Aim is to access views in layout XML as if they were properties
e.g. no findViewByID but using the id name to access
Required a special import in synthetics import kotlinx.android.synthetic.main.activity_main.*
View Binding
add viewBinding { enabled = true } to android {} in the build.gradle file
This enables view binding class for each layout xml to be used
ViewBinding Class for each layout xml
Only for values in the xml with attribute ID's
A viewbinding is inflated in the oncreate method
Instance of the inflation can be used to reference any of the views
binding.box00.text = 'X' for example or binding.box00.setOnClickListener { viewModel.positionClicked }
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updatePlayer()
        updateBoardWhenSquareClicked()
    }

    private fun updatePlayer() {
        gameProgressTextView.text = getString(
            when (controller.getCurrentPlayer()) {
                TicTacToeBoard.PlayerType.X -> R.string.x_turn
                TicTacToeBoard.PlayerType.O -> R.string.o_turn
            }
        )
    }

    private fun TicTacToeBoard.PlayerType.toDisplayText(): String =
        getString(
            when (this) {
                TicTacToeBoard.PlayerType.X -> R.string.x_player
                TicTacToeBoard.PlayerType.O -> R.string.o_player
            }
        )

    //Inside listener
    //Gets current move, if this change after move made (is a valid move) else don't overwrite
    private fun updateBoardWhenSquareClicked() {
        for (boardList in boardItems) {
            for (board in boardList) {
                board.setOnClickListener {
                    val moveToPlay = controller.getCurrentPlayer().toDisplayText()
                    playMoveAndUpdateText(boardItems.indexOf(boardList), boardList.indexOf(board))
                    if (moveToPlay != controller.getCurrentPlayer().toDisplayText()) {  //Check to stop overwriting
                        board.text = moveToPlay
                    }
                }
            }
        }
    }

    private fun playMoveAndUpdateText(x: Int, y: Int) {
        val gameState = controller.playMoveAtPosition(x, y)
        updateGameProgressText(gameState)
    }

    private fun updateGameProgressText(gameState: String) {
        if(gameState.endsWith("plays next")) {
            setGameProgressTextToPlayerTurn()
        }
        else {
            setGameProgressTextToFinalResult(gameState)
        }
    }

    private fun setGameProgressTextToFinalResult(gameResult: String) {
        gameProgressTextView.text = getString(
            when (gameResult) {
                "draw" -> R.string.game_draw
                "X wins" -> R.string.x_wins
                "O wins" -> R.string.o_wins
                else -> R.string.app_name
            }
        )
    }

    private fun setGameProgressTextToPlayerTurn() {
        val player = controller.getCurrentPlayer()
        if(player == TicTacToeBoard.PlayerType.O)
            gameProgressTextView.text = getString(R.string.o_turn)
        else
            gameProgressTextView.text = getString(R.string.x_turn)
    }

    val controller = TicTacToeBoard()

    val titleTextView: TextView by lazy {
        findViewById(R.id.title)
    }
    val gameProgressTextView: TextView by lazy {
        findViewById(R.id.gameProgress)
    }
    val boardItems: List<List<TextView>> by lazy {
        listOf(
            listOf(
                findViewById(R.id.box00),
                findViewById(R.id.box01),
                findViewById(R.id.box02)
            ),
            listOf(
                findViewById(R.id.box10),
                findViewById(R.id.box11),
                findViewById(R.id.box12)
            ),
            listOf(
                findViewById(R.id.box20),
                findViewById(R.id.box21),
                findViewById(R.id.box22)
            )
        )
    }
}