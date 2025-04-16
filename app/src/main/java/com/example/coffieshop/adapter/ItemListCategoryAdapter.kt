package com.example.coffieshop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.activities.DetailActivity
import com.example.coffieshop.databinding.ViewholderItemPicLeftBinding
import com.example.coffieshop.databinding.ViewholderItemPicRightBinding

class ItemListCategoryAdapter(
    private val items: MutableList<ItemsModel>,
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_ITEM1 = 0
        const val TYPE_ITEM2 = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            TYPE_ITEM1
        else
            TYPE_ITEM2
    }

    class ViewHolder1(var binding: ViewholderItemPicRightBinding) : RecyclerView.ViewHolder(binding.root)

    class ViewHolder2(var binding: ViewholderItemPicLeftBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_ITEM1->{
                val binding = ViewholderItemPicRightBinding.inflate(LayoutInflater.from(context), parent, false)
                ViewHolder1(binding)
            }
            TYPE_ITEM2 ->{
                val binding = ViewholderItemPicLeftBinding.inflate(LayoutInflater.from(context), parent, false)
                ViewHolder2(binding)
            }
            else->throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        fun bindCommonData(titleTxt: String, priceTxt: String, rating: Float, picUrl: String){
            when(holder){
                is ViewHolder1->{
                    holder.binding.titletxt.text = titleTxt
                    holder.binding.priceText.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide
                        .with(context)
                        .load(picUrl)
                        .into(holder.binding.coffeePhoto)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("item", item)
                        context.startActivity(intent)
                    }
                }
                is ViewHolder2->{
                    holder.binding.titletxt.text = titleTxt
                    holder.binding.priceText.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide
                        .with(context)
                        .load(picUrl)
                        .into(holder.binding.coffeePhoto)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("item", item)
                        context.startActivity(intent)
                    }
                }
            }
        }

        bindCommonData(
            item.title,
            "${item.price} USD",
            item.price.toFloat(),
            item.picUrl[0]
        )

    }
}