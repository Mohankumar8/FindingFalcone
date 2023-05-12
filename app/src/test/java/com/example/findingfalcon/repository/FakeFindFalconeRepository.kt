package com.example.findingfalcon.repository

import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.domain.entities.Token
import com.example.findingfalcon.domain.repository.FindFalconeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFindFalconeRepository: FindFalconeRepository {

    companion object {
        const val TOKEN_FAILURE_MESSAGE = "Failed to generate token"
        const val FIND_FALCONE_RESPONSE_ERROR_MESSAGE = "Token not initialised. Please get a new token."
    }

    lateinit var token: Token
    lateinit var response: FindFalconResponse

    fun addToken(token: Token) {
        this.token = token
    }

    fun addResponse(response: FindFalconResponse) {
        this.response = response
    }

    override suspend fun getToken(): Flow<ApiResponse<Token>> {
        return flow {
            if(token.token.isEmpty()) {
                emit(ApiResponse.FAILURE(TOKEN_FAILURE_MESSAGE))
            } else {
                emit(ApiResponse.SUCCESS(token))
            }
        }
    }

    override suspend fun findFalcon(request: FindFalconRequest): Flow<FindFalconResponse> {
        return flow {
            emit(response)
        }
    }
}