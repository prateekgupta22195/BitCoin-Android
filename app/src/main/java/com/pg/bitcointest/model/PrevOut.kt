package com.pg.bitcointest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrevOut {
    @SerializedName("spent")
    @Expose
    var spent: Boolean? = null

    @SerializedName("tx_index")
    @Expose
    var txIndex: Int? = null

    @SerializedName("type")
    @Expose
    var type: Int? = null

    @SerializedName("addr")
    @Expose
    var addr: String? = null

    @SerializedName("value")
    @Expose
    var value: Double? = null

    @SerializedName("n")
    @Expose
    var n: Int? = null

    @SerializedName("script")
    @Expose
    var script: String? = null

}