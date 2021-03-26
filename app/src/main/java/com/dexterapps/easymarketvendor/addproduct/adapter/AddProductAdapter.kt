package com.dexterapps.easymarketvendor.addproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R

class AddProductAdapter() :
    RecyclerView.Adapter<AddProductAdapter.AddProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductViewHolder {
        return AddProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AddProductViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

    class AddProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}