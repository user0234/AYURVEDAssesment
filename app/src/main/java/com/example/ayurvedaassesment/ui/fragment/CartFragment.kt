package com.example.ayurvedaassesment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ayurvedaassesment.R
import com.example.ayurvedaassesment.databinding.FragmentCartBinding
import com.example.ayurvedaassesment.ui.MainActivity
import com.example.ayurvedaassesment.ui.MainViewModel
import com.example.ayurvedaassesment.ui.adaptor.CartListAdaptor


class CartFragment : Fragment(R.layout.fragment_cart) {

    lateinit var binding: FragmentCartBinding
    private lateinit var cartAdaptor: CartListAdaptor
    private val viewModel: MainViewModel by lazy {
        (activity as MainActivity).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)
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

        viewModel.getItemInCartItem().observe(viewLifecycleOwner) {

            cartAdaptor.cartDiffer.submitList(it)

            binding.priceCount.text = it.sumOf { (it.price * it.cartCount) }.toString()

        }

    }

}