package com.alonedev.databinding.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel (
    var t_id :String?=null,
    var type_id :String?=null,
    var p_id :String?=null,
    var fname :String?=null,
    var lname :String?=null,
    var company :String?=null,
    var email :String?=null,
    var facebook :String?=null,
    var address :String?=null,
    var location :String?=null,
    var phone :String?=null,
    var card_image :String?=null,
    var type_name : String?=null,
    var profile_image:String ="https://icons.iconarchive.com/icons/webalys/kameleon.pics/512/Road-Worker-1-icon.png",
    var about :String?=null,
    var status :String?=null,
    var date_added :String?=null,
    var date_updated :String?=null,
    var token :String?=null,
) :Parcelable

data class CatModel (
    val type_id:String,
    val name:String,
    val image:String
)
data class CatList(
    val data: ArrayList<CatModel>,
    val total: Int? = null,
    val error: ArrayList<Any>?= null,
    val status: String? = null
)

data class DataList(
    val data: ArrayList<DataModel>,
    val total: Int? = null,
    val error: ArrayList<Any>?= null,
    val status: String? = null
)