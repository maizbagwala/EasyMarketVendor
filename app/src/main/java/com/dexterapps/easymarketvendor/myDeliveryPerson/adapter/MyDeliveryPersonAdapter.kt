package com.dexterapps.easymarketvendor.myDeliveryPerson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R

class MyDeliveryPersonAdapter() :
    RecyclerView.Adapter<MyDeliveryPersonAdapter.MyDeliveryPersonAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDeliveryPersonAdapterHolder {
        return MyDeliveryPersonAdapterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_my_delivery_person, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyDeliveryPersonAdapterHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 3
    }

    class MyDeliveryPersonAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}