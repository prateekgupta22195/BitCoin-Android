package com.pg.bitcointest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pg.bitcointest.coinModel.CoinRate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!AppUtil.isUserOnline()) {
            val contextView: View? = findViewById(R.id.frag_container)
            contextView?.let {
                val snack = Snackbar.make(it, "No Internet Connection", Snackbar.LENGTH_SHORT)
                snack.view.setBackgroundColor(ContextCompat.getColor(baseContext, android.R.color.holo_red_dark))
                snack.show()
            }
        } else {
            BitCoinApplication.instance.getHttpService().getCoinRates().enqueue(object : Callback<CoinRate>{
                override fun onFailure(call: Call<CoinRate>, t: Throwable) {}
                override fun onResponse(call: Call<CoinRate>, response: Response<CoinRate>) {
                    SharedPreferences.instance.put(
                        SharedPreferences.BITCOIN_TO_DOLLAR,
                        response.body()?.uSD?.last?.toFloat()!!
                    )
                    supportFragmentManager.beginTransaction()
                        .add(R.id.frag_container, LandingFragment.newInstance()).commit()
                }
            })
        }
    }
}
