package com.sun.moviedb_54.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sun.moviedb_54.data.source.remote.api.APIService
import com.sun.moviedb_54.data.source.remote.client.RetrofitClient
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    fun provideApi(retrofit: RetrofitClient) = retrofit.create(APIService::class.java)

    single { provideApi(get()) }
}

val retrofitModule = module {

    fun provideGson() = GsonBuilder().create()

    fun provideGsonConverterFactory(factory: Gson) = GsonConverterFactory.create(factory)

    fun provideHttpClient() = OkHttpClient.Builder().build()

    fun provideRetrofit(factory: GsonConverterFactory, client: OkHttpClient): RetrofitClient {
        return RetrofitClient(factory, client)
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideGsonConverterFactory(get()) }
    single { provideRetrofit(get(), get()) }
}
