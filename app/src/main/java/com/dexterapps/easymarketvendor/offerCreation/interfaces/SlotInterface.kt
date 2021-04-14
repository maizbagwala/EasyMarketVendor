package com.dexterapps.easymarketvendor.offerCreation.interfaces

import com.dexterapps.easymarketvendor.offerCreation.model.Data


interface OfferInterface {
    fun deleteOffer(id: Int)
    fun updateOffer(model: Data)

}