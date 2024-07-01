package me.ashutoshkk.stonks.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ashutoshkk.stonks.common.Constants.BASE_URL
import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.deserializer.GraphPointsDeserializer
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesAlphaVantageApiService() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(GraphDataDto::class.java, GraphPointsDeserializer())
                    .create()
            )
        )
        .build()
        .create(AlphaVantageApiService::class.java)

}