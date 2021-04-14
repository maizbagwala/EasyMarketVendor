package com.dexterapps.easymarketvendor.home.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.home.model.DashboardResponse
import com.dexterapps.easymarketvendor.login.model.User
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    private var dashboardData: MutableLiveData<DashboardResponse>? = null
    fun getDashboardData(id: Int): MutableLiveData<DashboardResponse>? {
        if (dashboardData == null) {
            dashboardData = MutableLiveData<DashboardResponse>()
            //we will load it asynchronously from server in this method
            loadData(id)
        }
        return dashboardData
    }

    private fun loadData(id: Int) {
        var myresponse: DashboardResponse
        val call = RetrofitClient.getClient().getDatabase(id)
        call.enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                Log.d(Variables.TAG, "onResponse: " + response.body())
                myresponse = response.body()!!
                dashboardData?.value = myresponse
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: $t",)
            }
        })

    }
}