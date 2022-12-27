package com.example.themoviedatabase.di

import com.example.themoviedatabase.BuildConfig
import com.example.themoviedatabase.services.MoviesService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val DEFAULT_TIMEOUT: Long = 5_000

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Singleton
    @Provides
    fun providesOkhttpClient() : OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            }
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesBaseUrl() = HttpUrl.Builder()
        .scheme("https")
        .host(BuildConfig.API_HOST)
        .addPathSegment(BuildConfig.API_VERSION)
        .addPathSegment("") // baseUrl needs to end with /
        .build()

    @Singleton
    @Provides
    fun providesMoviesService(
        client: OkHttpClient,
        url: HttpUrl
    ) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .callFactory(client)
        .build()
        .create(MoviesService::class.java)

    @Singleton
    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesCoroutineDispatcher() = Dispatchers.IO
}