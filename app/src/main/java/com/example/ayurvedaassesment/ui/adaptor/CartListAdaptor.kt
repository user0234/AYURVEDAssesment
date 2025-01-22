package com.example.ayurvedaassesment.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ayurvedaassesment.data.models.SingleCartItem
import com.example.ayurvedaassesment.databinding.SingleCartLayoutBinding


class CartListAdaptor : RecyclerView.Adapter<CartListAdaptor.CartListViewHolder>() {

    inner class CartListViewHolder(val binding: SingleCartLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    // for faster change in view
    private val differCallBack = object : DiffUtil.ItemCallback<SingleCartItem>() {
        override fun areItemsTheSame(oldItem: SingleCartItem, newItem: SingleCartItem): Boolean {
            return oldItem.id == newItem.id && oldItem.cartCount == newItem.cartCount
        }

        override fun areContentsTheSame(oldItem: SingleCartItem, newItem: SingleCartItem): Boolean {
            return oldItem == newItem
        }

    }

    val cartDiffer = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CartListAdaptor.CartListViewHolder {
        return CartListViewHolder(
            SingleCartLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartListAdaptor.CartListViewHolder, position: Int) {

        val binding = holder.binding
        val itemCurrent = cartDiffer.currentList[position]

        binding.cartItemName.text = itemCurrent.name
        binding.itemCartCount.text = itemCurrent.cartCount.toString()
        binding.cartPrice.text = itemCurrent.price.toString()

        if (itemCurrent.cartCount > 0) {

            binding.addDeleteBtLayout.visibility = View.VISIBLE
            binding.cartItemLayout.visibility = View.GONE

        } else {
            binding.addDeleteBtLayout.visibility = View.GONE
            binding.cartItemLayout.visibility = View.VISIBLE

        }

        binding.addCartButton.setOnClickListener {

            //    cartDiffer.currentList[position].cartCount++
            itemCurrent.cartCount++
            onItemAddedListener?.let {
                it(itemCurrent, position)
            }
        }

        binding.addCartItem.setOnClickListener {

            //     cartDiffer.currentList[position].cartCount++
            itemCurrent.cartCount++
            onItemAddedListener?.let {
                it(itemCurrent, position)
            }
        }

        binding.removeCartItem.setOnClickListener {

            //    cartDiffer.currentList[position].cartCount--
            itemCurrent.cartCount--
            onItemRemovedListener?.let {
                it(itemCurrent, position)
            }

        }


    }

    override fun getItemCount(): Int {
        return cartDiffer.currentList.size
    }

    private var onItemAddedListener: ((SingleCartItem, Int) -> Unit)? = null

    fun setOnItemAddedListener(listener: (SingleCartItem, Int) -> Unit) {
        onItemAddedListener = listener
    }

    private var onItemRemovedListener: ((SingleCartItem, Int) -> Unit)? = null

    fun setOnItemRemovedListener(listener: (SingleCartItem, Int) -> Unit) {
        onItemRemovedListener = listener
    }


}