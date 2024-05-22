package com.example.mathquiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mathquiz.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var gameBinding: ActivityGameBinding
    private var randomA: Int = 0
    private var randomB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(gameBinding.root)

        generateNewQuestion()

        gameBinding.checkBtn.setOnClickListener {
            checkResult()
        }

        gameBinding.nextBtn.setOnClickListener {
            generateNewQuestion()
        }
    }

    private fun generateNewQuestion() {
        randomA = Random.nextInt(100)
        randomB = Random.nextInt(100)

        gameBinding.firstTextView.text = randomA.toString()
        gameBinding.secondTextView.text = randomB.toString()
        gameBinding.resultInputText.text?.clear() // Clear the input field
        gameBinding.correctorIncorrect.text = ""
    }

    private fun checkResult() {
        val userInput = gameBinding.resultInputText.text.toString().trim() // Convert Editable! to String

        if (userInput.isEmpty()) {
            gameBinding.correctorIncorrect.text = "Please enter an answer"
            return // Exit the function if no input is provided
        }

        val sum = randomA + randomB
        val userAnswer = userInput.toIntOrNull()

        if (userAnswer != null) {
            if (userAnswer == sum) {
                gameBinding.correctorIncorrect.text = "Correct answer!"
            } else {
                gameBinding.correctorIncorrect.text = "Incorrect answer. Try again."
            }
        } else {
            gameBinding.correctorIncorrect.text = "Invalid input. Please enter a number."
        }
    }

}
