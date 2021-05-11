package com.sun.moviedb_54.data.source.remote

import com.sun.moviedb_54.ultis.Constant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(factory: GsonConverterFactory, client: OkHttpClient) {

    private var baseRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()

    fun <T> create(service: Class<T>): T {
        return baseRetrofit.create(service)
    }
}
