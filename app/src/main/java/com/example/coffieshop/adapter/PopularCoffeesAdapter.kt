package com.example.coffieshop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.Helper.ManageCart
import com.example.coffieshop.activities.DetailActivity
import com.example.coffieshop.databinding.ViewholderPopcoffeesBinding

class PopularCoffeesAdapter(private val items: MutableList<ItemsModel>, private val context: Context)
    :RecyclerView.Adapter<PopularCoffeesAdapter.PopViewHolder>(){

    class PopViewHolder(val binding: ViewholderPopcoffeesBinding): RecyclerView.ViewHolder(binding.root)

    private val manageCart = ManageCart()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopViewHolder {
        val binding = ViewholderPopcoffeesBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopViewHolder, position: Int) {
        val item = items[position]
        holder.binding.coffeeTitle.text = item.title
        holder.binding.coffeePrice.text = "$"+item.price.toString()
        Glide.with(context).load(item.picUrl[0]).into(holder.binding.coffeePic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("item", item)
            context.startActivity(intent)
        }

        holder.binding.addCart.setOnClickListener {
            manageCart.insertItem(context, item)
        }
    }


}