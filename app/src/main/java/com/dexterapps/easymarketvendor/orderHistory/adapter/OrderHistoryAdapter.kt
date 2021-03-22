package com.dexterapps.easymarketvendor.orderHistory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.orderHistory.model.OrderHistoryModel

class OrderHistoryAdapter(
    private val context: Context,
    private val dataList: List<OrderHistoryModel>
) : RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_history_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        init {


        }
    }


}