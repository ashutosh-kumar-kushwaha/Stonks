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
import me.ashutoshkk.stonks.common.Constants.CACHE_SIZE
import me.ashutoshkk.stonks.common.isNetworkAvailable
import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.deserializer.GraphDataDeserializer
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.data.room.StonksDatabase
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesAlphaVantageApiService(okHttpClient: OkHttpClient): AlphaVantageApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(GraphDataDto::class.java, GraphDataDeserializer())
                        .create()
                )
            )
            .client(okHttpClient)
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

    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .addInterceptor { chain ->
            var request = chain.request()

            request = if (isNetworkAvailable(context))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()

            chain.proceed(request)
        }
        .build()
}