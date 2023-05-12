package com.example.findingfalcon.repository

import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.entities.Vehicle
import com.example.findingfalcon.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDestinationRepository: DestinationRepository {

    companion object {
        const val PLANETS_FAILURE_MESSAGE = "Failed to fetch planets"
        const val VEHICLES_FAILURE_MESSAGE = "Failed to fetch vehicles"
    }

    private val _planets: MutableList<Planet> = mutableListOf()
    private val _vehicles: MutableList<Vehicle> = mutableListOf()

    fun addPlanets(planets: List<Planet>) {
        _planets.addAll(planets)
    }

    fun addVehicles(vehicles: List<Vehicle>) {
        _vehicles.addAll(vehicles)
    }

    override suspend fun getPlanets(): Flow<ApiListResponse<Planet>> {
        return flow {
            _planets.let {
                if(it.isEmpty()) {
                    emit(ApiListResponse.FAILURE(PLANETS_FAILURE_MESSAGE))
                }
                emit(ApiListResponse.SUCCESS(it))
            }
        }
    }

    override suspend fun getVehicles(): Flow<ApiListResponse<Vehicle>> {
        return flow {
            _vehicles.let {
                if(it.isEmpty()) {
                    emit(ApiListResponse.FAILURE(VEHICLES_FAILURE_MESSAGE))
                }
                emit(ApiListResponse.SUCCESS(it))
            }
        }
    }
}