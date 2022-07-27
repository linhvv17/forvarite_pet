package com.dev.forvaritepet.model

import androidx.annotation.DrawableRes

data class Pet(
    val position: Int,
    val name: String,
    @DrawableRes val picture: Int,
    val sku: String?,
    val strength: Int = 100
)