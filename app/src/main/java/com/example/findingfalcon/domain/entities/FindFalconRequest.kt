package com.example.findingfalcon.domain.entities

import com.google.gson.annotations.SerializedName

data class FindFalconRequest(
    @SerializedName("token")
    var token: String,
    @SerializedName("planet_names")
    var planet_names: ArrayList<String>,
    @SerializedName("vehicle_names")
    var vehicle_names: ArrayList<String>,
    var vehicle_map: MutableMap<String, Int>? = null,
    var time_taken: Int = 0
)
