package com.dexterapps.easymarketvendor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.addproduct.AddProductFragment
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.adapter.ViewOrderAdapter
import com.dexterapps.easymarketvendor.home.model.DataX
import com.dexterapps.easymarketvendor.home.model.Product

class ViewOrderFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_view_order, container, false)

        val bundle = this.arguments
        if (bundle != null) {
            val data: DataX = bundle.getSerializable("data") as DataX

            view.findViewById<TextView>(R.id.tv_order_number).text = data.code
            view.findViewById<TextView>(R.id.tv_date).text = data.datetime
            view.findViewById<TextView>(R.id.tv_total_item).text = data.products.size.toString()
            view.findViewById<TextView>(R.id.tv_address).text = data.shipping_address.address
            view.findViewById<TextView>(R.id.tv_sub_total).text = data.subtotal.toString()
            view.findViewById<TextView>(R.id.tv_delivery_fee).text = data.delivery.toString()
            view.findViewById<TextView>(R.id.tv_total).text = data.total.toString()
            val rvViewOrder: RecyclerView = view.findViewById(R.id.rv_view_order)
            rvViewOrder.layoutManager = LinearLayoutManager(context)
            rvViewOrder.adapter = ViewOrderAdapter(data.products as ArrayList<Product>)
        }
        MainActivity.hideShow(Variables.NAME_VIEW_ORDER, true)
        view.findViewById<LinearLayout>(R.id.b_tab_accept).setOnClickListener {
            MainActivity.loadFragment(AddProductFragment(), Variables.TAG_ADD_PRODUCT)
        }


        return view
    }


}