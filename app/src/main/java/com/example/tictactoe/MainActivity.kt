package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var combinationList: ArrayList<IntArray>
    private var boxPositions = IntArray(9)
    private var playerTurn = 1
    private var totalSelectedBox = 1
    private lateinit var setPlayerOneName: String
    private lateinit var setPlayerTwoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setPlayerOneName = intent.getStringExtra("playerOneName").toString()
        setPlayerTwoName = intent.getStringExtra("playerTwoName").toString()

        binding.tvSetPlayerOneName.text = setPlayerOneName
        binding.tvSetPlayerTwoName.text = setPlayerTwoName

        combinationList = ArrayList()
        combinationList.add(intArrayOf(0, 1, 2))
        combinationList.add(intArrayOf(3, 4, 5))
        combinationList.add(intArrayOf(6, 7, 8))
        combinationList.add(intArrayOf(0, 3, 6))
        combinationList.add(intArrayOf(1, 4, 7))
        combinationList.add(intArrayOf(2, 5, 8))
        combinationList.add(intArrayOf(2, 4, 6))
        combinationList.add(intArrayOf(0, 4, 8))

        binding.image1.setOnClickListener { onClickBox(0) }
        binding.image2.setOnClickListener { onClickBox(1) }
        binding.image3.setOnClickListener { onClickBox(2) }
        binding.image4.setOnClickListener { onClickBox(3) }
        binding.image5.setOnClickListener { onClickBox(4) }
        binding.image6.setOnClickListener { onClickBox(5) }
        binding.image7.setOnClickListener { onClickBox(6) }
        binding.image8.setOnClickListener { onClickBox(7) }
        binding.image9.setOnClickListener { onClickBox(8) }
    }

    private fun onClickBox(boxPosition: Int) {
        if (isBoxSelectable(boxPosition)) {
            val imageView = getImageViewForBox(boxPosition)
            performAction(imageView, boxPosition)
        }
    }

    private fun performAction(imageView: ImageView, selectedBoxPosition: Int) {
        boxPositions[selectedBoxPosition] = playerTurn

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.zero)
            if (checkPlayerWin()) {
                showWinDialog("$setPlayerOneName has Won!")
            } else if (totalSelectedBox == 9) {
                showWinDialog("It's a Draw")
            } else {
                changeTurn(2)
                totalSelectedBox++
            }
        } else {
            imageView.setImageResource(R.drawable.close)
            if (checkPlayerWin()) {
                showWinDialog("$setPlayerTwoName has Won!")
            } else if (totalSelectedBox == 9) {
                showWinDialog("It's a Draw")
            } else {
                changeTurn(1)
                totalSelectedBox++
            }
        }
    }

    private fun changeTurn(currentPlayerTurn: Int) {
        playerTurn = currentPlayerTurn

        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.player_selector_background)
            binding.playerTwoLayout.setBackgroundResource(R.drawable.player_background_blue)
        } else {
            binding.playerOneLayout.setBackgroundResource(R.drawable.player_background_blue)
            binding.playerTwoLayout.setBackgroundResource(R.drawable.player_selector_background)
        }
    }

    private fun checkPlayerWin(): Boolean {
        for (combination in combinationList) {
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn
                && boxPositions[combination[2]] == playerTurn
            ) {
                return true
            }
        }
        return false
    }

    private fun isBoxSelectable(boxPosition: Int): Boolean {
        return boxPositions[boxPosition] == 0
    }

    private fun getImageViewForBox(boxPosition: Int): ImageView {
        return when (boxPosition) {
            0 -> binding.image1
            1 -> binding.image2
            2 -> binding.image3
            3 -> binding.image4
            4 -> binding.image5
            5 -> binding.image6
            6 -> binding.image7
            7 -> binding.image8
            8 -> binding.image9
            else -> throw IllegalArgumentException("Invalid box position")
        }
    }

    private fun showWinDialog(message: String) {
        val dialog = WinDialogBox(this@MainActivity, message, this@MainActivity)
        dialog.setCancelable(false)
        dialog.show()
    }

    public fun rePlay() {
        boxPositions = IntArray(9)
        playerTurn = 1
        totalSelectedBox = 1

        val imageViews = listOf(
            binding.image1, binding.image2, binding.image3,
            binding.image4, binding.image5, binding.image6,
            binding.image7, binding.image8, binding.image9
        )

        for (imageView in imageViews) {
            imageView.setImageResource(R.drawable.background_transparent)
        }
    }
}