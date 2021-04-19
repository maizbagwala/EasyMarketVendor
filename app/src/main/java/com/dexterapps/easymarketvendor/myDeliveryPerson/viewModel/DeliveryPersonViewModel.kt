package com.dexterapps.easymarketvendor.myDeliveryPerson.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.myDeliveryPerson.model.DeliveryPersonResponse
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryPersonViewModel : ViewModel() {

    var getDeliveryPersonLiveData: MutableLiveData<DeliveryPersonResponse>? = null
    fun getDeliveryPerson(id: Int): MutableLiveData<DeliveryPersonResponse>? {

        if (getDeliveryPersonLiveData == null) {
            getDeliveryPersonLiveData = MutableLiveData()
        }
        loadGetDeliveryPerson(id)

        return getDeliveryPersonLiveData
    }

    private fun loadGetDeliveryPerson(id: Int) {
        val call = RetrofitClient.getClient().getDeliveryPerson(id)
        call.enqueue(object : Callback<DeliveryPersonResponse> {
            override fun onResponse(
                call: Call<DeliveryPersonResponse>,
                response: Response<DeliveryPersonResponse>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                getDeliveryPersonLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<DeliveryPersonResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

        })
    }


    var addDeliveryPersonLiveData: MutableLiveData<DeliveryPersonResponse>? = null
    fun addDeliveryPerson(
        id: RequestBody,
        name: RequestBody,
        email: RequestBody,
        mobile: RequestBody,
        password: RequestBody,
        licNo: RequestBody,
        address: RequestBody,
        licImage: MultipartBody.Part,
        profileImage: MultipartBody.Part
    ): MutableLiveData<DeliveryPersonResponse>? {
        if (addDeliveryPersonLiveData == null) {
            addDeliveryPersonLiveData = MutableLiveData()
        }
        loadAddDeliveryPerson(
            id,
            name,
            email,
            mobile,
            password,
            licNo,
            address,
            licImage,
            profileImage
        )

        return addDeliveryPersonLiveData
    }

    private fun loadAddDeliveryPerson(
        id: RequestBody,
        name: RequestBody,
        email: RequestBody,
        mobile: RequestBody,
        password: RequestBody,
        licNo: RequestBody,
        address: RequestBody,
        licImage: MultipartBody.Part,
        profileImage: MultipartBody.Part
    ) {
        val call = RetrofitClient.getClient().addDeliveryPerson(
            id,
            name,
            email,
            mobile,
            password,
            licNo,
            address,
            licImage,
            profileImage
        )

        call.enqueue(object : Callback<DeliveryPersonResponse> {
            override fun onResponse(
                call: Call<DeliveryPersonResponse>,
                response: Response<DeliveryPersonResponse>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                addDeliveryPersonLiveData!!.value = response.body()
            }

            override fun onFailure(call: Call<DeliveryPersonResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }
        })
    }
}