package com.dexterapps.easymarketvendor.myDeliveryPerson.model

data class DeliveryPersonResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)