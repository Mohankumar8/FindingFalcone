package com.example.findingfalcon.domain.usecase

import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.domain.repository.FindFalconeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindFalconeUseCase @Inject constructor(private val repository: FindFalconeRepository) {

    suspend operator fun invoke(request: FindFalconRequest): Flow<FindFalconResponse> {
        return repository.findFalcon(request)
    }
}