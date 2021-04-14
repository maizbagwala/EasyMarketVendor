package com.dexterapps.easymarketvendor.home.model

data class Product(
    val baseprice: Int,
    val baseqty: Int,
    val baseunit: String,
    val baseunitprice: Int,
    val discount: Int,
    val discount_type: String,
    val discountedprice: Int,
    val name: String,
    val product_id: Int,
    val thumbnail_image: String
)