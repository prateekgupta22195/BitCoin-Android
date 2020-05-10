package com.pg.bitcointest.coinModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class USD {
    @SerializedName("15m")
    @Expose
    private var _15m: Double? = null

    @SerializedName("last")
    @Expose
    var last: Double? = null

    @SerializedName("buy")
    @Expose
    var buy: Double? = null

    @SerializedName("sell")
    @Expose
    var sell: Double? = null

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    fun get15m(): Double? {
        return _15m
    }

    fun set15m(_15m: Double?) {
        this._15m = _15m
    }

}