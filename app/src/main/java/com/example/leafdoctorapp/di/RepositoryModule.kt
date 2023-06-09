package com.example.leafdoctorapp.di

import com.example.leafdoctorapp.data.remote.ApiRepository
import com.example.leafdoctorapp.data.remote.ApiRepositoryImpl
import com.example.leafdoctorapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesAuthRepository(api: ApiService) =
        ApiRepositoryImpl(api) as ApiRepository

}