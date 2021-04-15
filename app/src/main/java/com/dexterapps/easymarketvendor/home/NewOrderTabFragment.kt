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
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.adapter.NewOrderAdapter
import com.dexterapps.easymarketvendor.home.model.NewOrderModel


class NewOrderTabFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_new_order_tab, container, false)

        MainActivity.hideShow(Variables.NAME_NEW_ORDER, false)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        val tabAll: TextView
        val tabPending: TextView
        val tabAccepted: TextView

        val rvNewOrder: RecyclerView = view.findViewById(R.id.rv_new_order)
        val b_tab_dashboard: LinearLayout = view.findViewById(R.id.b_tab_dashboard)
        val newOrderList: ArrayList<NewOrderModel> = arrayListOf()
        rvNewOrder.layoutManager = LinearLayoutManager(context)
        rvNewOrder.adapter = NewOrderAdapter(newOrderList)

        tabAll = view.findViewById(R.id.tab_all)
        tabPending = view.findViewById(R.id.tab_pending)
        tabAccepted = view.findViewById(R.id.tab_accepted)
        b_tab_dashboard.setOnClickListener {
            MainActivity.loadFragment(DashboardTabFragment(), Variables.TAG_DASHBOARD)
        }

        tabAll.setOnClickListener {

            tabAll.setTextColor(resources.getColor(R.color.colorGreen))
            tabPending.setTextColor(resources.getColor(R.color.white))
            tabAccepted.setTextColor(resources.getColor(R.color.white))
            tabAll.setBackgroundResource(R.drawable.corner_view)
            tabPending.setBackgroundResource(R.drawable.greenbg_cornner)
            tabAccepted.setBackgroundResource(R.drawable.greenbg_cornner)
        }

        tabPending.setOnClickListener {
            tabAll.setTextColor(resources.getColor(R.color.white))
            tabPending.setTextColor(resources.getColor(R.color.colorGreen))
            tabAccepted.setTextColor(resources.getColor(R.color.white))
            tabAll.setBackgroundResource(R.drawable.greenbg_cornner)
            tabPending.setBackgroundResource(R.drawable.corner_view)
            tabAccepted.setBackgroundResource(R.drawable.greenbg_cornner)
        }

        tabAccepted.setOnClickListener {
            tabAll.setTextColor(resources.getColor(R.color.white))
            tabPending.setTextColor(resources.getColor(R.color.white))
            tabAccepted.setTextColor(resources.getColor(R.color.colorGreen))
            tabAll.setBackgroundResource(R.drawable.greenbg_cornner)
            tabPending.setBackgroundResource(R.drawable.greenbg_cornner)
            tabAccepted.setBackgroundResource(R.drawable.corner_view)
        }
        

        return view
    }


}