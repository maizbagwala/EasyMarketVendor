package com.dexterapps.easymarketvendor.home.model

data class Data(
    val cancelled_orders: Int,
    val pending_orders: Int,
    val revenue_cash: Int,
    val revenue_online: String,
    val todays_pending_orders: Int,
    val total_orders: Int,
    val total_visitor: Int
)