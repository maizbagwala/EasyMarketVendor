package com.dexterapps.easymarketvendor.offerCreation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.offerCreation.model.OfferCreationModel
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
}