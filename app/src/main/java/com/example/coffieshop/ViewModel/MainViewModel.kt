package com.example.coffieshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffieshop.Domains.BannerModel
import com.example.coffieshop.Domains.CategoryModel
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopCoffees(): LiveData<MutableList<ItemsModel>>{
        return repository.loadPopCoffees()
    }

    fun loadItemList(categoryId: String): LiveData<MutableList<ItemsModel>>{
        return repository.loadItemList(categoryId)
    }
}