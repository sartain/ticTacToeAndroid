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
        updateBoxWhenClicked()
    }

    private fun updatePlayer() {
        turnTextView.text = getString(
            when (controller.getCurrentPlayer()) {
                TicTacToeBoard.PlayerType.X -> R.string.x_turn
                TicTacToeBoard.PlayerType.O -> R.string.y_turn
            }
        )
    }

    private fun updateBoxWhenClicked() {
        boardItems[0][1].setOnClickListener {boardItems[0][1].text = "X"}
    }

    val controller = TicTacToeBoard()
    val titleTextView: TextView by lazy {
        findViewById(R.id.title)
    }
    val turnTextView: TextView by lazy {
        findViewById(R.id.playerTurn)
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