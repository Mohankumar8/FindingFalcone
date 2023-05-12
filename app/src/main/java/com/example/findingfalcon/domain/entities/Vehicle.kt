package com.example.findingfalcon.domain.entities

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("name")
    val name: String,
    @SerializedName("total_no")
    var total_no: Int,
    @SerializedName("max_distance")
    val max_distance: Int,
    @SerializedName("speed")
    val speed: Int,
    val isSelected: Boolean = false
)
