package com.dexterapps.easymarketvendor.home.model

data class OrderResponse(
    val `data`: List<DataX>,
    val message: String,
    val success: Boolean
)