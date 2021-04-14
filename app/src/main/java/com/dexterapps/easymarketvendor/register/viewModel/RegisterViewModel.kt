package com.dexterapps.easymarketvendor.register.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.register.model.businessCategoryModel
import com.dexterapps.easymarketvendor.register.model.registerResponse
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class RegisterViewModel : ViewModel() {
    private var categoryList: MutableLiveData<businessCategoryModel>? = null
    fun getRegCategory(): MutableLiveData<businessCategoryModel>? {
        if (categoryList == null) {
            categoryList = MutableLiveData<businessCategoryModel>()
            getData()
        }
        return categoryList
    }

    private fun getData() {
        var myresponse: businessCategoryModel
        var call = RetrofitClient.getClient().getRegCategory()
        call.enqueue(object : Callback<businessCategoryModel> {
            override fun onResponse(
                call: Call<businessCategoryModel>,
                response: Response<businessCategoryModel>
            ) {
                Log.d(TAG, "onResponse: " + response.body())
                myresponse = response.body()!!
                categoryList?.value = myresponse
            }

            override fun onFailure(call: Call<businessCategoryModel>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }
        })
    }

    private var registerLiveData: MutableLiveData<registerResponse>? = null
    fun registerVendor(
        firstname: String,
        lastname: String,
        mobileno: String,
        email: String,
        address: String,
        category: String,
        shop_name: String,
        password: String,
        password_confirmation: String
    ): MutableLiveData<registerResponse>? {

        if (registerLiveData == null) {
            registerLiveData = MutableLiveData()
        }

        loadRegistration(
            firstname,
            lastname,
            mobileno,
            email,
            address,
            category,
            shop_name,
            password,
            password_confirmation
        )
        return registerLiveData

    }

    private fun loadRegistration(
        firstname: String,
        lastname: String,
        mobileno: String,
        email: String,
        address: String,
        category: String,
        shop_name: String,
        password: String,
        password_confirmation: String
    ) {
        val call = RetrofitClient.getClient().register(
            firstname,
            lastname,
            mobileno,
            email,
            address,
            category,
            shop_name,
            password,
            password_confirmation
        )

        call.enqueue(object : Callback<registerResponse> {
            override fun onFailure(call: Call<registerResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }

            override fun onResponse(
                call: Call<registerResponse>,
                response: Response<registerResponse>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                registerLiveData!!.value = response.body()
            }
        })
    }
}