package com.dexterapps.easymarketvendor.home.model

import java.io.Serializable

data class DataX(
    val code: String,
    val coupon_discount: Int,
    val datetime: String,
    val delivery: Int,
    val order_id: Int,
    val product_discount: Int,
    val products: List<Product>,
    val shipping_address: ShippingAddress,
    val subtotal: Int,
    val tax: Int,
    val total: Int
) : Serializable