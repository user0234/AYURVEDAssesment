package com.example.ayurvedaassesment.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class SingleCartItem(
    @PrimaryKey()
    val id: String,
    val name: String,
    var cartCount: Int = 0,
    val price: Int,
)
