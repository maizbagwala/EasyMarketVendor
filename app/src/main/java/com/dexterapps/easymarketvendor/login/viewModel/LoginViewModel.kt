package com.dexterapps.easymarketvendor.login.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.login.model.LoginResponse
import com.dexterapps.easymarketvendor.login.model.User
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    //this is the data that we will fetch asynchronously
    private var LoginList: MutableLiveData<List<User>>? =
        null//we will load it asynchronously from server in this method
    private var users: ArrayList<User> = ArrayList()

    //finally we will return the list
//if the list is null
    //we will call this method to get the data
    fun getLogin(): MutableLiveData<List<User>>? {
        //if the list is null
        if (LoginList == null) {
            LoginList = MutableLiveData<List<User>>()
            //we will load it asynchronously from server in this method
            loadLogin()
        }

        //finally we will return the list
        return LoginList
    }

    //This method is using Retrofit to get the JSON data from URL
    private fun loadLogin() {
        val call = RetrofitClient.getClient().login("9724396247")

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                Log.d("Done", response.body().toString())
                val userWrapper: LoginResponse? = response.body()
                if (userWrapper!!.success == 200) {
//                    MainApplication.sharedPreferencesUser.put(
//                        Variables.ACCESS_TOKEN,
//                        userWrapper.access_token
//                    )
//                    MainApplication.sharedPreferencesUser.put(
//                        Variables.TOKEN_TYPE,
//                        userWrapper.token_type
//                    )
//                    MainApplication.sharedPreferencesUser.put(
//                        Variables.USER_ID,
//                        userWrapper.user!!.id
//                    )
                    users.add(userWrapper.user!!)
                    LoginList!!.setValue(users)
                } else {
                    Log.e("Invalid", response.message())
                }


            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("error", call.toString())
            }
        })
    }
}