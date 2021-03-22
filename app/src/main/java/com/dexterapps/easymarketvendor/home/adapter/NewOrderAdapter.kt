package com.dexterapps.easymarketvendor.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.ViewOrderFragment
import com.dexterapps.easymarketvendor.home.model.NewOrderModel

class NewOrderAdapter(newOrderList: ArrayList<NewOrderModel>) :
    RecyclerView.Adapter<NewOrderAdapter.NewOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderViewHolder {
        return NewOrderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewOrderViewHolder, position: Int) {
        holder.btn_view.setOnClickListener {
            MainActivity.loadFragment(ViewOrderFragment(), Variables.TAG_VIEW_ORDER)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    class NewOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn_view: TextView = itemView.findViewById(R.id.btn_view)

    }
}