package com.example.coffieshop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.activities.DetailActivity
import com.example.coffieshop.databinding.ViewholderSearchItemBinding

class SearchAdapter(
    private val items: ArrayList<ItemsModel>,
    private val context: Context) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    class SearchViewHolder(var binding: ViewholderSearchItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ViewholderSearchItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.itemTitle.text = items[position].title
        holder.binding.itemPrice.text = "$-${items[position].price}"

        Glide
            .with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.itemPic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("item", items[position])
            context.startActivity(intent)
        }
    }
}