package com.dexterapps.easymarketvendor.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.home.adapter.NewOrderAdapter
import com.dexterapps.easymarketvendor.home.interfaces.OrderInterface
import com.dexterapps.easymarketvendor.home.model.DataX
import com.dexterapps.easymarketvendor.home.model.OrderResponse
import com.dexterapps.easymarketvendor.home.viewModel.OrderViewModel


class NewOrderTabFragment : Fragment() {
    val newOrderList: ArrayList<DataX> = arrayListOf()
    lateinit var newOrderAdapter: NewOrderAdapter
    lateinit var orderViewModel: OrderViewModel
    lateinit var orderResponse: OrderResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_new_order_tab, container, false)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        MainActivity.hideShow(Variables.NAME_NEW_ORDER, false)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        val tabAll: TextView
        val tabPending: TextView
        val tabAccepted: TextView

        getOrders(57)
        val rvNewOrder: RecyclerView = view.findViewById(R.id.rv_new_order)
        val b_tab_dashboard: LinearLayout = view.findViewById(R.id.b_tab_dashboard)
        rvNewOrder.layoutManager = LinearLayoutManager(context)
        newOrderAdapter = NewOrderAdapter(newOrderList, object : OrderInterface {
            override fun viewButtonClick() {

            }
        })
        rvNewOrder.adapter = newOrderAdapter

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

    private fun getOrders(id: Int) {
        Utill.showLoader(context!!)
        orderViewModel.getOrder(id)!!.observe(viewLifecycleOwner, Observer {
            Utill.cancelLoader()
            Log.d(TAG, "getOrders: $it")
            orderResponse = it
            newOrderList.clear()
            for (i in it.data) {
                newOrderList.add(i)
            }
            newOrderAdapter.notifyDataSetChanged()
        })
    }


}