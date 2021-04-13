package com.dexterapps.easymarketvendor.delivery_slots.interfaces


interface SlotInterface {
    fun deleteSlot(id: Int)
    fun updateSlot(
        tId: Int,
        t_date: String,
        t_day: String,
        t_from: String,
        t_to: String
    )
}