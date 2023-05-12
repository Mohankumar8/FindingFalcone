package com.example.findingfalcon.data.response

sealed interface ApiListResponse<out T: Any> {
    data class SUCCESS<T: Any>(val list: List<T>): ApiListResponse<T>
    data class FAILURE(val error: String): ApiListResponse<Nothing>
}