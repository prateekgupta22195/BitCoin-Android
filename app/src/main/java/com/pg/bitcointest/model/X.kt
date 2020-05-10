package com.pg.bitcointest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pg.bitcoDoubleest.model.Out

class X {
    @SerializedName("lock_time")
    @Expose
    var lockTime: Int? = null

    @SerializedName("ver")
    @Expose
    var ver: Int? = null

    @SerializedName("size")
    @Expose
    var size: Int? = null

    @SerializedName("inputs")
    @Expose
    var inputs: List<Input>? = null

    @SerializedName("time")
    @Expose
    var time: Int? = null

    @SerializedName("tx_index")
    @Expose
    var txIndex: Int? = null

    @SerializedName("vin_sz")
    @Expose
    var vinSz: Int? = null

    @SerializedName("hash")
    @Expose
    var hash: String? = null

    @SerializedName("vout_sz")
    @Expose
    var voutSz: Int? = null

    @SerializedName("relayed_by")
    @Expose
    var relayedBy: String? = null

    @SerializedName("out")
    @Expose
    var out: List<Out>? = null

}