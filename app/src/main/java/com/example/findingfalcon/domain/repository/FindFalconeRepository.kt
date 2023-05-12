package com.example.findingfalcon.domain.repository

import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.domain.entities.Token
import kotlinx.coroutines.flow.Flow

interface FindFalconeRepository {
    suspend fun getToken(): Flow<ApiResponse<Token>>
    suspend fun findFalcon(request: FindFalconRequest): Flow<FindFalconResponse>
}