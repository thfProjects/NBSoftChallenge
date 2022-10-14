package com.thf.nbsoftchallenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thf.nbsoftchallenge.R
import com.thf.nbsoftchallenge.model.Product

class ProductRecylerAdapter(val fragment: Fragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataset: List<Product> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onProductClick: (Product) -> Unit = {}

    fun onProductClick(func: (Product) -> Unit) {
        onProductClick = func
    }

    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val brand: TextView = itemView.findViewById(R.id.brand)
        val price: TextView = itemView.findViewById(R.id.price)

        init {
            itemView.setOnClickListener {
                onProductClick(dataset[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ProductViewHolder
        Glide.with(fragment).load(dataset[position].imageLink).into(holder.image)
        holder.name.text = dataset[position].name
        holder.brand.text = dataset[position].brand
        holder.price.text = dataset[position].price
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}