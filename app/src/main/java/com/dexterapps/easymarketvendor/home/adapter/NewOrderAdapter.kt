package com.dexterapps.easymarketvendor.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.ViewOrderFragment
import com.dexterapps.easymarketvendor.home.interfaces.OrderInterface
import com.dexterapps.easymarketvendor.home.model.DataX

class NewOrderAdapter(
    val newOrderList: ArrayList<DataX>,
    val orderInterface: OrderInterface
) :
    RecyclerView.Adapter<NewOrderAdapter.NewOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderViewHolder {
        return NewOrderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewOrderViewHolder, position: Int) {
        val data = newOrderList[position]

        holder.tvOrderNo.text = data.code
        holder.tvDate.text = data.datetime
        holder.tvTotalItem.text = data.products.size.toString()
        holder.tvTotal.text = data.total.toString()
        holder.btnView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("data", data)
            val viewOrderFragment = ViewOrderFragment()
            viewOrderFragment.arguments = bundle
            MainActivity.loadFragment(viewOrderFragment, Variables.TAG_VIEW_ORDER)
        }
    }

    override fun getItemCount(): Int {
        return newOrderList.size
    }

    class NewOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btnView: TextView = itemView.findViewById(R.id.btn_view)
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvOrderNo: TextView = itemView.findViewById(R.id.tv_order_number)
        var tvDate: TextView = itemView.findViewById(R.id.tv_date)
        var tvTotalItem: TextView = itemView.findViewById(R.id.tv_total_item)
        var tvType: TextView = itemView.findViewById(R.id.tv_type)
        var tvPaymentMode: TextView = itemView.findViewById(R.id.tv_payment_mode)
        var tvOrderStatus: TextView = itemView.findViewById(R.id.tv_order_status)
        var tvDeliveryStatus: TextView = itemView.findViewById(R.id.tv_d_status)
        var tvTotal: TextView = itemView.findViewById(R.id.tv_total)

    }
}