package com.example.lesson6

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.example.lesson6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onStartBtnClick(view: View) {
        when(true){
            binding.foodRadio.isChecked -> foodSelected()
            binding.dietRadio.isChecked -> dietSelected()
            else -> Toast.makeText(this, "No option selected.", Toast.LENGTH_LONG).show()
        }
    }

    private fun foodSelected(){
        val intent = Intent(this, SurveyActivity::class.java)
        intent.putExtra("choice", "food")
        startActivity(intent)
        binding.radioGroup.clearCheck()
    }

    private fun dietSelected(){
        val intent = Intent(this, SurveyActivity::class.java)
        intent.putExtra("choice", "diet")
        startActivity(intent)
        binding.radioGroup.clearCheck()
    }
}