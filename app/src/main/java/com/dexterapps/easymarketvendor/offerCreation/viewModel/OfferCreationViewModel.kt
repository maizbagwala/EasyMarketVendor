package com.dexterapps.easymarketvendor.offerCreation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.delivery_slots.model.SlotResponse
import com.dexterapps.easymarketvendor.offerCreation.model.OfferCreationModel
import com.dexterapps.easymarketvendor.offerCreation.model.offerResponse
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfferCreationViewModel : ViewModel() {
    private var liveData: MutableLiveData<OfferCreationModel>? = null
    fun createOffer(
        userId: String,
        couponsCode: String,
        discount: String,
        discountType: String,
        startDate: String,
        endDate: String,
        couponName: String,
        minBuy: String,
        maxDiscount: String
    ): MutableLiveData<OfferCreationModel>? {

        if (liveData == null) {
            liveData = MutableLiveData<OfferCreationModel>()
            callApi(
                userId,
                couponsCode,
                discount,
                discountType,
                startDate,
                endDate,
                couponName,
                minBuy,
                maxDiscount
            )
        }
        return liveData
    }

    private fun callApi(
        userId: String,
        couponsCode: String,
        discount: String,
        discountType: String,
        startDate: String,
        endDate: String,
        couponName: String,
        minBuy: String,
        maxDiscount: String
    ) {
        val call = RetrofitClient.getClient().createOffer(
            userId,
            couponsCode,
            discount,
            discountType,
            startDate,
            endDate,
            couponName,
            minBuy,
            maxDiscount
        )

        call.enqueue(object : Callback<OfferCreationModel> {
            override fun onResponse(
                call: Call<OfferCreationModel>,
                response: Response<OfferCreationModel>
            ) {
                Log.d(TAG, "onResponse: " + response.body())

                liveData!!.value = response.body()

            }

            override fun onFailure(call: Call<OfferCreationModel>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }

    private var offerLiveData: MutableLiveData<offerResponse>? = null
    fun getOffer(id: Int): MutableLiveData<offerResponse>? {
        if (offerLiveData == null) {
            offerLiveData = MutableLiveData()
        }

        loadOfferData(id)
        return offerLiveData

    }

    private fun loadOfferData(id: Int) {
        val call = RetrofitClient.getClient().getOffer(id)
        call.enqueue(object : Callback<offerResponse> {
            override fun onFailure(call: Call<offerResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }

            override fun onResponse(call: Call<offerResponse>, response: Response<offerResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                offerLiveData!!.value = response.body()
            }

        })
    }

    private var deleteLiveData: MutableLiveData<offerResponse>? = null
    fun deleteOffer(id: Int): MutableLiveData<offerResponse>? {
        if (deleteLiveData == null) {
            deleteLiveData = MutableLiveData()
        }
        loadDeleteSlot(id)
        return deleteLiveData
    }

    private fun loadDeleteSlot(id: Int) {
        val call = RetrofitClient.getClient().deleteOffer(id)
        call.enqueue(object : Callback<offerResponse> {
            override fun onResponse(call: Call<offerResponse>, response: Response<offerResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                deleteLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<offerResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

        })

    }


    private var updateLiveData: MutableLiveData<offerResponse>? = null
    fun updateOffer(
        userId: String,
        couponsCode: String,
        discount: String,
        discountType: String,
        startDate: String,
        endDate: String,
        couponName: String,
        minBuy: String,
        maxDiscount: String,
        cid: String
    ): MutableLiveData<offerResponse>? {
        if (updateLiveData == null) {
            updateLiveData = MutableLiveData()
        }
        loadUpdateOffer(
            userId,
            couponsCode,
            discount,
            discountType,
            startDate,
            endDate,
            couponName,
            minBuy,
            maxDiscount,
            cid
        )
        return updateLiveData
    }

    private fun loadUpdateOffer(
        userId: String,
        couponsCode: String,
        discount: String,
        discountType: String,
        startDate: String,
        endDate: String,
        couponName: String,
        minBuy: String,
        maxDiscount: String,
        cid: String
    ) {
        val call = RetrofitClient.getClient().updateOffer(
            userId,
            couponsCode,
            discount,
            discountType,
            startDate,
            endDate,
            couponName,
            minBuy,
            maxDiscount,
            cid
        )

        call.enqueue(object : Callback<offerResponse> {
            override fun onFailure(call: Call<offerResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

            override fun onResponse(call: Call<offerResponse>, response: Response<offerResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                updateLiveData!!.value = response.body()
            }

        })
    }
}