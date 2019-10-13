package com.ironclad.tictattoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3) {
        IntArray(3)
    }

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(bt1, bt2, bt3),
            arrayOf(bt4, bt5, bt6),
            arrayOf(bt7, bt8, bt9)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        initialBoardStatus()


        btnReset.setOnClickListener {
            PLAYER = true
            TURN_COUNT = 0
            initialBoardStatus()
        }
    }

    private fun initialBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }

        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.bt1 -> {

                }
                R.id.bt2 -> {

                }

                R.id.bt3 -> {

                }

                R.id.bt4 -> {

                }
                R.id.bt5 -> {

                }
                R.id.bt6 -> {

                }
                R.id.bt7 -> {

                }
                R.id.bt8 -> {

                }
                R.id.bt9 -> {

                }
            }
        }

        TURN_COUNT++
        PLAYER = !PLAYER
        if (PLAYER) {
            updateDisplay("Player 1 Turn")
        } else {
            updateDisplay("Player 2 Turn")
        }


        if (TURN_COUNT == 9) {
            updateDisplay("Game Draw")
        }

        checkWinner()
    }


    private fun updateDisplay(s: String) {
        tvDisplay.text = s

        if (s.contains("Winner")) {
            disableButton()
        }
    }

    private fun disableButton() {
        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }
}
