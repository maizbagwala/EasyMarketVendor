package com.dexterapps.easymarketvendor.myDeliveryPerson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.myDeliveryPerson.model.Data

class MyDeliveryPersonAdapter(val list: ArrayList<Data>) :
    RecyclerView.Adapter<MyDeliveryPersonAdapter.MyDeliveryPersonAdapterHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyDeliveryPersonAdapterHolder {
        return MyDeliveryPersonAdapterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_my_delivery_person, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyDeliveryPersonAdapterHolder, position: Int) {

        val model = list[position]

        holder.name.text = model.d_name
        holder.position.text = (position + 1).toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyDeliveryPersonAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.tv_delivery_person_name)
        val position: TextView = itemView.findViewById(R.id.position_delivery_person)
        val image: ImageView = itemView.findViewById(R.id.iv_driver)
    }
}