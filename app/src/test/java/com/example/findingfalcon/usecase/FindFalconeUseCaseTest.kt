package com.example.findingfalcon.usecase

import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.domain.usecase.FindFalconeUseCase
import com.example.findingfalcon.repository.FakeFindFalconeRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FindFalconeUseCaseTest {

    lateinit var fakeFindFalconeRepository: FakeFindFalconeRepository
    lateinit var findFalconeUseCase: FindFalconeUseCase

    @Before
    fun setup() {
        fakeFindFalconeRepository = FakeFindFalconeRepository()
        findFalconeUseCase = FindFalconeUseCase(fakeFindFalconeRepository)
    }

    @Test
    fun `find falcone returns success response`() = runBlocking {
        // Given
        val request = FindFalconRequest("token1",
            arrayListOf("Donlon", "Enchai"),
            arrayListOf("Space pod", "Space rocket")
        )
        val response = FindFalconResponse.SUCCESS("Donlon", "Success")
        fakeFindFalconeRepository.addResponse(response)

        // When
        val result = findFalconeUseCase(request).first()

        // Then
        assertTrue(result is FindFalconResponse.SUCCESS)
        assertEquals(response.status, (result as FindFalconResponse.SUCCESS).status)
        assertEquals(response.planet_name, (result as FindFalconResponse.SUCCESS).planet_name)
    }

    @Test
    fun `find falcone returns failure message`() = runBlocking {
        // Given
        val request = FindFalconRequest("token1",
            arrayListOf("Singapore", "Enchai"),
            arrayListOf("Space pod", "Space rocket")
        )
        val response = FindFalconResponse.FAILURE("Failure")
        fakeFindFalconeRepository.addResponse(response)

        // When
        val result = findFalconeUseCase(request).first()

        // Then
        assertTrue(result is FindFalconResponse.FAILURE)
        assertEquals(response.status, (result as FindFalconResponse.FAILURE).status)
    }

    @Test
    fun `find falcone with no token returns error`() = runBlocking {
        // Given
        val request = FindFalconRequest("",
            arrayListOf("Singapore", "Enchai"),
            arrayListOf("Space pod", "Space rocket")
        )
        val response = FindFalconResponse.ERROR(FakeFindFalconeRepository.FIND_FALCONE_RESPONSE_ERROR_MESSAGE)
        fakeFindFalconeRepository.addResponse(response)

        // When
        val result = findFalconeUseCase(request).first()

        // Then
        assertTrue(result is FindFalconResponse.ERROR)
        assertEquals(response.error, (result as FindFalconResponse.ERROR).error)
    }
}