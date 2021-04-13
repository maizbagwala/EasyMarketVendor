package com.dexterapps.easymarketvendor.offerCreation.model

data class Data(
    val cart_limit: String,
    val code: String,
    val coupon_name: String,
    val created_at: String,
    val details: String,
    val discount: Int,
    val discount_type: String,
    val end_date: String,
    val id: Int,
    val start_date: String,
    val store_id: Int,
    val updated_at: String
)