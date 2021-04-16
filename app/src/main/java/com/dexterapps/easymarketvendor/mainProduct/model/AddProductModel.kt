package com.dexterapps.easymarketvendor.mainProduct.model

import com.google.gson.annotations.SerializedName

class AddProductModel (

    @SerializedName("message") val message : String,
    @SerializedName("success") val success : String
)