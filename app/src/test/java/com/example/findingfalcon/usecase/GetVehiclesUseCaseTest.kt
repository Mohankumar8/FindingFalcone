package com.example.findingfalcon.usecase

import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Vehicle
import com.example.findingfalcon.domain.usecase.GetVehiclesUseCase
import com.example.findingfalcon.repository.FakeDestinationRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetVehiclesUseCaseTest {

    private lateinit var getVehiclesUseCase: GetVehiclesUseCase
    private lateinit var fakeDestinationRepository: FakeDestinationRepository

    @Before
    fun setup() {
        fakeDestinationRepository = FakeDestinationRepository()
        getVehiclesUseCase = GetVehiclesUseCase(fakeDestinationRepository)
    }

    @Test
    fun `get vehicles should return list of Vehicle`() = runBlocking {
        // Given
        val vehicle1 = Vehicle("Space pod", 2, 200, 2)
        val vehicle2 = Vehicle("Space rocket", 1, 300, 4)
        val vehicles = listOf(vehicle1, vehicle2)

        fakeDestinationRepository.addVehicles(vehicles)

        // When
        val result = getVehiclesUseCase().first()

        // Then
        assertTrue(result is ApiListResponse.SUCCESS)
        assertEquals(vehicles, (result as ApiListResponse.SUCCESS).list)
    }

    @Test
    fun `get vehicles with empty list should return failure message`() = runBlocking {
        // empty list
        fakeDestinationRepository.addPlanets(emptyList())

        // When
        val result = getVehiclesUseCase().first()

        // Then
        assertTrue(result is ApiListResponse.FAILURE)
        assertEquals(FakeDestinationRepository.VEHICLES_FAILURE_MESSAGE, (result as ApiListResponse.FAILURE).error)
    }
}