package com.example.findingfalcon.data.response

import com.google.gson.annotations.SerializedName

sealed interface FindFalconResponse {
    data class FAILURE(@SerializedName("status") val status: String): FindFalconResponse
    data class ERROR(@SerializedName("error") val error: String): FindFalconResponse
    data class SUCCESS(
        @SerializedName("planet_name")
        val planet_name: String,
        @SerializedName("status")
        val status: String
    ): FindFalconResponse
}
