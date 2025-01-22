package com.example.ayurvedaassesment.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ayurvedaassesment.data.models.SingleCartItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartItem: SingleCartItem): Long

    @Query("Select * From cart_item")
    fun getCartItemList(): LiveData<List<SingleCartItem>>

    @Query("Select * From cart_item where cartCount > 0")
    fun getItemInCart(): LiveData<List<SingleCartItem>>

    @Update
    suspend fun update(cartItem: SingleCartItem)

    @Delete
    suspend fun delete(cartItem: SingleCartItem)

    @Query("Select COUNT(*) From cart_item where cartCount > 0")
    fun getCartItemCount(): LiveData<Int>

}