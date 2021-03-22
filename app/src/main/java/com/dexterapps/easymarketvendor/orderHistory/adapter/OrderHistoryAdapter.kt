package com.dexterapps.easymarketvendor.orderHistory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.dexterapps.easymarket.myCart.model.OrderHistoryModel
import com.dexterapps.easymarketvendor.R

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
        val data = dataList[position]

        if (data.d_status.equals("Delivery Pending")) {
            holder.tv_d_status!!.setTextColor(ContextCompat.getColor(context,R.color.color7))
        } else {
            holder.tv_d_status!!.setTextColor(ContextCompat.getColor(context,R.color.colorGreen))
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_d_status: TextView? = null

        init {
            tv_d_status = view.findViewById(R.id.tv_d_status)


        }
    }


}