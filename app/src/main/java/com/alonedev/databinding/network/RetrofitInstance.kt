package com.alonedev.databinding.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object{
        private val base_URL="http://testfixmate.atwebpages.com/api/"
        fun getInstance():Retrofit=Retrofit.Builder().baseUrl(base_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}