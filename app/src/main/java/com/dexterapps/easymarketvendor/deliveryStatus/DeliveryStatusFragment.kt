package com.dexterapps.easymarketvendor.deliveryStatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.adapter.ViewDeliveryOrderStatusAdapter
import com.dexterapps.easymarketvendor.home.model.ViewDeliveryOrderStatusModel

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
        var tabAccepted: TextView
        var tabCancelled: TextView

        val rvDeliveryOrderStatus: RecyclerView = view.findViewById(R.id.rv_delivery_order_status)

        val deliveryOrderStatusList: ArrayList<ViewDeliveryOrderStatusModel> = arrayListOf()
        rvDeliveryOrderStatus.layoutManager = LinearLayoutManager(context)
        rvDeliveryOrderStatus.adapter = ViewDeliveryOrderStatusAdapter(deliveryOrderStatusList)

        tabAll = view.findViewById(R.id.tab_all)
        tabAccepted = view.findViewById(R.id.tab_accepted)
        tabCancelled = view.findViewById(R.id.tab_cancelled)



        tabAll.setOnClickListener {

            tabAll.setTextColor(resources.getColor(R.color.colorGreen))
            tabCancelled.setTextColor(resources.getColor(R.color.white))
            tabAccepted.setTextColor(resources.getColor(R.color.white))
            tabAll.setBackgroundResource(R.drawable.corner_view)
            tabCancelled.setBackgroundResource(R.drawable.greenbg_cornner)
            tabAccepted.setBackgroundResource(R.drawable.greenbg_cornner)
        }

        tabCancelled.setOnClickListener {
            tabAll.setTextColor(resources.getColor(R.color.white))
            tabCancelled.setTextColor(resources.getColor(R.color.colorGreen))
            tabAccepted.setTextColor(resources.getColor(R.color.white))
            tabAll.setBackgroundResource(R.drawable.greenbg_cornner)
            tabCancelled.setBackgroundResource(R.drawable.corner_view)
            tabAccepted.setBackgroundResource(R.drawable.greenbg_cornner)
        }

        tabAccepted.setOnClickListener {
            tabAll.setTextColor(resources.getColor(R.color.white))
            tabCancelled.setTextColor(resources.getColor(R.color.white))
            tabAccepted.setTextColor(resources.getColor(R.color.colorGreen))
            tabAll.setBackgroundResource(R.drawable.greenbg_cornner)
            tabCancelled.setBackgroundResource(R.drawable.greenbg_cornner)
            tabAccepted.setBackgroundResource(R.drawable.corner_view)
        }





        return view
    }


}