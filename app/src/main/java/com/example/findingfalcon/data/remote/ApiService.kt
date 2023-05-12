package com.example.findingfalcon.data.remote

import com.example.findingfalcon.data.utils.ApiEndPoints
import com.example.findingfalcon.domain.entities.FindFalconRequest
import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.entities.Token
import com.example.findingfalcon.domain.entities.Vehicle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPlanets(@Url url: String): Response<List<Planet>>

    @GET(ApiEndPoints.END_POINT_VEHICLES)
    suspend fun getVehicles(): Response<List<Vehicle>>

    @Headers("Accept: application/json")
    @POST(ApiEndPoints.END_POINT_TOKEN)
    suspend fun generateToken(): Response<Token>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST(ApiEndPoints.END_POINT_FIND)
    suspend fun findFalcone(@Body request: FindFalconRequest): Response<FindFalconResponse.SUCCESS>
}