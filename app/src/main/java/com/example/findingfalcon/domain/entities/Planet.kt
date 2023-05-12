package com.example.findingfalcon.domain.entities

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("name")
    val name: String,
    @SerializedName("distance")
    val distance: Int,
    val isSelected: Boolean = false
)
