package com.pg.bitcointest

import android.util.Log
import com.google.gson.Gson
import com.pg.bitcointest.model.TransactionMessage
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString


public class EchoWebSocketListener : WebSocketListener() {

    val gson = Gson()
    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        webSocket.send("{\"op\":\"ping\"}\n")
        webSocket.send("{\"op\":\"unconfirmed_sub\"}\n")
//        webSocket.send("What's up ?")
//        webSocket.send("deadbeef".decodeHex())
//        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
    }



    override fun onMessage(webSocket: WebSocket, text: String) {
//        message comes up here
        try {
            val transactionMessage = gson.fromJson(text, TransactionMessage::class.java)
        } catch ( e : Exception) {
            e.printStackTrace()
        }
        Log.e("message ", text)

    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.e("message ", bytes.toString())
    }

    override fun onClosing(
        webSocket: WebSocket,
        code: Int,
        reason: String
    ) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        Log.e("closing  ", reason)
    }



    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.e("on failure  ", t.message + " " + response?.code)
//        connection failed
    }
    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }
}
