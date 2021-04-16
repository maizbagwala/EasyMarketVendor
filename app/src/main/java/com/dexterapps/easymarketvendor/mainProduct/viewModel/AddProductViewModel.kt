package com.dexterapps.easymarketvendor.mainProduct.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.mainProduct.model.AddProductModel
import com.dexterapps.easymarketvendor.offerCreation.model.OfferCreationModel
import com.dexterapps.easymarketvendor.retrofit.RetrofitClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductViewModel : ViewModel() {
    private var liveData: MutableLiveData<AddProductModel>? = null
    fun addProduct(
        name: RequestBody,
        userId: RequestBody,
        categoryId: RequestBody,
        photos:List<MultipartBody.Part>,
        thumbnailImg: MultipartBody.Part,
        unit: RequestBody,
        minQty: RequestBody,
        baseUnit: RequestBody,
        description: RequestBody,
        unitPrice: RequestBody,
        purchasePrice: RequestBody,
        discount: RequestBody,
        discountType: RequestBody,
        baseUnitPrice: RequestBody
    ): MutableLiveData<AddProductModel>? {

        if (liveData == null) {
            liveData = MutableLiveData<AddProductModel>()
            callApi(
                name,
                userId,
                categoryId,
                photos,
                thumbnailImg,
                unit,
                minQty,
                baseUnit,
                description,
                unitPrice,
                purchasePrice,
                discount,
                discountType,
                baseUnitPrice
            )
        }
        return liveData
    }

    private fun callApi(
        name: RequestBody,
        userId: RequestBody,
        categoryId: RequestBody,
        photos:List<MultipartBody.Part>,
        thumbnailImg: MultipartBody.Part,
        unit: RequestBody,
        minQty: RequestBody,
        baseUnit: RequestBody,
        description: RequestBody,
        unitPrice: RequestBody,
        purchasePrice: RequestBody,
        discount: RequestBody,
        discountType: RequestBody,
        baseUnitPrice: RequestBody
    ) {
        val call = RetrofitClient.getClient().addProduct(
            name,
            userId,
            categoryId,
            photos,
            thumbnailImg,
            unit,
            minQty,
            baseUnit,
            description,
            unitPrice,
            purchasePrice,
            discount,
            discountType,
            baseUnitPrice
        )

        call.enqueue(object : Callback<AddProductModel> {
            override fun onResponse(
                call: Call<AddProductModel>,
                response: Response<AddProductModel>
            ) {
                Log.d(TAG, "onResponse: " + response.body())

                liveData!!.value = response.body()

            }

            override fun onFailure(call: Call<AddProductModel>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }
}