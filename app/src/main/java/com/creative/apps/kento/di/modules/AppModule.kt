package com.creative.apps.kento.di.modules

import com.creative.apps.kento.di.annotations.OkHttpClientLoggingInterceptor
import com.creative.apps.kento.framework.network.AnimesServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @ExperimentalSerializationApi
    fun providesAnimesService(@OkHttpClientLoggingInterceptor client: OkHttpClient) : AnimesServices {
        val contentType = MediaType.get("application/json")
        val json = Json { ignoreUnknownKeys = true }
        val serializer = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .client(client)
            .addConverterFactory(serializer)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(AnimesServices::class.java)
    }
}