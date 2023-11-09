package com.monitech.favore_app.models

import java.time.LocalDateTime

data class Contract (
    val contract_id: Int?,
    val description: String,
    val status: String,
    val paymentMethod: String,
    val ammount: Double,
    val category: Category,
    val freelancer: User,
    val client: User,
    val postId: Post,
    val createdAt: String? = null,
)