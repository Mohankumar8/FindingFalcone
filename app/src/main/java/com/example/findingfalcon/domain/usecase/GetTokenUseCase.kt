package com.example.findingfalcon.domain.usecase

import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.domain.entities.Token
import com.example.findingfalcon.domain.repository.FindFalconeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val repository: FindFalconeRepository) {

    suspend operator fun invoke(): Flow<ApiResponse<Token>> {
        return repository.getToken()
    }
}