package com.dexterapps.easymarketvendor.mainProduct.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.mainProduct.model.productCategoryModel
import com.dexterapps.easymarketvendor.register.model.businessCategoryModel
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductCategoryViewModel : ViewModel() {
    private var categoryList: MutableLiveData<productCategoryModel>? = null
    fun getProductCategory(): MutableLiveData<productCategoryModel>? {
        if (categoryList == null) {
            categoryList = MutableLiveData<productCategoryModel>()
            getData()
        }
        return categoryList
    }

    private fun getData() {
        var myresponse: productCategoryModel
        var call = RetrofitClient.getClient().getProductCategory()
        call.enqueue(object : Callback<productCategoryModel> {
            override fun onResponse(
                call: Call<productCategoryModel>,
                response: Response<productCategoryModel>
            ) {
                Log.d(Variables.TAG, "onResponse: " + response.body())
                myresponse = response.body()!!
                categoryList?.value = myresponse
            }

            override fun onFailure(call: Call<productCategoryModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}