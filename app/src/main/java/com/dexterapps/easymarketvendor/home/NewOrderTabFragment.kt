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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewOrderTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewOrderTabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_new_order_tab, container, false)

        MainActivity.hideShow(Variables.NAME_NEW_ORDER,false)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        var tabAll: TextView
        var tabPending: TextView
        var tabAccepted: TextView

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewOrderTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewOrderTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}