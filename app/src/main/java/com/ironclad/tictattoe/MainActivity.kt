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
                    updateValues(0, 0, PLAYER)
                }
                R.id.bt2 -> {
                    updateValues(0, 1, PLAYER)

                }

                R.id.bt3 -> {
                    updateValues(0, 2, PLAYER)

                }

                R.id.bt4 -> {
                    updateValues(1, 0, PLAYER)

                }
                R.id.bt5 -> {
                    updateValues(1, 1, PLAYER)

                }
                R.id.bt6 -> {
                    updateValues(1, 2, PLAYER)

                }
                R.id.bt7 -> {
                    updateValues(2, 0, PLAYER)

                }
                R.id.bt8 -> {
                    updateValues(2, 1, PLAYER)

                }
                R.id.bt9 -> {
                    updateValues(2, 2, PLAYER)

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

    private fun updateValues(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        boardStatus[row][col] = value
    }

    private fun checkWinner() {
        //Horizontal Rows
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player 1 Winner")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player 2 Winner")
                    break
                }
            }
        }


        //Vertical Columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player 1 Winner")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player 2 Winner")
                    break
                }
            }
        }

        //FirstDiagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player 1 is Winner")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player 2 is Winner")
            }
        }

        //FirstDiagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player 1 is Winner")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player 2 is Winner")
            }
        }
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
