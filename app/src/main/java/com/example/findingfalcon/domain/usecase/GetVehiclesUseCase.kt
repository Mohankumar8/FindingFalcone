package com.example.findingfalcon.domain.usecase

import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Vehicle
import com.example.findingfalcon.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehiclesUseCase @Inject constructor(private val repository: DestinationRepository) {

    suspend operator fun invoke(): Flow<ApiListResponse<Vehicle>> {
        return repository.getVehicles()
    }
}