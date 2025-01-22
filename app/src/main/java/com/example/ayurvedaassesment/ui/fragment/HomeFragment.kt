package com.example.ayurvedaassesment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ayurvedaassesment.R
import com.example.ayurvedaassesment.databinding.FragmentHomeBinding
import com.example.ayurvedaassesment.ui.MainActivity
import com.example.ayurvedaassesment.ui.MainViewModel
import com.example.ayurvedaassesment.ui.adaptor.CartListAdaptor


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var cartAdaptor: CartListAdaptor
    private val viewModel: MainViewModel by lazy {
        (activity as MainActivity).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.cartButton.setOnClickListener {

            viewModel.openCartFragment()

        }

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {

        cartAdaptor = CartListAdaptor()

        binding.recyclerView.apply {
            adapter = cartAdaptor
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(false)
        }

        cartAdaptor.setOnItemAddedListener { item, i ->

            viewModel.updateItem(item)
            cartAdaptor.notifyItemChanged(i)

        }

        cartAdaptor.setOnItemRemovedListener { item, i ->

            viewModel.updateItem(item)
            cartAdaptor.notifyItemChanged(i)
        }

        viewModel.getAllItem().observe(viewLifecycleOwner) {

            cartAdaptor.cartDiffer.submitList(it)

            binding.cartButton.text = it.sumOf { it.cartCount }.toString()

        }


    }

}