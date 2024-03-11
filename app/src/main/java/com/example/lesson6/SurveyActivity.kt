package com.example.lesson6

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson6.databinding.ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {

    lateinit var binding: ActivitySurveyBinding
    lateinit var survey: ArrayList<Survey>
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        survey = ArrayList()
        val choice = intent.getStringExtra("choice")
        if(choice != null)
        {
            initSurveys(choice)
        }

        if(savedInstanceState != null)
        {
            binding.output.text = savedInstanceState.getString("output")
            binding.rv.layoutManager?.onRestoreInstanceState(savedInstanceState.getParcelable("manager"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("output", binding.output.text.toString())
        outState.putParcelable("manager", binding.rv.layoutManager?.onSaveInstanceState())
    }

    private fun initSurveys(choice: String){
        when(choice){
            "food" -> {
                binding.surveyTitle.text = resources.getString(R.string.foodSurvey)
                survey.add(Survey("What is your favorite cuisine?", arrayListOf("Chinese","French", "Italian", "Indian", "Japanese", "Thai", "Turkish")))
                survey.add(Survey("How often do you eat out?", arrayListOf("Never","Rarely", "Sometimes", "Frequently")))
                survey.add(Survey("Do you prefer spicy food?", arrayListOf("Yes","No")))
                survey.add(Survey("Do you prefer vegetarian food?", arrayListOf("Yes","No")))
                survey.add(Survey("Do you like seafood?", arrayListOf("Yes","No")))

                adapter = MyAdapter(this, survey)
                binding.rv.layoutManager = LinearLayoutManager(this)
                binding.rv.adapter = adapter
            }
            "diet" -> {
                binding.surveyTitle.text = resources.getString(R.string.dietSurvey)
                survey.add(Survey("Are you vegetarian?", arrayListOf("Yes","No")))
                survey.add(Survey("Do you prefer organic food?", arrayListOf("Yes","No")))
                survey.add(Survey("Do you consume dairy products?", arrayListOf("Yes","No")))
                survey.add(Survey("Do you eat fast food?", arrayListOf("Yes","No")))
                survey.add(Survey("Do you have any food allergies?", arrayListOf("Yes","No")))

                adapter = MyAdapter(this, survey)
                binding.rv.layoutManager = LinearLayoutManager(this)
                binding.rv.adapter = adapter

            }
            else -> {}
        }

    }

    fun onSubmit(view: View) {

       val allChecked = adapter.selectedPositions.any { it == null }

        if(allChecked)
        {
            Toast.makeText(this, "you must select an answer for each question.", Toast.LENGTH_LONG).show()
        }
        else{
            var content = ""
            for(i in survey.indices)
            {
                content += "${survey[i].questionTitle}: ${survey[i].options[adapter.selectedPositions[i]!!]}\n"
            }

            binding.output.text = content
        }
    }
}