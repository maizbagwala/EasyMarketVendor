package com.dexterapps.easymarketvendor.orderHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarket.myCart.adapter.OrderHistoryAdapter
import com.dexterapps.easymarket.myCart.model.OrderHistoryModel
import com.dexterapps.easymarketvendor.R
import java.util.ArrayList

class OrderHistoryFragment : Fragment() {
    private var ll_pending_delivery: LinearLayout? = null
    private var ll_past_delivery: LinearLayout? = null
    private var tv_pending: TextView? = null
    private var tv_past: TextView? = null
    private var orderHistoryModel: OrderHistoryModel? = null
    private val orderHistoryModelArrayList: MutableList<OrderHistoryModel> = ArrayList()
    private var orderHistoryAdapter: OrderHistoryAdapter? = null
    private var rv_order_history: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_order_history, container, false)
        initView(root)
        return root
    }

    private fun initView(root: View?) {
        ll_pending_delivery = root?.findViewById(R.id.ll_pending_delivery)
        ll_past_delivery = root?.findViewById(R.id.ll_past_delivery)
        tv_pending = root?.findViewById(R.id.tv_pending)
        tv_past = root?.findViewById(R.id.tv_past)
        rv_order_history = root?.findViewById(R.id.rv_order_history)

        initClick()

        orderHistoryAdapter = OrderHistoryAdapter(getContext()!!, orderHistoryModelArrayList)
        val layoutManager = LinearLayoutManager(getContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_order_history!!.layoutManager = layoutManager
        rv_order_history!!.itemAnimator = DefaultItemAnimator()
        rv_order_history!!.adapter = orderHistoryAdapter

        loadDataPending()


    }

    private fun loadDataPending() {
        orderHistoryModelArrayList.clear()

        for (i in 1..10) {
            orderHistoryModel = OrderHistoryModel(
                "1",
                "Varun Jhaveri",
                "05",
                "0123456789",
                " 12,340\$",
                "22/02/2021 At 11:00 Am",
                "100\$",
                "05",
                "Home Delivery",
                "Online",
                "Accepted",
                "Delivery Done",
                "9874561230",
                "1,kumud app, B/h Gwalia\\n vasna, Ahmedabad, 380007, Gujrat, India"

            )
            orderHistoryModelArrayList.add(orderHistoryModel!!)
        }
        orderHistoryAdapter!!.notifyDataSetChanged()

    }

    private fun loadDataPast() {
        orderHistoryModelArrayList.clear()


        orderHistoryModel = OrderHistoryModel(
            "1",
            "Varun Jhaveri",
            "05",
            "0123456789",
            " 12,340\$",
            "22/02/2021 At 11:00 Am",
            "100\$",
            "05",
            "Home Delivery",
            "Online",
            "Accepted",
            "Delivery Pending",
            "9874561230",
            "1,kumud app, B/h Gwalia\\n vasna, Ahmedabad, 380007, Gujrat, India"

            )
        orderHistoryModelArrayList.add(orderHistoryModel!!)

        orderHistoryAdapter!!.notifyDataSetChanged()

    }

    private fun initClick() {
        ll_pending_delivery?.setOnClickListener {

            ll_pending_delivery!!.background =
                ContextCompat.getDrawable(context!!, R.drawable.custom_curved_green_bg)

            ll_past_delivery!!.background =
                ContextCompat.getDrawable(context!!, R.drawable.custom_border_bg)

            tv_pending!!.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            tv_past!!.setTextColor(ContextCompat.getColor(context!!, R.color.colorGreen))
            loadDataPending()

        }
        ll_past_delivery?.setOnClickListener {
            ll_pending_delivery!!.background =
                ContextCompat.getDrawable(context!!, R.drawable.custom_border_bg)

            ll_past_delivery!!.background =
                ContextCompat.getDrawable(context!!, R.drawable.custom_curved_green_bg)

            tv_pending!!.setTextColor(ContextCompat.getColor(context!!, R.color.colorGreen))
            tv_past!!.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            loadDataPast()
        }
    }


}