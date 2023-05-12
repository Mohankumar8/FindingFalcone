package com.example.findingfalcon.di

import com.example.findingfalcon.data.remote.ApiService
import com.example.findingfalcon.data.repositoryImpl.DestinationRepositoryImpl
import com.example.findingfalcon.data.repositoryImpl.FindFalconeRepositoryImpl
import com.example.findingfalcon.domain.repository.DestinationRepository
import com.example.findingfalcon.domain.repository.FindFalconeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDestinationRepo(apiService: ApiService): DestinationRepository {
        return DestinationRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideFindFalconeRepo(apiService: ApiService) : FindFalconeRepository {
        return FindFalconeRepositoryImpl(apiService)
    }
}