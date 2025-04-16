package com.example.coffieshop.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffieshop.Domains.ItemsModel
import com.example.coffieshop.R
import com.example.coffieshop.databinding.ActivityDetailBinding
import java.io.Serializable

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bundle()
        initSizeList()

    }

    private fun bundle() {

        val item = intent.getSerializableExtra("item") as? ItemsModel

        binding.titleText.text = item?.title
        binding.descriptionText.text = item?.description
        binding.ratingText.text = item?.rating.toString()
        Glide
            .with(this)
            .load(item?.picUrl?.get(0))
            .into(binding.coffeeImage)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.plus.setOnClickListener {
            binding.numberTxt.text = (item?.numberInCart?.plus(1)).toString()
            item?.numberInCart = item?.numberInCart!! + 1
        }

        binding.minus.setOnClickListener {
            if (item?.numberInCart!! >0){
                binding.numberTxt.text = (item.numberInCart.minus(1)).toString()
                item.numberInCart --
            }
        }


    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.border_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.border_bg)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.border_bg)
            }
        }
    }

}