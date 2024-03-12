package com.example.lesson6.electronic_gadgets

import java.io.Serializable

data class Product(val productName: String, val productDescription: String, val cost: Double, val imageId: Int, val logoId: Int) : Serializable
