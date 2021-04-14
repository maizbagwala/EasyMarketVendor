package com.dexterapps.easymarketvendor.offerCreation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.offerCreation.adapter.OfferAdapter
import com.dexterapps.easymarketvendor.offerCreation.interfaces.OfferInterface
import com.dexterapps.easymarketvendor.offerCreation.model.Data
import com.dexterapps.easymarketvendor.offerCreation.viewModel.OfferCreationViewModel


class ShowOfferFragment : Fragment() {
    private lateinit var offerAdapter: OfferAdapter
    private var offerList = ArrayList<Data>()
    lateinit var offerViewModel: OfferCreationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_show_offer, container, false)
        offerViewModel = ViewModelProvider(this).get(OfferCreationViewModel::class.java)
        val rvOffer: RecyclerView = root.findViewById(R.id.rv_offer)
        MainActivity.hideShow(Variables.NAME_VIEW_OFFER, true)

        offerAdapter = OfferAdapter(offerList, object : OfferInterface {
            override fun deleteOffer(id: Int) {
                Utill.showLoader(context!!)
                offerViewModel.deleteOffer(id)!!.observe(viewLifecycleOwner, Observer {
                    Utill.cancelLoader()
                    Log.d(TAG, "deleteOffer: $it")
                    MainActivity.snack(root, it.message)
                    getOffer()
                })
            }

            override fun updateOffer(model: Data) {
                val offerCreation = OfferCreation()
                val bundle = Bundle()
                bundle.putSerializable("model", model)
                offerCreation.arguments = bundle
                MainActivity.loadFragment(offerCreation, Variables.TAG_OFFER_CREATION)
            }

        })
        rvOffer.layoutManager = GridLayoutManager(context, 2)
        rvOffer.adapter = offerAdapter

        root.findViewById<TextView>(R.id.add_offer).setOnClickListener {
            MainActivity.loadFragment(OfferCreation(), Variables.TAG_OFFER_CREATION)
        }

        getOffer()
        return root
    }

    private fun getOffer() {
        Utill.showLoader(context!!)
        offerViewModel.getOffer(57)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Utill.cancelLoader()
            Log.d(TAG, "onCreateView: ${it.data}")
            offerList.clear()
            for (i in it.data) {
                offerList.add(i)
            }
            offerAdapter.notifyDataSetChanged()

        })

    }
}