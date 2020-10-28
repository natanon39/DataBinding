package com.alonedev.databinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alonedev.databinding.adapter.TechAdapter
import com.alonedev.databinding.model.DataList
import com.alonedev.databinding.model.DataModel
import com.alonedev.databinding.network.APIService
import com.alonedev.databinding.network.RetrofitInstance
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel:ViewModel() {
    var mDataList : MutableLiveData<DataList>
    var mTechAdapter : TechAdapter
    init {
        mDataList = MutableLiveData()
        mTechAdapter = TechAdapter()
    }

    fun getAdapter():TechAdapter = mTechAdapter

    fun setAdapterData(data:ArrayList<DataModel>)
    {
        mTechAdapter.setDataList(data)
        mTechAdapter.notifyDataSetChanged()
    }

    fun getDataObserver():MutableLiveData<DataList> = mDataList

    fun makeAPICall()
    {
        val instance = RetrofitInstance.getInstance().create(APIService::class.java)
        val jsonObject =JSONObject()
        jsonObject.put("start",0)
        jsonObject.put("end",20)
        val call = instance.getRecTech("techmember.getRecTech",jsonObject.toString())
        println("Make API")
        call.enqueue(object :Callback<DataList>{
            override fun onResponse(call: Call<DataList>, response: Response<DataList>) {
                println(response.body())
                if(response.isSuccessful)mDataList.postValue(response.body())
                else mDataList.postValue(null)
            }
            override fun onFailure(call: Call<DataList>, t: Throwable) {
                println(t)
                mDataList.postValue(null)
                makeAPICall()
            }
        })
    }
}