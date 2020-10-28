package com.alonedev.databinding.page

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import com.alonedev.databinding.MainActivity
import com.alonedev.databinding.R
import com.alonedev.databinding.model.*
import com.alonedev.databinding.network.APIService
import com.alonedev.databinding.network.RetrofitInstance
import com.alonedev.databinding.singleton
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var shared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        shared = getSharedPreferences("login.conf", MODE_PRIVATE)
        checkUser()
        onLoginClick()

    }

    private fun checkUser() {
        if (!singleton.checkLogin(shared, baseContext).isNullOrEmpty())
            startActivity(Intent(baseContext, MainActivity::class.java))
    }
    private fun logout()
    {
        val edit = shared.edit()
        edit.clear()
        edit.apply()
        startActivity(Intent(baseContext, LoginActivity::class.java))
    }

    private fun onLoginClick() {
        val b = findViewById<Button>(R.id.btn_login)
        b.setOnClickListener {
            val t = findViewById<EditText>(R.id.editText_email)
            val v = findViewById<EditText>(R.id.editText_password)
            val email = t.text.toString()
            val password = v.text.toString()
            when {
                email.isEmpty() -> {
                    toast("กรุณาใส่อีเมล์")
                }
                password.isEmpty() -> {
                    toast("กรุณาใส่รหัสผ่าน")
                }
                else -> {
                    val loadingDialog = findViewById<ProgressBar>(R.id.simpleProgressBar)
                    loadingDialog.visibility = View.VISIBLE
                    t.isEnabled = false
                    v.isEnabled = false
                    b.isClickable = false

                    val instance = RetrofitInstance.getInstance().create(APIService::class.java)
                    val json = JSONObject()
                    json.put("email",email)
                    json.put("password",password)
                    val call = instance.login("techmember.login",json.toString())
                    call.enqueue(object :Callback<DataList>{
                        override fun onResponse(call: Call<DataList>, response: Response<DataList>) {
                            println(response.body())
                            if(response.isSuccessful&&!response.body()?.data.isNullOrEmpty())
                            {
                                var gson = Gson()
                                val jsonObject: JsonObject =
                                    gson.toJsonTree(response.body()!!.data!!.get(0)).getAsJsonObject()
                                val jsonob = gson.fromJson(jsonObject, DataModel::class.java)
                                toast("Welcome Back")
                                singleton.setData(shared, jsonob)
                                startActivity(Intent(baseContext, MainActivity::class.java))
                                loadingDialog.visibility = View.INVISIBLE
                                t.isEnabled = true
                                v.isEnabled = true
                                b.isClickable = true
                            }
                            else {
                                toast("Wrong email or password")
                                loadingDialog.visibility = View.INVISIBLE
                                t.isEnabled = true
                                v.isEnabled = true
                                b.isClickable = true
                            }
                        }
                        override fun onFailure(call: Call<DataList>, td: Throwable) {
                            toast("Error")
                            loadingDialog.visibility = View.INVISIBLE
                            t.isEnabled = true
                            v.isEnabled = true
                            b.isClickable = true
                        }
                    })
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(baseContext, msg, LENGTH_LONG).show()
}