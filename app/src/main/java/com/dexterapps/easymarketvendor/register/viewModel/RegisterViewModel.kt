package com.dexterapps.easymarketvendor.register.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.register.model.businessCategoryModel
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                Log.d(Variables.TAG, "onResponse: " + response.body())
                myresponse = response.body()!!
                categoryList?.value = myresponse
            }

            override fun onFailure(call: Call<businessCategoryModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}