package com.example.ayurvedaassesment.data.repository

import com.example.ayurvedaassesment.data.database.CartDatabase
import com.example.ayurvedaassesment.data.models.SingleCartItem

class CartRepository(
    private val dataBase: CartDatabase
) {

    suspend fun createItem(cartItem: SingleCartItem) = dataBase.cartDao().insert(cartItem)

    fun getAllItem() = dataBase.cartDao().getCartItemList()

    fun getItemInCartItem() = dataBase.cartDao().getItemInCart()

    suspend fun deleteItem(cartItem: SingleCartItem) = dataBase.cartDao().delete(cartItem)

    suspend fun updateItem(cartItem: SingleCartItem) = dataBase.cartDao().update(cartItem)


    fun getCartCount() = dataBase.cartDao().getCartItemCount()


}