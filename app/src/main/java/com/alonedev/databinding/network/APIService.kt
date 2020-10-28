package com.alonedev.databinding.network

import com.alonedev.databinding.model.CatList
import com.alonedev.databinding.model.DataList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIService {

    @FormUrlEncoded
    @POST("fixmate_webservice")
    fun getRecTech(@Field("service_name")service_name:String,@Field("limit")limit:String) : Call<DataList>

    @FormUrlEncoded
    @POST("fixmate_webservice")
    fun getTechType(@Field("service_name")service_name:String,@Field("filter")filter:String) : Call<CatList>


    @FormUrlEncoded
    @POST("fixmate_webservice")
    fun login(@Field("service_name")service_name:String,@Field("filter")filter:String) : Call<DataList>
}