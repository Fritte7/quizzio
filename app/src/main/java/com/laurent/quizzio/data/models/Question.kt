package com.laurent.quizzio.data.models

data class Question(
    val id : Int,
    val question : String,
    val url : String,
    val choice : List<String>,
    val answer : String,
    val time : Int,
    val lifeUsed : Boolean,
    val result : Boolean
)