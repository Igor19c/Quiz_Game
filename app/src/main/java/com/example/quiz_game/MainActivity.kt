package com.example.quiz_game

import android.content.Intent
import com.example.quiz_game.databinding.ActivityMainBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val questions: List<Question> = QuizManager.getQuestion().shuffled()
    private var currentQuestionIndex = 0
    private var score = 0

    private var lives = 3
    private lateinit var hearts: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hearts = listOf(
            binding.life1,
            binding.life2,
            binding.life3
        )

        setupClickListeners()
        displayQuestion(questions[currentQuestionIndex])
    }

    private fun setupClickListeners() {
        binding.answer1.setOnClickListener { onAnswerClicked(0) }
        binding.answer2.setOnClickListener { onAnswerClicked(1) }
        binding.answer3.setOnClickListener { onAnswerClicked(2) }
        binding.answer4.setOnClickListener { onAnswerClicked(3) }
    }

    private fun displayQuestion(question: Question) {
        binding.questionImage.setImageResource(question.imageResId)
        binding.questionView.text = getString(question.textResId)
        binding.answer1.text = question.answers[0]
        binding.answer2.text = question.answers[1]
        binding.answer3.text = question.answers[2]
        binding.answer4.text = question.answers[3]

        binding.progressBar.max = questions.size
        binding.progressBar.progress = currentQuestionIndex + 1

        binding.scoreTextView.text = String.format("%02d", score)

        enableButtonClicks()
    }

    private fun onAnswerClicked(selectedAnswerIndex: Int) {
        disableButtonClicks()

        val currentQuestion = questions[currentQuestionIndex]

        if (selectedAnswerIndex == currentQuestion.correctAnswerId) {
            score++
            binding.scoreTextView.text = String.format("%02d", score)
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show()
            lives--
            updateHearts()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            handleNextQuestion()
            }, 1200)
    }

    private fun updateHearts() {
        if (lives < 3) {
            hearts[2].setImageResource(R.drawable.ic_heart_white_vector)
        }
        if (lives < 2) {
            hearts[1].setImageResource(R.drawable.ic_heart_white_vector)
        }
        if (lives < 1) {
            hearts[0].setImageResource(R.drawable.ic_heart_white_vector)
            endGame()
        }
    }

    private fun endGame() {
        disableButtonClicks()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }, 1200)


    }
    private fun handleNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            displayQuestion(questions[currentQuestionIndex])
        } else {
            endGame()
        }
    }

    private fun disableButtonClicks() {
        binding.answer1.isClickable = false
        binding.answer2.isClickable = false
        binding.answer3.isClickable = false
        binding.answer4.isClickable = false
    }

    private fun enableButtonClicks() {
        binding.answer1.isClickable = true
        binding.answer2.isClickable = true
        binding.answer3.isClickable = true
        binding.answer4.isClickable = true
    }
}
