package com.example.lesson6.electronic_gadgets

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.R
import java.io.Serializable
import android.app.Activity
import androidx.core.content.ContextCompat.startActivity

class MyGadgetAdapter(val context: Context, private val product: ArrayList<Product>): RecyclerView.Adapter<MyGadgetAdapter.MyViewHolder>(){

    var cart: IntArray = IntArray(product.size) {0}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gadget, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return product.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productName.text = product[position].productName
        holder.description.text = product[position].productDescription
        val price = "$ ${product[position].cost}"
        holder.price.text = price
        holder.image.setImageResource(product[position].imageId)
        holder.logo.setImageResource(product[position].logoId)

        holder.layout.setOnClickListener(){
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("details", product[position] as Serializable)
            startActivity(context, intent, null)
        }

        holder.addBtn.setOnClickListener(){
           cart[position] = cart[position] + 1

            Toast.makeText(context, "Item added.", Toast.LENGTH_LONG).show()
        }
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var productName: TextView
        var description: TextView
        var price: TextView
        var image: ImageView
        var logo: ImageView
        var addBtn: Button
        var layout: ConstraintLayout
        init{
            productName = itemView.findViewById((R.id.name))!!
            description = itemView.findViewById(R.id.description)!!
            price = itemView.findViewById(R.id.price)!!
            image = itemView.findViewById(R.id.image)!!
            logo = itemView.findViewById(R.id.logo)!!
            addBtn = itemView.findViewById(R.id.addBtn)!!
            layout= itemView.findViewById(R.id.gadgetItem)
        }
    }
}