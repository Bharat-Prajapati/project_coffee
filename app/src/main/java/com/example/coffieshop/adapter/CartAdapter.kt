package com.example.coffieshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.Helper.ChangeNumberItemsListener
import com.example.coffieshop.databinding.ViewholderCartBinding

class CartAdapter(
    private val context: Context,
    private val listItemSelected: ArrayList<ItemsModel>,
    val changeNumberItemsListener: ChangeNumberItemsListener? = null
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(var binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       val binding = ViewholderCartBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemSelected.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var items = listItemSelected[position]

        holder.binding.titleEachCart.text = items.title
        holder.binding.priceEachCart.text = "$${items.price}"

        Glide
            .with(context)
            .load(items.picUrl[0])
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.picCart)

    }
}