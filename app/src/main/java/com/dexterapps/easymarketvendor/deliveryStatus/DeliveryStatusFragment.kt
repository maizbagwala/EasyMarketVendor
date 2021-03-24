package com.dexterapps.easymarketvendor.deliveryStatus

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
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.DashboardTabFragment
import com.dexterapps.easymarketvendor.home.adapter.NewOrderAdapter
import com.dexterapps.easymarketvendor.home.model.NewOrderModel

class DeliveryStatusFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_delivery_status, container, false)

        MainActivity.hideShow(Variables.NAME_DELIVERY_STATUS, true)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }

        var tabAll: TextView
        var tabPending: TextView
        var tabAccepted: TextView

        val rvNewOrder: RecyclerView = view.findViewById(R.id.rv_new_order)

        val newOrderList: ArrayList<NewOrderModel> = arrayListOf()
        rvNewOrder.layoutManager = LinearLayoutManager(context)
        rvNewOrder.adapter = NewOrderAdapter(newOrderList)

        tabAll = view.findViewById(R.id.tab_all)
        tabPending = view.findViewById(R.id.tab_pending)
        tabAccepted = view.findViewById(R.id.tab_accepted)

        tabAll.setOnClickListener {

            tabAll.setTextColor(resources.getColor(R.color.colorGreen))
            tabPending.setTextColor(resources.getColor(R.color.white))
            tabAccepted.setTextColor(resources.getColor(R.color.white))
            tabAll.setBackgroundResource(R.drawable.custom_border_bg_square)
            tabPending.setBackgroundResource(R.drawable.custom_green_bg_square)
            tabAccepted.setBackgroundResource(R.drawable.custom_green_bg_square)
        }

        tabPending.setOnClickListener {
            tabAll.setTextColor(resources.getColor(R.color.white))
            tabPending.setTextColor(resources.getColor(R.color.colorGreen))
            tabAccepted.setTextColor(resources.getColor(R.color.white))
            tabAll.setBackgroundResource(R.drawable.custom_green_bg_square)
            tabPending.setBackgroundResource(R.drawable.custom_border_bg_square)
            tabAccepted.setBackgroundResource(R.drawable.custom_green_bg_square)
        }

        tabAccepted.setOnClickListener {
            tabAll.setTextColor(resources.getColor(R.color.white))
            tabPending.setTextColor(resources.getColor(R.color.white))
            tabAccepted.setTextColor(resources.getColor(R.color.colorGreen))
            tabAll.setBackgroundResource(R.drawable.custom_green_bg_square)
            tabPending.setBackgroundResource(R.drawable.custom_green_bg_square)
            tabAccepted.setBackgroundResource(R.drawable.custom_border_bg_square)
        }

        return view
    }


}