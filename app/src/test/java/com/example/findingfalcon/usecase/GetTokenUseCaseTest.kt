package com.example.findingfalcon.usecase

import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.domain.entities.Token
import com.example.findingfalcon.domain.usecase.GetTokenUseCase
import com.example.findingfalcon.repository.FakeFindFalconeRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTokenUseCaseTest {
    private lateinit var getTokenUseCase: GetTokenUseCase
    private lateinit var findFalconeRepository: FakeFindFalconeRepository

    @Before
    fun setup() {
        findFalconeRepository = FakeFindFalconeRepository()
        getTokenUseCase = GetTokenUseCase(findFalconeRepository)
    }

    @Test
    fun `get token returns success token`() = runBlocking {
        // Given
        val expectedData = Token("Token1")
        findFalconeRepository.addToken(expectedData)

        // When
        val result = getTokenUseCase().first()

        // Then
        assertTrue(result is ApiResponse.SUCCESS)
        assertEquals(expectedData.token, (result as ApiResponse.SUCCESS).data.token)
    }

    @Test
    fun `get token returns failure message`() = runBlocking {
        // Given
        val expectedData = Token("")
        findFalconeRepository.addToken(expectedData)

        // When
        val result = getTokenUseCase().first()

        // Then
        assertTrue(result is ApiResponse.FAILURE)
        assertEquals(FakeFindFalconeRepository.TOKEN_FAILURE_MESSAGE, (result as ApiResponse.FAILURE).error)
    }
}