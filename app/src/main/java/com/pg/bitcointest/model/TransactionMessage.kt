package com.pg.bitcointest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransactionMessage {

    @SerializedName("op")
    @Expose
    var op: String? = null

    @SerializedName("x")
    @Expose
    var x: X? = null

}