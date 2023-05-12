package com.example.findingfalcon.domain.repository

import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.entities.Vehicle
import kotlinx.coroutines.flow.Flow

interface DestinationRepository {
    suspend fun getPlanets(): Flow<ApiListResponse<Planet>>
    suspend fun getVehicles(): Flow<ApiListResponse<Vehicle>>
}