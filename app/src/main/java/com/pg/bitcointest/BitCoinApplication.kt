package com.pg.bitcointest

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BitCoinApplication : Application() {



    val okHttpClient by lazy {
        OkHttpClient()
    }


    private val retrofitClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://blockchain.info/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BitCoinApplication
            private set
    }



    fun getHttpService(): HttpApiService {
        return instance.retrofitClient.create(HttpApiService::class.java)
    }



}