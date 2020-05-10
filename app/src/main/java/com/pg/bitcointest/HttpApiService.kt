package com.pg.bitcointest

import com.pg.bitcointest.coinModel.CoinRate
import retrofit2.Call
import retrofit2.http.GET


interface HttpApiService {

    @GET("ticker")
    fun getCoinRates(): Call<CoinRate>


}