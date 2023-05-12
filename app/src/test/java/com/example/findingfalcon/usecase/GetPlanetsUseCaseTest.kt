package com.example.findingfalcon.usecase

import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.usecase.GetPlanetsUseCase
import com.example.findingfalcon.repository.FakeDestinationRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetPlanetsUseCaseTest {

    private lateinit var getPlanetsUseCase: GetPlanetsUseCase
    private lateinit var fakeDestinationRepository: FakeDestinationRepository

    @Before
    fun setup() {
        fakeDestinationRepository = FakeDestinationRepository()
        getPlanetsUseCase = GetPlanetsUseCase(fakeDestinationRepository)
    }

    @Test
    fun `get planets should return list of Planets`() = runBlocking {
        // Given
        val planet1 = Planet("Donlon", 100)
        val planet2 = Planet("Sebron", 400)
        val planets = listOf(planet1, planet2)

        fakeDestinationRepository.addPlanets(planets)

        // When
        val result = getPlanetsUseCase().first()

        // Then
        assertTrue(result is ApiListResponse.SUCCESS)
        assertEquals(planets, (result as ApiListResponse.SUCCESS).list)
    }

    @Test
    fun `get planets with empty list should return failure message`() = runBlocking {
        // empty list
        fakeDestinationRepository.addPlanets(emptyList())

        // When
        val result = getPlanetsUseCase().first()

        // Then
        assertTrue(result is ApiListResponse.FAILURE)
        assertEquals(FakeDestinationRepository.PLANETS_FAILURE_MESSAGE, (result as ApiListResponse.FAILURE).error)
    }
}