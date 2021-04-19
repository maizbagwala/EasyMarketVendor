package com.dexterapps.easymarketvendor.myDeliveryPerson

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.home.adapter.ViewOrderAdapter
import com.dexterapps.easymarketvendor.myDeliveryPerson.adapter.MyDeliveryPersonAdapter
import com.dexterapps.easymarketvendor.myDeliveryPerson.model.Data
import com.dexterapps.easymarketvendor.myDeliveryPerson.viewModel.DeliveryPersonViewModel


class MyDeliveryPersonFragment : Fragment() {
    lateinit var deliveryPersonViewModel: DeliveryPersonViewModel
    lateinit var adapter: MyDeliveryPersonAdapter
    var deliveryList = arrayListOf<Data>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_my_delivery_person, container, false)
        deliveryPersonViewModel = ViewModelProvider(this).get(DeliveryPersonViewModel::class.java)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }

        view.findViewById<TextView>(R.id.btn_add_delivery_person).setOnClickListener {
            MainActivity.loadFragment(
                AddDeliveryPersonFragment(),
                Variables.TAG_ADD_DELIVERY_PERSON
            )
        }
        MainActivity.hideShow(Variables.NAME_MY_DELIVERY_PERSON, true)

        val rvMyDeliveryPerson: RecyclerView = view.findViewById(R.id.rv_my_delivery_person)
        rvMyDeliveryPerson.layoutManager = LinearLayoutManager(context)
        adapter = MyDeliveryPersonAdapter(deliveryList)
        rvMyDeliveryPerson.adapter = adapter

        getDeliveryPerson(57)
        return view
    }

    private fun getDeliveryPerson(id: Int) {
        Utill.showLoader(context!!)
        deliveryPersonViewModel.getDeliveryPerson(id)!!.observe(viewLifecycleOwner, Observer {
            Utill.cancelLoader()
            Log.d(TAG, "getDeliveryPerson: $it")
            deliveryList.clear()
            for (i in it.data) {
                deliveryList.add(i)
            }
            adapter.notifyDataSetChanged()
        })
    }


}