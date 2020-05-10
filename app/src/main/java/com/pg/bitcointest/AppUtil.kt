package com.pg.bitcointest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import kotlin.math.pow


class AppUtil {
    companion object {

        fun isUserOnline(): Boolean {
            val info: NetworkInfo? = getNetworkInfo(BitCoinApplication.instance.applicationContext)
            return info != null && info.isConnected
        }


        fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }


        fun getRateInBitCoin(satoshi : Double) : Double {
            return satoshi/ 10.0.pow(8.0)
        }

    }
}