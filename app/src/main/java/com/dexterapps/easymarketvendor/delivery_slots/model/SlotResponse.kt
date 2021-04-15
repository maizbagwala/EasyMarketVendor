package com.dexterapps.easymarketvendor.delivery_slots.model

data class SlotResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)