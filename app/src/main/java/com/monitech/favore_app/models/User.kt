package com.monitech.favore_app.models

import java.io.Serializable

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
) : Serializable