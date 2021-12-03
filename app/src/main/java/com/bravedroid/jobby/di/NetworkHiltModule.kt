package com.bravedroid.jobby.di

import com.bravedroid.jobby.infrastructure.data.datasource.network.findwork.FindWorkConstants.BASE_URL
import com.bravedroid.jobby.infrastructure.data.datasource.network.findwork.di.FindWorkRetrofit
import com.bravedroid.jobby.infrastructure.data.datasource.network.findwork.service.FindWorkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkBuilderHiltModule::class])
@InstallIn(SingletonComponent::class)
abstract class NetworkHiltModule

@Module
@InstallIn(SingletonComponent::class)
class NetworkBuilderHiltModule {

    @Singleton
    @Provides
    @FindWorkRetrofit
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory("application/json".toMediaType()))
        .build()


    @Singleton
    @Provides
    fun providesFindWorkService(@FindWorkRetrofit retrofit: Retrofit): FindWorkService =
        retrofit.create(FindWorkService::class.java)
}
