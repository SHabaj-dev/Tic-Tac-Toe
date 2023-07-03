package com.example.tictactoe

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class WinDialogBox(context: Context, private val message: String, private val mainActivity: MainActivity) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.win_layout_dialogue)

        val messageText = findViewById<TextView>(R.id.tv_messageText)
        val playAgainButton = findViewById<Button>(R.id.btn_playAgain)

        messageText.text = message
        playAgainButton.setOnClickListener {
            mainActivity.rePlay()
            dismiss()
        }
    }
}
