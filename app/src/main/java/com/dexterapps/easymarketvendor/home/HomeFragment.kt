package com.dexterapps.easymarketvendor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val tvDashboard: TextView = view.findViewById(R.id.b_tab_dashboard_tv)
        val tvOrder: TextView = view.findViewById(R.id.b_tab_order_tv)
        val tab_dashboard: LinearLayout = view.findViewById(R.id.b_tab_dashboard)
        val iv_dashboard: ImageView = view.findViewById(R.id.b_tab_dashboard_iv)
        val tv_dashboard: TextView = view.findViewById(R.id.b_tab_dashboard_tv)
        val tab_order: LinearLayout = view.findViewById(R.id.b_tab_order)
        val iv_order: ImageView = view.findViewById(R.id.b_tab_order_iv)
        val tv_order: TextView = view.findViewById(R.id.b_tab_order_tv)

        tvOrder.visibility = View.GONE
        tvDashboard.visibility = View.VISIBLE
        tab_dashboard.setBackgroundResource(R.drawable.custom_green_bg_square)
        tab_order.setBackgroundResource(R.drawable.custom_border_bg_square)
        tvDashboard.setTextColor(resources.getColor(R.color.white))
        iv_dashboard.setImageResource(R.drawable.ic_nav_dashboard)
        iv_order.setImageResource(R.drawable.ic_nav_dashboard_green)
        MainActivity.loadFragment(
            DashboardTabFragment(), Variables.TAG_DASHBOARD,
            R.id.frag_host_home
        )

        tab_dashboard.setOnClickListener {
            tvOrder.visibility = View.GONE
            tvDashboard.visibility = View.VISIBLE
            tab_dashboard.setBackgroundResource(R.drawable.custom_green_bg_square)
            tab_order.setBackgroundResource(R.drawable.custom_border_bg_square)
            tvDashboard.setTextColor(resources.getColor(R.color.white))
            iv_dashboard.setImageResource(R.drawable.ic_nav_dashboard)
            iv_order.setImageResource(R.drawable.ic_nav_dashboard_green)
            MainActivity.loadFragment(
                DashboardTabFragment(), Variables.TAG_DASHBOARD,
                R.id.frag_host_home
            )

        }
        view.findViewById<LinearLayout>(R.id.b_tab_order).setOnClickListener {
            tvDashboard.visibility = View.GONE
            tvOrder.visibility = View.VISIBLE
            tab_dashboard.setBackgroundResource(R.drawable.custom_border_bg_square)
            tab_order.setBackgroundResource(R.drawable.custom_green_bg_square)
            tvOrder.setTextColor(resources.getColor(R.color.white))
            iv_dashboard.setImageResource(R.drawable.ic_nav_dashboard_green)
            iv_order.setImageResource(R.drawable.ic_nav_dashboard)
            MainActivity.loadFragment(
                NewOrderTabFragment(),
                Variables.TAG_NEW_ORDER,
                R.id.frag_host_home
            )

        }


        return view
    }

//    private fun loadHomeFragment(fragment: Fragment, tag: String) {
//        parentFragmentManager.beginTransaction().replace(R.id.frag_host_home, fragment)
//            .commit()
//
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}