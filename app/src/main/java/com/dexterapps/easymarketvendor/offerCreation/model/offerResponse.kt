package com.dexterapps.easymarketvendor.offerCreation.model

data class offerResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)