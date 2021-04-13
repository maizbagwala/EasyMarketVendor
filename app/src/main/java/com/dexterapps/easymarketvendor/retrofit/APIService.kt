package com.dexterapps.easymarketvendor.retrofit

import com.dexterapps.easymarketvendor.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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


    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("phone") action: String): Call<LoginResponse>
}