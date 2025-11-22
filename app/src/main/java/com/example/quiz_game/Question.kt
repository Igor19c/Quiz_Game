package com.example.quiz_game

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Question(
    val id: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val textResId: Int,
    val answers: List<String>,
    val correctAnswerId: Int
)
