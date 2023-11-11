package com.monitech.favore_app.models

data class Contract (
    val contract_id: Int?,
    val description: String,
    val status: String,
    val paymentMethod: String,
    val ammount: Double,
    val category: Category,
    val freelancer: User?,
    val client: User,
    val post: Post,
    val createdAt: String? = null,
)