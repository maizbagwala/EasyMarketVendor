package com.dexterapps.easymarketvendor.retrofit

import com.dexterapps.easymarketvendor.delivery_slots.model.SlotResponse
import com.dexterapps.easymarketvendor.home.model.DashboardResponse
import com.dexterapps.easymarketvendor.home.model.OrderResponse
import com.dexterapps.easymarketvendor.login.model.LoginResponse
import com.dexterapps.easymarketvendor.mainProduct.model.AddProductModel
import com.dexterapps.easymarketvendor.mainProduct.model.productCategoryModel
import com.dexterapps.easymarketvendor.myDeliveryPerson.model.DeliveryPersonResponse
import com.dexterapps.easymarketvendor.offerCreation.model.OfferCreationModel
import com.dexterapps.easymarketvendor.offerCreation.model.offerResponse
import com.dexterapps.easymarketvendor.register.model.businessCategoryModel
import com.dexterapps.easymarketvendor.register.model.registerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {
//    @GET
//    fun getHeroes(@Url fullUrl: String?):  Call<GetAddressResponse>

    //    @GET("banners")
//    fun getBanners(): Call<GetBannerData>
//
//
//    @GET("home")
//    fun getFeatureStore(): Call<FeatureStoreRoot>
//
//    @Headers("Content-Type: application/json")
//    @GET("user/shipping/address")
//    fun getAddress(
//        @Query("id") para1: String,
//        @Header("Authorization") authHeader: String?
//    ): Call<GetAddressResponse>
//
//    @Headers("Content-Type: application/json")
//    @GET("shops/details")
//    fun getStoreList(
//        @Query("id") para1: String,
//        @Header("Authorization") authHeader: String?
//    ): Call<StoreRoot>
//
//    @FormUrlEncoded
//    @POST("user/shipping/create")
//    fun AddAddress(
//        @Field("user_id") user_id: String,
//        @Field("address") address: String,
//        @Field("a_type") a_type: String,
//        @Field("name") name: String,
//        @Field("locality") locality: String,
//        @Header("Authorization") authHeader: String?
//    ): Call<AddAddressResponse>
//
//    @FormUrlEncoded
//    @POST("user/shipping/update")
//    fun UpdateAddress(
//        @Field("id") id: String,
//        @Field("address") address: String,
//        @Field("a_type") a_type: String,
//        @Field("name") name: String,
//        @Field("locality") locality: String,
//        @Header("Authorization") authHeader: String?
//    ): Call<AddAddressResponse>
//
//    @Headers("Content-Type: application/json")
//    @GET("user/shipping/delete")
//    fun DeleteAddress(
//        @Query("id") para1: String,
//        @Header("Authorization") authHeader: String?
//    ): Call<AddAddressResponse>
    @Multipart
    @POST("vendor/adddriver")
    fun addDeliveryPerson(
        @Part("store_id") id: RequestBody,
        @Part("d_name") d_name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("mobile_no") mobile_no: RequestBody,
        @Part("password") password: RequestBody,
        @Part("lic_no") lic_no: RequestBody,
        @Part("address") address: RequestBody,
        @Part lic_img: MultipartBody.Part,
        @Part profile_img: MultipartBody.Part,
    ): Call<DeliveryPersonResponse>

    @GET("vendor/driverlist")
    fun getDeliveryPerson(@Query("id") id: Int): Call<DeliveryPersonResponse>


    @GET("vendor/order/pendinglist")
    fun getPendingOrder(
        @Query("user_id") id: Int
    ): Call<OrderResponse>

    @GET("vendor/order/acceptedlist")
    fun getAcceptedOrder(
        @Query("user_id") id: Int
    ): Call<OrderResponse>


    @GET("vendor/order/list")
    fun getOrder(
        @Query("user_id") id: Int
    ): Call<OrderResponse>


    @FormUrlEncoded
    @POST("vendor/coupon/create")
    fun createOffer(
        @Field("user_id") userId: String,
        @Field("coupon_code") couponsCode: String,
        @Field("discount") discount: String,
        @Field("discount_type") discountType: String,
        @Field("start_date") startDate: String,
        @Field("end_date") endDate: String,
        @Field("coupon_name") couponName: String,
        @Field("min_buy") minBuy: String,
        @Field("max_discount") maxDiscount: String
    ): Call<OfferCreationModel>

    @Multipart
    @POST("vendor/addproduct")
    fun addProduct(
        @Part("name") name: RequestBody,
        @Part("user_id") userId: RequestBody,
        @Part("category_id") categoryId: RequestBody,
        @Part photos: List<MultipartBody.Part>,
        @Part thumbnailImg: MultipartBody.Part,
        @Part("unit") unit: RequestBody,
        @Part("min_qty") minQty: RequestBody,
        @Part("baseunit") baseUnit: RequestBody,
        @Part("description") description: RequestBody,
        @Part("unit_price") unitPrice: RequestBody,
        @Part("purchase_price") purchasePrice: RequestBody,
        @Part("discount") discount: RequestBody,
        @Part("discount_type") discountType: RequestBody,
        @Part("baseunitprice") baseUnitPrice: RequestBody,
    ): Call<AddProductModel>

    @GET("vendor/productcategory")
    fun getProductCategory(): Call<productCategoryModel>

    @FormUrlEncoded
    @POST("vendor/coupon/update")
    fun updateOffer(
        @Field("user_id") userId: String,
        @Field("coupon_code") couponsCode: String,
        @Field("discount") discount: String,
        @Field("discount_type") discountType: String,
        @Field("start_date") startDate: String,
        @Field("end_date") endDate: String,
        @Field("coupon_name") couponName: String,
        @Field("min_buy") minBuy: String,
        @Field("max_discount") maxDiscount: String,
        @Field("cid") cid: String
    ): Call<offerResponse>

    @GET("vendor/coupon/lists")
    fun getOffer(@Query("user_id") id: Int): Call<offerResponse>


    @GET("vendor/coupon/remove")
    fun deleteOffer(@Query("id") id: Int): Call<offerResponse>


    @GET("vendor/shopcategory")
    fun getRegCategory(): Call<businessCategoryModel>

    @GET("vendor/dashboard")
    fun getDatabase(@Query("id") id: Int): Call<DashboardResponse>

    @GET("vendor/timeslot/list")
    fun getSlot(@Query("user_id") id: Int): Call<SlotResponse>

    @GET("vendor/timeslot/remove")
    fun deleteSlot(@Query("tid") id: Int): Call<SlotResponse>

    @FormUrlEncoded
    @POST("vendor/timeslot/create")
    fun createSlot(
        @Field("user_id") id: Int,
        @Field("t_date") t_date: String,
        @Field("t_day") t_day: String,
        @Field("t_from") t_from: String,
        @Field("t_to") t_to: String
    ): Call<SlotResponse>

    @FormUrlEncoded
    @POST("vendor/timeslot/update")
    fun updateSlot(
        @Field("user_id") id: Int,
        @Field("tid") tid: Int,
        @Field("t_date") t_date: String,
        @Field("t_day") t_day: String,
        @Field("t_from") t_from: String,
        @Field("t_to") t_to: String
    ): Call<SlotResponse>

    @FormUrlEncoded
    @POST("vendor/register")
    fun register(
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("mobileno") mobileno: String,
        @Field("email") email: String,
        @Field("address") address: String,
        @Field("category") category: String,
        @Field("shop_name") shop_name: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String
    ): Call<registerResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("phone") action: String): Call<LoginResponse>
}