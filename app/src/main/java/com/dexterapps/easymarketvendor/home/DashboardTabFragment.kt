package com.dexterapps.easymarketvendor.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardTabFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard_tab, container, false)
        val tab_order: LinearLayout = view.findViewById(R.id.b_tab_order)

        tab_order.setOnClickListener {

            MainActivity.loadFragment(
                NewOrderTabFragment(),
                Variables.TAG_NEW_ORDER
            )

        }

        MainActivity.hideShow(Variables.NAME_DASHBOARD,false)
        return view
    }

}