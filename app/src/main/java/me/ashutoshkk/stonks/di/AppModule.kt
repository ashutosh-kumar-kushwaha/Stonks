package me.ashutoshkk.stonks.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.ashutoshkk.stonks.common.Constants.BASE_URL
import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.deserializer.GraphDataDeserializer
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.data.room.StonksDatabase
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
                    .registerTypeAdapter(GraphDataDto::class.java, GraphDataDeserializer())
                    .create()
            )
        )
        .build()
        .create(AlphaVantageApiService::class.java)

    @Provides
    @Singleton
    fun providesSearchHistoryDao(database: StonksDatabase) = database.searchHistoryDao

    @Provides
    @Singleton
    fun providesStonksDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        StonksDatabase::class.java,
        "stonks-database"
    ).build()
}