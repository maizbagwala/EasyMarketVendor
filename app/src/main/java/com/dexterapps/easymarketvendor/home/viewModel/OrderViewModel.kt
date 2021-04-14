package com.dexterapps.easymarketvendor.home.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.home.model.OrderResponse
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : ViewModel() {
    var orderLiveData: MutableLiveData<OrderResponse>? = null
    fun getOrder(id: Int): MutableLiveData<OrderResponse>? {
        if (orderLiveData == null) {
            orderLiveData = MutableLiveData()
        }
        loadGetOrder(id)
        return orderLiveData
    }

    private fun loadGetOrder(id: Int) {
        val call = RetrofitClient.getClient().getOrder(id)
        call.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                orderLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

        })
    }
}