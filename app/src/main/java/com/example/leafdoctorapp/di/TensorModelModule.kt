package com.example.leafdoctorapp.di

import android.content.Context
import com.example.leafdoctorapp.ml.Bellpepper
import com.example.leafdoctorapp.ml.LiteTomatoesModel
import com.example.leafdoctorapp.ml.Potato
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TensorModelModule {

    @Singleton
    @Provides
    fun providePotatoModel(@ApplicationContext context: Context) : Potato{
        return Potato.newInstance(context)
    }

    @Singleton
    @Provides
    fun provideTomatoModel(@ApplicationContext context: Context) : LiteTomatoesModel{
        return LiteTomatoesModel.newInstance(context)
    }

    @Singleton
    @Provides
    fun provideBellPepperModel(@ApplicationContext context: Context) : Bellpepper{
        return Bellpepper.newInstance(context)
    }


}