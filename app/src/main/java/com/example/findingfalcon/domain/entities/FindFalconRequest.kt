package com.example.findingfalcon.domain.entities

import com.google.gson.annotations.SerializedName

data class FindFalconRequest(
    @SerializedName("token")
    var token: String = "",
    @SerializedName("planet_names")
    var planet_names: MutableList<String> = mutableListOf(),
    @SerializedName("vehicle_names")
    var vehicle_names: MutableList<String> = mutableListOf(),
    var vehicle_map: MutableMap<String, Int> = mutableMapOf(),
    var time_taken: Int = 0
)
