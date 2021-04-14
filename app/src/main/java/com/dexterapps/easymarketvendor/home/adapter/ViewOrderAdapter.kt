package com.dexterapps.easymarketvendor.home.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.model.Product
import com.squareup.picasso.Picasso

class ViewOrderAdapter(val list: ArrayList<Product>) :
    RecyclerView.Adapter<ViewOrderAdapter.ViewOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewOrderViewHolder {
        return ViewOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_order_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewOrderViewHolder, position: Int) {
        val model: Product = list[position]
        holder.position.text = (position + 1).toString()
        holder.tvTitle.text = model.name
        holder.Qty.text = "Qty - ${model.baseqty.toString()}"
        holder.price.text = "Price ${model.discountedprice.toString()}$"
        Picasso.get().load(Variables.ASSETS_URL + model.thumbnail_image)
            .placeholder(R.drawable.corner_view).into(holder.iv);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView = itemView.findViewById(R.id.iv)
        val tvTitle: TextView = itemView.findViewById(R.id.title)
        val Qty: TextView = itemView.findViewById(R.id.qty)
        val price: TextView = itemView.findViewById(R.id.price)
        val position: TextView = itemView.findViewById(R.id.position)

    }
}