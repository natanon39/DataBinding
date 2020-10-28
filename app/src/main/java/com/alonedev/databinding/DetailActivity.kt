package com.alonedev.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alonedev.databinding.model.DataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
       getData()
    }



    fun getData()
    {
        if (intent.hasExtra("tdata")) {
            val photo= intent.getParcelableExtra<DataModel>("tdata")
            if (photo != null) {

                ttype.setText(photo.type_name)
                tname.setText(photo.fname)
                tcompany.setText(photo.company)
               Glide.with(this).load(photo.profile_image).fitCenter().into(photo_image)
            }
        }
    }
}