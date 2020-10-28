package com.alonedev.databinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alonedev.databinding.adapter.CatAdapter
import com.alonedev.databinding.model.CatList
import com.alonedev.databinding.model.CatModel
import com.alonedev.databinding.network.APIService
import com.alonedev.databinding.network.RetrofitInstance
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatViewModel:ViewModel() {
    var mCatList : MutableLiveData<CatList>
    var mCatAdapter : CatAdapter
    init {
        mCatList = MutableLiveData()
        mCatAdapter = CatAdapter()
    }

    fun getAdapter():CatAdapter = mCatAdapter

    fun setAdapterData(data:ArrayList<CatModel>)
    {
        mCatAdapter.setCatList(data)
        mCatAdapter.notifyDataSetChanged()
    }

    fun getDataObserver():MutableLiveData<CatList> = mCatList

    fun makeAPICall()
    {
        println("cMake API")
        val instance = RetrofitInstance.getInstance().create(APIService::class.java)
        val call = instance.getTechType("techmember.getTechType","")
        call.enqueue(object :Callback<CatList>{
            override fun onResponse(call: Call<CatList>, response: Response<CatList>) {
                println(response.body())
                if(response.isSuccessful)mCatList.postValue(response.body())
                else mCatList.postValue(null)
            }
            override fun onFailure(call: Call<CatList>, t: Throwable) {
                println(t)
                mCatList.postValue(null)
                makeAPICall()
            }
        })
    }
}