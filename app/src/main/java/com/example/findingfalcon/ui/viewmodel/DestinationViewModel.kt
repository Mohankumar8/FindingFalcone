package com.example.findingfalcon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findingfalcon.data.response.ApiListResponse
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.entities.Vehicle
import com.example.findingfalcon.domain.usecase.GetPlanetsUseCase
import com.example.findingfalcon.domain.usecase.GetVehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase,
    private val getVehiclesUseCase: GetVehiclesUseCase
): ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _planets: MutableStateFlow<List<Planet>> = MutableStateFlow(emptyList())
    val planets: StateFlow<List<Planet>> = _planets.asStateFlow()

    private val _vehicles: MutableStateFlow<List<Vehicle>> = MutableStateFlow(emptyList())
    val vehicles: StateFlow<List<Vehicle>> = _vehicles.asStateFlow()

    init {
        // get planets and vehicles
        _isLoading.value = true
        viewModelScope.launch {
            try {
                getPlanetsUseCase().collect {
                    when(it) {
                        is ApiListResponse.SUCCESS -> _planets.value = it.list
                        is ApiListResponse.FAILURE -> _planets.value = emptyList()
                    }
                }

                getVehiclesUseCase().collect {
                    when(it) {
                        is ApiListResponse.SUCCESS -> _vehicles.value = it.list
                        is ApiListResponse.FAILURE -> _vehicles.value = emptyList()
                    }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}