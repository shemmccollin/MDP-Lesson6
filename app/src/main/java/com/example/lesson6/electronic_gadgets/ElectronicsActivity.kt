package com.example.lesson6.electronic_gadgets

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson6.R
import com.example.lesson6.databinding.ActivityElectronicsBinding
import com.example.lesson6.food_survey.MyAdapter

class ElectronicsActivity : AppCompatActivity() {

    lateinit var binding: ActivityElectronicsBinding
    lateinit var adapter: MyGadgetAdapter
    lateinit var products: ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityElectronicsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initGadgets()

        if(savedInstanceState != null)
        {
            adapter.cart = savedInstanceState.getIntArray("cart")!!
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("cart", adapter.cart)
    }

    private fun initGadgets(){
        products = ArrayList<Product>()
        products.add(Product("iPad", "iPad Pro 11-inch", 400.0, R.drawable.ipad, R.drawable.apple_logo))
        products.add(Product("MacBook M3 Pro", "12-core CPU\n18-core GPU", 2500.00, R.drawable.mac, R.drawable.apple_logo))
        products.add(Product("Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.00, R.drawable.dell, R.drawable.dell_logo))
        products.add(Product("Logitech Keyboard", "Logitech - PRO X\nTKL LIGHTSPEED Wireless", 199.00, R.drawable.keyboard, R.drawable.logitech_logo))
        products.add(Product("MacBook M3 Max", "14-core CPU\n30-core GPU", 3499.00, R.drawable.mac, R.drawable.apple_logo))

        adapter = MyGadgetAdapter(this, products)
        binding.rvGadgets.layoutManager = LinearLayoutManager(this)
        binding.rvGadgets.adapter = adapter
    }
    fun onViewCart(view: View) {
        var string: String = ""

        for(i in adapter.cart.indices){
            if(adapter.cart[i] > 0){
                string += "${adapter.cart[i]} * ${products[i].productName}\n"
            }
        }

        if(string.isNotBlank())
        {
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }

    }
}