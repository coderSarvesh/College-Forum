package com.psp.collegeforum.Retrofit

data class Question(
    val completed: Boolean,
    var id: Int,
    val title: String,
    val userId: Int
)