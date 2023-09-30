package com.monitech.favore_app.models

data class User(
    val id: Int?,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val createdAt: String,
    val updatedAt: String,
    val imageUrl: String,
    val active: Boolean,
    val type: String
)