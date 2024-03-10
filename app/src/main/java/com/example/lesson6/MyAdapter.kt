package com.example.lesson6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context, private val survey: ArrayList<Survey>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    public var selectedPositions: Array<Int?> = arrayOfNulls(survey.size)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return survey.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.questionTitle.text = survey[position].questionTitle
        for(option in survey[position].options)
        {
            val radioButton = RadioButton(context)
            radioButton.text = option
            radioButton.id = survey[position].options.indexOf(option)
            holder.choiceGroup.addView(radioButton)
        }

        holder.choiceGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedPositions[position] = checkedId
        }

        holder.choiceGroup.setOnClickListener(){

            Toast.makeText(context, holder.choiceGroup.checkedRadioButtonId.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var questionTitle: TextView
        var choiceGroup: RadioGroup
        init {
            questionTitle = itemView.findViewById(R.id.questionTitle)!!
            choiceGroup  = itemView.findViewById(R.id.choiceGroup)!!
        }
    }
}