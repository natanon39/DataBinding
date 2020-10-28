package com.alonedev.databinding

import android.content.Context
import android.content.SharedPreferences
import com.alonedev.databinding.model.DataModel
import com.google.gson.Gson

object singleton {
    var user : DataModel? = null
    init {

    }
    fun setData(sharedPref: SharedPreferences,data : DataModel)
    {
        user = data
        val editor = sharedPref.edit()
        var dad = Gson().toJson(data)
        editor.putString("userdata",dad)
        editor.apply()
    }
    fun getData():DataModel?= user!!

    fun checkLogin(sharedPref: SharedPreferences,context: Context):String?
    {
        return sharedPref.getString("userdata","")
    }
}