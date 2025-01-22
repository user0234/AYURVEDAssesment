package com.example.ayurvedaassesment.utils

import com.example.ayurvedaassesment.data.models.SingleCartItem
import java.util.UUID

object SampleData {

    private val item1 = SingleCartItem(
        id = 11.toString(),
        name = "Item 1",
        cartCount = 0,
        price = 100

    )

    private val item2 = SingleCartItem(
        id = 22.toString(),
        name = "Item 2",
        cartCount = 0,
        price = 200

    )

    private val item3 = SingleCartItem(
        id = 33.toString(),
        name = "Item 3",
        cartCount = 0,
        price = 300

    )

    private val item4 = SingleCartItem(
        id = 44.toString(),
        name = "Item 4",
        cartCount = 0,
        price = 400

    )

    private val item5 = SingleCartItem(
        id = 55.toString(),
        name = "Item 5",
        cartCount = 0,
        price = 500

    )

    fun listOfSampleData() = listOf(item1, item2, item3, item4, item5)


}