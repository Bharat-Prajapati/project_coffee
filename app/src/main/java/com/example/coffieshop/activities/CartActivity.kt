package com.example.coffieshop.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.Helper.ManageCart
import com.example.coffieshop.R
import com.example.coffieshop.adapter.CartAdapter
import com.example.coffieshop.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private val binding: ActivityCartBinding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    private val manageCart = ManageCart()
    private lateinit var adapter: CartAdapter
    private lateinit var itemList : ArrayList<ItemsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        observeCart()

        initCartList()

        binding.backBtn.setOnClickListener { finish() }
    }

    private fun observeCart() {
        binding.progressBar.visibility = View.VISIBLE
        manageCart.getAllItemList().observe(this) {items->
            binding.progressBar.visibility = View.GONE
            itemList = ArrayList(items)
            adapter = CartAdapter(this, itemList)
            binding.rvItemListCart.adapter = adapter
        }
    }

    private fun initCartList() {
        binding.apply {
            binding.rvItemListCart.layoutManager = LinearLayoutManager(this@CartActivity)
        }
    }
}