package com.dexterapps.easymarketvendor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.addproduct.AddProductFragment
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.adapter.ViewOrderAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewOrderFragment : Fragment() {
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
        val view: View = inflater.inflate(R.layout.fragment_view_order, container, false)

        MainActivity.hideShow(Variables.NAME_VIEW_ORDER, true)
        view.findViewById<LinearLayout>(R.id.b_tab_accept).setOnClickListener {
            MainActivity.loadFragment(AddProductFragment(),Variables.TAG_ADD_PRODUCT)
        }

        val rvViewOrder: RecyclerView = view.findViewById(R.id.rv_view_order)
        rvViewOrder.layoutManager = LinearLayoutManager(context)
        rvViewOrder.adapter = ViewOrderAdapter()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}