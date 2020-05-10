package com.pg.bitcointest.coinModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoinRate {
    @SerializedName("USD")
    @Expose
    var uSD: USD? = null
}