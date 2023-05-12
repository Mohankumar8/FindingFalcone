package com.example.findingfalcon.data.response

sealed interface ApiResponse<out T: Any> {
    data class SUCCESS<T: Any>(val data: T): ApiResponse<T>
    data class FAILURE(val error: String): ApiResponse<Nothing>
}