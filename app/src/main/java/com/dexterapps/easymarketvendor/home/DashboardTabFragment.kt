package com.dexterapps.easymarketvendor.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.addproduct.AddProductFragment
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.viewModel.DashboardViewModel
import com.dexterapps.easymarketvendor.productList.productList


class DashboardTabFragment : Fragment() {

    lateinit var dashboardViewModel: DashboardViewModel
    private var tvAddProduct: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard_tab, container, false)
        val tabOrder: LinearLayout = view.findViewById(R.id.b_tab_order)
        tvAddProduct = view.findViewById(R.id.tv_product)



        onClick()

        setDashboardData()

        tabOrder.setOnClickListener {

            MainActivity.loadFragment(
                NewOrderTabFragment(),
                Variables.TAG_NEW_ORDER
            )

        }

        MainActivity.hideShow(Variables.NAME_DASHBOARD, false)
        return view
    }

    private fun setDashboardData() {
//        dashboardViewModel.getDashboardData().observe(viewLifecycleOwner, Observer {
//
//        })

    }

    private fun onClick() {
        tvAddProduct!!.setOnClickListener {
            Log.d(Variables.TAG, "onCreateView: ")


            MainActivity.loadFragment(productList(), "productList")

        }
    }

}