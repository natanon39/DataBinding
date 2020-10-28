package com.alonedev.databinding.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alonedev.databinding.DetailActivity
import com.alonedev.databinding.databinding.TechListBinding
import com.alonedev.databinding.model.DataModel
import com.bumptech.glide.Glide


class TechAdapter : RecyclerView.Adapter<TechAdapter.myViewHolder>() {
    var items = ArrayList<DataModel>()


    fun setDataList(data: ArrayList<DataModel>) {
        items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechAdapter.myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val data = TechListBinding.inflate(layoutInflater)
        return myViewHolder(data)
    }

    override fun onBindViewHolder(holder: TechAdapter.myViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.getContext(), DetailActivity::class.java)
            intent.putExtra("tdata", items[position])
            it.context.startActivity(intent)
            println(items[position])
        }
    }

    override fun getItemCount(): Int = items.size


    class myViewHolder(val binding: TechListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    companion object {

        @BindingAdapter("loadTImage")
        @JvmStatic
        fun loadTImage(view: ImageView, url: String?) {
                Glide.with(view)
                    .load("https://icons.iconarchive.com/icons/webalys/kameleon.pics/512/Road-Worker-1-icon.png")
                    .into(view)
        }

    }

}