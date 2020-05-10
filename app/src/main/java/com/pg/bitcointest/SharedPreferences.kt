package com.pg.bitcointest

import android.app.Activity
import android.content.Context
import com.google.gson.Gson

class SharedPreferences (protected val fileName: String, context: Context, protected val gson : Gson)  {

    private val sharedPreferences: android.content.SharedPreferences = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    /**
     * Retrives object
     */
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value : String? = getData(key, "")
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type “T” is used to cast.
        return try {
            gson.fromJson(value, T::class.java)
        } catch (exception : Exception) {
            null
        }
    }


    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = gson.toJson(`object`)
        //Save that String in SharedPreferences
        saveData(key, jsonString)

    }

    fun put(key : String, value : String) {
        editor.putString(key, value)
        editor.apply()
    }
    fun put(key : String, value : Int) {
        editor.putInt(key, value)
        editor.apply()
    }
    fun put(key : String, value : Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun put(key : String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    fun put(key : String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    fun get(key : String, defaultValue: String?) : String?{
        return sharedPreferences.getString(key, defaultValue)
    }


    fun get(key : String, defaultValue : Int) :Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun get(key : String, defaultValue : Long) :Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun get(key : String, defaultValue: Boolean) : Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun get(key : String, defaultValue: Float) : Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }



    private fun saveData(key: String, value: String) {
        editor.putString(key, value)
        return editor.apply()
    }

    protected fun getData(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun deleteAllData() {
        editor.clear()
        editor.apply()
    }



    companion object {
        val instance = SharedPreferences("bitcoin_sp", BitCoinApplication.instance.applicationContext, Gson())

        const val BITCOIN_TO_DOLLAR = "bitcoin_to_dollar"

    }

}