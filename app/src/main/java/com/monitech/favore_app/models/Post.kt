package com.monitech.favore_app.models

data class Post (
    val title:String,
    val description: String,
    val keywords: List<String>,
    val budgetAmount: Double
)