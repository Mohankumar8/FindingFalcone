package com.example.findingfalcon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.usecase.FindFalconeUseCase
import com.example.findingfalcon.domain.usecase.GetTokenUseCase
import com.example.findingfalcon.ui.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val tokenUseCase: GetTokenUseCase,
    private val findFalconeUseCase: FindFalconeUseCase,
    private val sharedPrefs: SharedPrefs
): ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _token: MutableStateFlow<String> = MutableStateFlow("")
    val token: StateFlow<String> = _token.asStateFlow()

    private val _falconeResult: MutableStateFlow<FindFalconResponse> = MutableStateFlow(FindFalconResponse.FAILURE(""))
    val falconeResult: StateFlow<FindFalconResponse> = _falconeResult.asStateFlow()

    fun generateToken(sharedViewModel: SharedViewModel): String {
        if(sharedPrefs.getToken() == null || sharedPrefs.getToken().toString().isEmpty()) {
            viewModelScope.async {
                tokenUseCase().collect {
                    when(it) {
                        is ApiResponse.SUCCESS -> {
                            _token.value = it.data.token
                            sharedViewModel.setToken(it.data.token)
                            sharedPrefs.saveToken(it.data.token)
                        }
                        is ApiResponse.FAILURE -> _token.value = it.error
                    }
                }
            }.onAwait
        } else {
            sharedViewModel.setToken(sharedPrefs.getToken().toString())
        }
        return sharedViewModel.falconeRequest.value.token
    }

    fun findFalcone(sharedViewModel: SharedViewModel) {
        _isLoading.value = true
        generateToken(sharedViewModel)
        viewModelScope.launch {
            try {
                findFalconeUseCase(sharedViewModel.falconeRequest.value).collect { result ->
                    _isLoading.value = false
                    when (result) {
                        is FindFalconResponse.SUCCESS -> _falconeResult.value = result
                        is FindFalconResponse.FAILURE -> _falconeResult.value = result
                        is FindFalconResponse.ERROR -> _falconeResult.value = result
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
                e.printStackTrace()
            }
        }
    }
}