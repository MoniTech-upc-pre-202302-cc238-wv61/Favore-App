package com.monitech.favore_app.dto

data class UserCreateDTO (
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val imageUrl: String,
    val active: Boolean,
    val type: String
)