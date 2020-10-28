package com.alonedev.databinding.fragment

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.alonedev.databinding.R
import com.alonedev.databinding.page.LoginActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    private lateinit var shared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shared = getActivity()!!.getSharedPreferences("login.conf",MODE_PRIVATE)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(mypic)
            .load("https://icons.iconarchive.com/icons/webalys/kameleon.pics/512/Road-Worker-1-icon.png")
            .into(mypic)

        textView_logout.setOnClickListener {
            logout()
        }
    }

    private fun logout()
    {
        val edit = shared.edit()
        edit.clear()
        edit.apply()
        startActivity(Intent(activity, LoginActivity::class.java))
    }
}