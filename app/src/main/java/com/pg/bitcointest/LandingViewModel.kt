package com.pg.bitcointest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.pg.bitcointest.model.TransactionItem
import com.pg.bitcointest.model.TransactionMessage
import com.pg.bitcointest.model.WSConstant
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.*
import kotlin.math.pow
import kotlin.math.round

class LandingViewModel() : ViewModel() {

    
     val data : MutableLiveData<MutableList<TransactionItem>> by lazy { MutableLiveData(mutableListOf<TransactionItem>()) }


    private val request: Request by lazy { Request.Builder().url(WSConstant.NEW_MESSAGE_URL).build() }
    val listener : EchoWebSocketListener by lazy {  EchoWebSocketListener() }
    val ws by lazy { BitCoinApplication.instance.okHttpClient.newWebSocket(request, listener) }

    public val state : MutableLiveData<STATE> by lazy{
        if(AppUtil.isUserOnline())
            MutableLiveData(STATE.CONNECTING)
        else
            MutableLiveData(STATE.NO_INTERNET)
    }



    init {
        ws.request()
    }



    inner class EchoWebSocketListener : WebSocketListener() {

        val gson = Gson()
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            state.postValue(STATE.CONNECTING)
            webSocket.send("{\"op\":\"ping\"}\n")
            webSocket.send("{\"op\":\"unconfirmed_sub\"}\n")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
//        message comes up here
            if(state.value!=STATE.CONNECTED)
                state.postValue(STATE.CONNECTED)
            try {
                val transactionMessage = gson.fromJson(text, TransactionMessage::class.java)

                var rateInSatoshi : Double = 0.0

                for(satoshi in transactionMessage.x?.out!!) {
                    rateInSatoshi += satoshi.value!!
                }


                var rateInBitCoin = AppUtil.getRateInBitCoin(rateInSatoshi)

                var oneBitCoinInDollars = SharedPreferences.instance.get(SharedPreferences.BITCOIN_TO_DOLLAR, 0f)


                val rate = rateInBitCoin * oneBitCoinInDollars
                if(rate <= 100)
                    return

                val transactionItem = TransactionItem(transactionMessage.x?.hash, String.format("%.2f $", rate) , Date(transactionMessage.x?.time?.toLong()!!))
                data.value?.let {
                    if(it.size == 5) {
                        it.removeAt(4)
                    }
                    it.add(transactionItem)
                    it.sortByDescending { it.date }
                    data.postValue(it)
                }
            } catch ( e : Exception) {
                e.printStackTrace()
            }
            Log.e("message ", text)

        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            Log.e("message ", bytes.toString())
            if(state.value!=STATE.CONNECTING)
                state.postValue(STATE.CONNECTING)
        }

        override fun onClosing(
            webSocket: WebSocket,
            code: Int,
            reason: String
        ) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            Log.e("closing  ", reason)
            state.postValue(STATE.DISCONNECTED)
        }



        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            Log.e("on failure  ", t.message + " " + response?.code)
            state.postValue(STATE.DISCONNECTED)
//        connection failed
        }
    }


    enum class STATE(val message : String, val color : Int) {
        CONNECTED("Connected", android.R.color.holo_green_dark),
        DISCONNECTED("Disconnected", android.R.color.holo_red_light),
        NO_INTERNET("No Internet", android.R.color.holo_red_dark),
        CONNECTING ("Connecting", android.R.color.holo_orange_light)
    }

    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }


    public fun clearData() {
        data.value?.clear()
    }
}
