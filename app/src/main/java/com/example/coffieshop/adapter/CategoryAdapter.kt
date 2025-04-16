package com.example.coffieshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffieshop.Domains.CategoryModel
import com.example.coffieshop.R
import com.example.coffieshop.databinding.ViewholderCategoryBinding

class CategoryAdapter(private val items: MutableList<CategoryModel>, private val context: Context): RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {
    private var lastSelectedPosition = -1
    private var selectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CatViewHolder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CatViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.dark_brown))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CatViewHolder(val binding: ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root)
}