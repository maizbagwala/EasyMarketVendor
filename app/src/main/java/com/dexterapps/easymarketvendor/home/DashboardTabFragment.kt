package com.dexterapps.easymarketvendor.home

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.addproduct.AddProductFragment
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.home.model.DashboardResponse
import com.dexterapps.easymarketvendor.home.viewModel.DashboardViewModel
import com.dexterapps.easymarketvendor.mainProduct.AddMainProductFragment

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


    var mcontext: Context? = null
    private var tv_add_product: TextView? = null
    lateinit var dashboardViewModel: DashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mcontext = context
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard_tab, container, false)
        val tab_order: LinearLayout = view.findViewById(R.id.b_tab_order)
        tv_add_product = view.findViewById(R.id.tv_add_product) as TextView
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        onClick()
        getDashboardData()

        tab_order.setOnClickListener {

            MainActivity.loadFragment(
                NewOrderTabFragment(),
                Variables.TAG_NEW_ORDER
            )

        }

        MainActivity.hideShow(Variables.NAME_DASHBOARD, false)
        return view
    }

    private fun getDashboardData() {

        val dialog: Dialog = Dialog(context!!).apply {
            val wlmp: WindowManager.LayoutParams = window!!.attributes
            wlmp.gravity = Gravity.CENTER_HORIZONTAL
            window!!.attributes = wlmp
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setTitle(null)
            setCancelable(false)
            setOnCancelListener(null)
            val view = LayoutInflater.from(context).inflate(
                R.layout.custom_loader, null
            )
            setContentView(view)
        }
        dialog.show()


        dashboardViewModel.getDashboardData(57)?.observe(viewLifecycleOwner, {
            Log.d(
                TAG, "getDashboardData: $it"
            )
            setData(view!!, it)
            dialog.dismiss()
        })
    }

    private fun setData(view: View, dashboardResponse: DashboardResponse) {
        view.findViewById<TextView>(R.id.tv_total_order).text =
            dashboardResponse.data.total_orders.toString()
        view.findViewById<TextView>(R.id.tv_total_visitor).text =
            dashboardResponse.data.total_visitor.toString()
        view.findViewById<TextView>(R.id.tv_revenue_online).text =
            dashboardResponse.data.revenue_online
        view.findViewById<TextView>(R.id.tv_revenue_cash).text =
            dashboardResponse.data.revenue_cash.toString()
        view.findViewById<TextView>(R.id.tv_cancel_order).text =
            dashboardResponse.data.cancelled_orders.toString()
        view.findViewById<TextView>(R.id.tv_pending_order).text =
            dashboardResponse.data.pending_orders.toString()
        view.findViewById<TextView>(R.id.tv_todays_pending_orders).text =
            dashboardResponse.data.todays_pending_orders.toString()
    }

    private fun onClick() {
        tv_add_product!!.setOnClickListener {
            Log.d(TAG, "onCreateView: ")


            MainActivity.loadFragment(AddMainProductFragment(), Variables.TAG_ADD_MAIN_PRODUCT)

        }
    }

}