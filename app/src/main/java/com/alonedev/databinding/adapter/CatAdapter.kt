package com.alonedev.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alonedev.databinding.R
import com.alonedev.databinding.databinding.CatListBinding
import com.alonedev.databinding.model.CatModel
import com.bumptech.glide.Glide

class CatAdapter : RecyclerView.Adapter<CatAdapter.myViewHolder>() {
    var items = ArrayList<CatModel>()


    fun setCatList(data: ArrayList<CatModel>) {
        items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatAdapter.myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val data = CatListBinding.inflate(layoutInflater, parent, false)
        return myViewHolder(data)
    }

    override fun onBindViewHolder(holder: CatAdapter.myViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            println(items[position])
        }
    }

    override fun getItemCount(): Int = items.size


    class myViewHolder(val binding: CatListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CatModel) {
            binding.cdata = data
            binding.executePendingBindings()
        }
    }

    companion object {

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(view)
                    .load("https://fixmate.net/assets/images/$url")
                    .placeholder(R.drawable.loading_animation)
                    .fallback(R.drawable.loading_animation)
                    .error(R.drawable.loading_animation).into(view)
            }
        }

    }

}