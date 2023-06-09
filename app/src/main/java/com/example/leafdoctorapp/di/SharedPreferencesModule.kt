package com.example.leafdoctorapp.di

import android.content.Context
import com.example.leafdoctorapp.core.AppPreferenceManager
import com.example.leafdoctorapp.core.AppPreferenceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        AppPreferenceManagerImpl(context) as AppPreferenceManager
}