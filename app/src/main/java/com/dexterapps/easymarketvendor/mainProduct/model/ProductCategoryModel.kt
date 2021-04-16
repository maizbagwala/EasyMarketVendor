package com.dexterapps.easymarketvendor.mainProduct.model

import com.google.gson.annotations.SerializedName

 class productCategoryModel (

    @SerializedName("data") val data : List<Data>,
    @SerializedName("message") val message : String,
    @SerializedName("success") val success : Boolean
)