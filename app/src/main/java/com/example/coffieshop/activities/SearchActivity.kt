package com.example.coffieshop.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.R
import com.example.coffieshop.adapter.SearchAdapter
import com.example.coffieshop.databinding.ActivitySearchBinding
import com.google.firebase.database.FirebaseDatabase

class SearchActivity : AppCompatActivity() {
    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: SearchAdapter
    private val allItems = ArrayList<ItemsModel>()
    private val filteredItems = ArrayList<ItemsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setUpRvSearch()
        loadItemsFormFirebase()
        setUpSearchView()

    }

    private fun setUpRvSearch(){
        adapter = SearchAdapter(filteredItems, this@SearchActivity)
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.adapter = adapter
    }

    private fun loadItemsFormFirebase() {
        val dRef = FirebaseDatabase.getInstance().getReference("Items")
        dRef.get().addOnSuccessListener { snapshot->
            if (snapshot.exists()){
                allItems.clear()
                for (children in snapshot.children){
                    val item = children.getValue(ItemsModel::class.java)
                    if (item != null){
                        allItems.add(item)
                    }
                }

                filteredItems.clear()
                filteredItems.addAll(allItems)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setUpSearchView() {
        binding.searchHere.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText ?: "")
                return true
            }

        })
    }

    private fun filterList(query: String) {
        val result = allItems.filter {
                it.title.contains(query, ignoreCase = true)
        }

        filteredItems.clear()
        filteredItems.addAll(result)
        adapter.notifyDataSetChanged()
    }

}