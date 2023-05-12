package com.example.findingfalcon.data.repositoryImpl

import com.example.findingfalcon.data.remote.ApiService
import com.example.findingfalcon.data.response.ApiResponse
import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.domain.entities.Token
import com.example.findingfalcon.domain.repository.FindFalconeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FindFalconeRepositoryImpl @Inject constructor(private val apiService: ApiService): FindFalconeRepository {

    override suspend fun getToken(): Flow<ApiResponse<Token>> {
        return flow {
            val result = apiService.generateToken()
            if(result.isSuccessful) {
                result.body()?.let { emit(ApiResponse.SUCCESS(it)) }
            } else {
                emit(ApiResponse.FAILURE(result.message()))
            }
        }
    }

    override suspend fun findFalcon(request: FindFalconRequest): Flow<FindFalconResponse> {
        return flow {
            //val requestStr = Gson().toJson(request)
            val result = apiService.findFalcone(request)
            if(result.isSuccessful) {
                result.body()?.let { emit(it) }
            } else {
                result.body()?.let { emit(it) }
            }
        }
    }
}