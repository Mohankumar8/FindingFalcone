package com.example.findingfalcon.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.domain.entities.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
open class SharedViewModel @Inject constructor(): ViewModel() {

    private val _falconeRequest = MutableStateFlow(FindFalconRequest())
    val falconeRequest: StateFlow<FindFalconRequest> = _falconeRequest.asStateFlow()

    fun setToken(token: String) {
        _falconeRequest.value.token = token
    }

    fun updateFindFalconeRequest(planetName: String, vehicleName: String, timeTaken: Int, add: Boolean) {
        val map: MutableMap<String, Int> = _falconeRequest.value.vehicle_map
        if(add) {
            _falconeRequest.value.planet_names.add(planetName)
            _falconeRequest.value.vehicle_names.add(vehicleName)
            _falconeRequest.value.time_taken = _falconeRequest.value.time_taken + timeTaken
            if(map.containsKey(vehicleName)) {
                map[vehicleName]?.let { map.put(vehicleName, it + 1) }
            } else {
                map[vehicleName] = 1
            }
        } else {
            _falconeRequest.value.planet_names.remove(planetName)
            _falconeRequest.value.vehicle_names.remove(vehicleName)
            _falconeRequest.value.time_taken =- timeTaken
            if(map.containsKey(vehicleName)) {
                map[vehicleName]?.let {
                    if(it == 1) {
                        map.remove(vehicleName)
                    } else {
                        map.put(vehicleName, it - 1)
                    }
                }
            } else {
                map[vehicleName] = 1
            }
        }
    }

    /**
     * reset the request value
     **/
    fun resetApp() {
        _falconeRequest.value = FindFalconRequest()
    }
}