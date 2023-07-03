package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.databinding.ActivityAddPlayersBinding

class AddPlayers : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlayersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_players)

        val playerFirstName = binding.etPlayerOneName.text.toString()
        val playerSecondName = binding.etPlayerTwoName.text.toString()



        binding.btnStartGame.setOnClickListener {
            val playerFirstName = binding.etPlayerOneName.text.toString()
            val playerSecondName = binding.etPlayerTwoName.text.toString()
            if (playerFirstName.isEmpty()) {
                Toast.makeText(this, "Please Enter First Player Name!!", Toast.LENGTH_SHORT).show()
            } else if (playerSecondName.isEmpty()) {
                Toast.makeText(this, "Please Enter Second Player Name!!", Toast.LENGTH_SHORT).show()

            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("playerOneName", playerFirstName)
                intent.putExtra("playerTwoName", playerSecondName)
                startActivity(intent)
                finish()
            }
        }
    }
}