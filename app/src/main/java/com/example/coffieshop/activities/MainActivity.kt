package com.example.coffieshop.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffieshop.R
import com.example.coffieshop.ViewModel.MainViewModel
import com.example.coffieshop.adapter.CategoryAdapter
import com.example.coffieshop.adapter.PopularCoffeesAdapter
import com.example.coffieshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initBanner()
        initCategory()
        initPopCoffees()
        initBottom()

        binding.searchCoffee.setOnClickListener {
            val searchIntent = Intent(this, SearchActivity::class.java)
            startActivity(searchIntent)
        }
    }

    private fun initBottom() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    private fun initCategory() {
        binding.catProgressBar.visibility = View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.categoryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.categoryRv.adapter = CategoryAdapter(it, this)
            binding.catProgressBar.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initPopCoffees() {
        binding.popProgressBar.visibility = View.VISIBLE
        viewModel.loadPopCoffees().observeForever {
            binding.popularCoffeeRv.layoutManager = GridLayoutManager(this, 2)
            binding.popularCoffeeRv.adapter = PopularCoffeesAdapter(it, this)
            binding.popProgressBar.visibility = View.GONE
        }
        viewModel.loadPopCoffees()
    }

    private fun initBanner() {
        binding.bannerProgressBar.visibility = View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)
            binding.bannerProgressBar.visibility = View.GONE
        }
        viewModel.loadBanner()
    }

}