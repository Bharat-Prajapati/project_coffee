package com.example.coffieshop.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffieshop.R
import com.example.coffieshop.ViewModel.MainViewModel
import com.example.coffieshop.adapter.ItemListCategoryAdapter
import com.example.coffieshop.databinding.ActivityItemListBinding

class ItemListActivity : AppCompatActivity() {
    private val binding : ActivityItemListBinding by lazy {
        ActivityItemListBinding.inflate(layoutInflater)
    }

    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.loadItemList(id).observeForever{
                rvItemList.layoutManager = LinearLayoutManager(this@ItemListActivity)
                rvItemList.adapter = ItemListCategoryAdapter(it, this@ItemListActivity)
                progressBar.visibility = View.GONE
            }

            backBtn.setOnClickListener{
                finish()
            }
        }
    }

    private fun getBundle(){
        id = intent.getStringExtra("id").toString()
        title = intent.getStringExtra("title").toString()

        binding.categoryTxt.text = title
    }
}