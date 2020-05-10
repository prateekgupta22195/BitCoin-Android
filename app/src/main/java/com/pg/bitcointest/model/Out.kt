package com.pg.bitcoDoubleest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Out {
    @SerializedName("spent")
    @Expose
    var spent: Boolean? = null

    @SerializedName("tx_index")
    @Expose
    var txIndex: Double? = null

    @SerializedName("type")
    @Expose
    var type: Double? = null

    @SerializedName("addr")
    @Expose
    var addr: String? = null

    @SerializedName("value")
    @Expose
    var value: Double? = null

    @SerializedName("n")
    @Expose
    var n: Double? = null

    @SerializedName("script")
    @Expose
    var script: String? = null

}