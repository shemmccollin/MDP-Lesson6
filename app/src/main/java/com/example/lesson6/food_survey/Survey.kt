package com.example.lesson6.food_survey

import java.io.Serializable

data class Survey (val questionTitle: String, val options: ArrayList<String>): Serializable