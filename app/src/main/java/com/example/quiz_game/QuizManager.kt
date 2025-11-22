package com.example.quiz_game

object QuizManager {

    fun getQuestion(): List<Question> {
        return listOf(
            Question(
                id = 1,
                imageResId = R.drawable._09_romania,
                textResId = R.string.question,
                answers = listOf("Moldova", "Romania", "Chad", "Belgium"),
                correctAnswerId = 1
            ),
            Question(
                id = 2,
                imageResId = R.drawable._63_japan,
                textResId = R.string.question,
                answers = listOf("Japan", "South-Korea", "Tokyo", "Vietnam"),
                correctAnswerId = 0
            ),
            Question(
                id = 3,
                imageResId = R.drawable._12_moldova,
                textResId = R.string.question,
                answers = listOf("Guinea", "Romania", "Moldova", "Andorra"),
                correctAnswerId = 2
            ),
            Question(
                id = 4,
                imageResId = R.drawable._53_cuba,
                textResId = R.string.question,
                answers = listOf("Thailand", "Israel", "Vietnam", "Cuba"),
                correctAnswerId = 3
            ),
            Question(
                id = 5,
                imageResId = R.drawable._37_jamaica,
                textResId = R.string.question,
                answers = listOf("Jamaica", "Senegal", "Ghana", "Tanzania"),
                correctAnswerId = 0
            )

        )
    }
}