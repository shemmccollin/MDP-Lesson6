package com.example.lesson6.electronic_gadgets

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lesson6.R
import com.example.lesson6.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val product = intent.getSerializableExtra("details") as Product
        initDetails(product)
    }

    private fun initDetails(product: Product){
        binding.productName.text = product.productName
        binding.productDescription.text = product.productDescription
        binding.productPrice.text = product.cost.toString()
        binding.detailImage.setImageResource(product.imageId)
    }

    fun onHome(v: View){
        finish()
    }
}