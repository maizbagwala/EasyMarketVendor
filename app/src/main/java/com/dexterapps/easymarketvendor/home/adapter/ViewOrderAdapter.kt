package com.dexterapps.easymarketvendor.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R

class ViewOrderAdapter() :
    RecyclerView.Adapter<ViewOrderAdapter.ViewOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewOrderViewHolder {
        return ViewOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_order_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewOrderViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 3
    }

    class ViewOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}