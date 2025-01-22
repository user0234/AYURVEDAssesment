package com.example.ayurvedaassesment.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ayurvedaassesment.R
import com.example.ayurvedaassesment.data.database.CartDatabase
import com.example.ayurvedaassesment.data.repository.CartRepository
import com.example.ayurvedaassesment.databinding.ActivityMainBinding
import com.example.ayurvedaassesment.ui.fragment.CartFragment
import com.example.ayurvedaassesment.ui.fragment.HomeFragment
import com.example.ayurvedaassesment.utils.SampleData.listOfSampleData
import com.example.ayurvedaassesment.utils.observeEvent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by lazy {
        val appDataBase = CartDatabase(applicationContext)!!

        val cartRepository = CartRepository(appDataBase)

        val viewModelProviderFactory =
            MainViewModelProviderFactory(cartRepository)

        ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listOfSampleData().forEach {

            viewModel.createItem(it)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        openHomeScreen()

        viewModel.handleOpenCartFragment.observeEvent(this) {
            openCartFragment()
        }

    }

    private fun openHomeScreen() {

        val fragment = HomeFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_view, fragment
            )

        fragmentTransaction.commit()

        fragmentTransaction.addToBackStack(fragment.toString())

    }

    private fun openCartFragment() {

        val fragment = CartFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_view, fragment
            )

        fragmentTransaction.commit()

        fragmentTransaction.addToBackStack(fragment.toString())

    }
}