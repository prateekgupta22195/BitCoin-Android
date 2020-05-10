package com.pg.bitcointest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Input {
    @SerializedName("sequence")
    @Expose
    var sequence: Double? = null

    @SerializedName("prev_out")
    @Expose
    var prevOut: PrevOut? = null

    @SerializedName("script")
    @Expose
    var script: String? = null

}