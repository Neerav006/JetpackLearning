package com.example.myapplication.di

import com.example.myapplication.common.AppConstants
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.remote.MovieApi
import com.example.myapplication.data.repository.CoinRepoImpl
import com.example.myapplication.domain.repository.CoinAppRepository
import com.example.myapplication.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    @Named("main")
    fun provideApiService(): ApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(AppConstants.BASE_URL).build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("movie")
    fun provideApiService2(): MovieApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(AppConstants.BASE_URL_2).build().create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinRepo(@Named("main") apiService: ApiService): CoinAppRepository {
        return CoinAppRepository(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideMovieDbRepo(@Named("movie") movieApi: MovieApi): MovieRepository {
        return MovieRepository(movieApi = movieApi)
    }


}