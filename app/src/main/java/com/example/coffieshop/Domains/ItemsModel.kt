package com.example.coffieshop.Domains

import java.io.Serializable

data class ItemsModel(
    val title: String = "",
    val description: String = "",
    val picUrl: ArrayList<String> = ArrayList(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val extra: String = "",
    var numberInCart: Int = 0
): Serializable
