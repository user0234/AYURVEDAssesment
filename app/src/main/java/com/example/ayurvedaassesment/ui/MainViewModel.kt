package com.example.ayurvedaassesment.ui

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ayurvedaassesment.data.models.SingleCartItem
import com.example.ayurvedaassesment.data.repository.CartRepository
import com.example.ayurvedaassesment.utils.Event
import com.example.ayurvedaassesment.utils.send
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CartRepository,
) : ViewModel() {

    // open settings fragment

    private val _openCartFragment = MutableLiveData<Event<Boolean>>()
    val handleOpenCartFragment: LiveData<Event<Boolean>>
        get() = _openCartFragment

    fun openCartFragment() {
        _openCartFragment.send(true)
    }

    fun getCartCount() = repository.getCartCount()
    fun getAllItem() = repository.getAllItem()
    fun getItemInCartItem() = repository.getItemInCartItem()

    fun createItem(it: SingleCartItem) {

        viewModelScope.launch {
            repository.createItem(it)
        }

    }


    fun updateItem(it: SingleCartItem) {

        viewModelScope.launch {
            repository.updateItem(it)
        }

    }


}