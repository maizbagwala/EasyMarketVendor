package com.dexterapps.easymarketvendor.delivery_slots.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.delivery_slots.model.SlotResponse
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliverySlotViewModel : ViewModel() {

    private var myLiveData: MutableLiveData<SlotResponse>? = null
    fun getSlot(id: Int): MutableLiveData<SlotResponse>? {

        if (myLiveData == null) {
            myLiveData = MutableLiveData<SlotResponse>()
        }
        loadData(id)

        return myLiveData
    }

    private fun loadData(id: Int) {

        val call = RetrofitClient.getClient().getSlot(id)
        call.enqueue(object : Callback<SlotResponse> {
            override fun onResponse(call: Call<SlotResponse>, response: Response<SlotResponse>) {
                Log.d(Variables.TAG, "onResponse: " + response.body())
                Log.d(TAG, "onResponse before put: $myLiveData")

                myLiveData!!.value = response.body()
                Log.d(TAG, "onResponse after put: $myLiveData")

            }

            override fun onFailure(call: Call<SlotResponse>, t: Throwable) {
                Log.e(Variables.TAG, "onFailure: $t",)
            }
        })

    }

    private var createSlotLiveData: MutableLiveData<SlotResponse>? = null
    fun createSlot(
        id: Int,
        t_date: String,
        t_day: String,
        t_from: String,
        t_to: String
    ): MutableLiveData<SlotResponse>? {
        if (createSlotLiveData == null) {
            createSlotLiveData = MutableLiveData()
            loadCreateSlot(id, t_date, t_day, t_from, t_to)
        }
        return createSlotLiveData
    }

    private fun loadCreateSlot(
        id: Int,
        t_date: String,
        t_day: String,
        t_from: String,
        t_to: String
    ) {
        val call = RetrofitClient.getClient().createSlot(id, t_date, t_day, t_from, t_to)
        call.enqueue(object : Callback<SlotResponse> {
            override fun onResponse(call: Call<SlotResponse>, response: Response<SlotResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                Log.d(TAG, "onResponse before put: $createSlotLiveData")
                createSlotLiveData!!.value = response.body()
                Log.d(TAG, "onResponse: after put $createSlotLiveData")

            }

            override fun onFailure(call: Call<SlotResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

        })
    }

    private var deleteLiveData: MutableLiveData<SlotResponse>? = null
    fun deleteSlot(id: Int): MutableLiveData<SlotResponse>? {
        if (deleteLiveData == null) {
            deleteLiveData = MutableLiveData()
        }
        loadDeleteSlot(id)
        return deleteLiveData
    }

    private fun loadDeleteSlot(id: Int) {
        val call = RetrofitClient.getClient().deleteSlot(id)
        call.enqueue(object : Callback<SlotResponse> {
            override fun onResponse(call: Call<SlotResponse>, response: Response<SlotResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                deleteLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<SlotResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

        })

    }

    private var updateLiveData: MutableLiveData<SlotResponse>? = null
    fun updateSlot(
        uId: Int,
        tId: Int,
        t_date: String,
        t_day: String,
        t_from: String,
        t_to: String
    ): MutableLiveData<SlotResponse>? {
        if (updateLiveData == null) {
            updateLiveData = MutableLiveData()
        }
        loadUpdateSlot(uId, tId, t_date, t_day, t_from, t_to)
        return updateLiveData
    }

    private fun loadUpdateSlot(
        uId: Int,
        tId: Int,
        t_date: String,
        t_day: String,
        t_from: String,
        t_to: String
    ) {
        val call = RetrofitClient.getClient().updateSlot(uId, tId, t_date, t_day, t_from, t_to)
        call.enqueue(object : Callback<SlotResponse> {
            override fun onResponse(call: Call<SlotResponse>, response: Response<SlotResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                updateLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<SlotResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

        })
    }

}