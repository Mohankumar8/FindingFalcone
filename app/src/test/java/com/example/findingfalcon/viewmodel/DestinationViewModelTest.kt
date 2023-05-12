package com.example.findingfalcon.viewmodel

import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.domain.entities.Token
import com.example.findingfalcon.domain.usecase.FindFalconeUseCase
import com.example.findingfalcon.domain.usecase.GetPlanetsUseCase
import com.example.findingfalcon.domain.usecase.GetTokenUseCase
import com.example.findingfalcon.domain.usecase.GetVehiclesUseCase
import com.example.findingfalcon.ui.viewmodel.DestinationViewModel
import com.example.findingfalcon.ui.utils.SharedPrefs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class DestinationViewModelTest {

    /*@MockK
    lateinit var getPlanetsUseCase: GetPlanetsUseCase
    lateinit var getVehiclesUseCase: GetVehiclesUseCase
    lateinit var getTokenUseCase: GetTokenUseCase
    lateinit var findFalconeUseCase: FindFalconeUseCase
    lateinit var sharedPrefs: SharedPrefs
    lateinit var viewModel: DestinationViewModel

    @Before
    fun setup() {
        getPlanetsUseCase = mockk()
        getVehiclesUseCase = mockk()
        getTokenUseCase = mockk()
        findFalconeUseCase = mockk()
        sharedPrefs = mockk()
        viewModel = DestinationViewModel(getPlanetsUseCase, getVehiclesUseCase, getTokenUseCase, findFalconeUseCase, sharedPrefs)
    }

    @Test
    fun `generate token should return string`() = runBlocking {
        val expected = sharedPrefs.getToken()
        val actual = viewModel.generateToken()

        assertEquals(actual, expected)
    }*/


}