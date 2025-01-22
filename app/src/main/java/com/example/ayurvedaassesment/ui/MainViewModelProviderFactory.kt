package com.example.ayurvedaassesment.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ayurvedaassesment.data.repository.CartRepository

class MainViewModelProviderFactory(
    private val cartRepository: CartRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainViewModel(cartRepository) as T
    }
}