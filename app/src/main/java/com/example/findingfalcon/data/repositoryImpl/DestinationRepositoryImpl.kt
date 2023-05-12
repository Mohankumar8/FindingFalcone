package com.example.findingfalcon.data.repositoryImpl

import com.example.findingfalcon.data.utils.ApiEndPoints
import com.example.findingfalcon.data.remote.ApiService
import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.entities.Vehicle
import com.example.findingfalcon.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(private val apiService: ApiService): DestinationRepository {

    override suspend fun getPlanets(): Flow<ApiListResponse<Planet>> {
        return flow {
            val result = apiService.getPlanets(ApiEndPoints.BASE_URL + ApiEndPoints.END_POINT_PLANETS)
            if(result.isSuccessful) {
                result.body()?.let { emit(ApiListResponse.SUCCESS(it)) }
            } else {
                emit(ApiListResponse.FAILURE(result.message()))
            }
        }
    }

    override suspend fun getVehicles(): Flow<ApiListResponse<Vehicle>> {
        return flow {
            val result = apiService.getVehicles()
            if(result.isSuccessful) {
                result.body()?.let { emit(ApiListResponse.SUCCESS(it)) }
            } else {
                emit(ApiListResponse.FAILURE(result.message()))
            }
        }
    }
}