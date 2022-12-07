package com.ctseducare.expensebook.model

import com.ctseducare.expensebook.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Expense(
    var id: Int? = null,
    var description: String,
    var value: Double,
    @Serializable(with = InstantSerializer::class)
    var paid_at: Instant
)