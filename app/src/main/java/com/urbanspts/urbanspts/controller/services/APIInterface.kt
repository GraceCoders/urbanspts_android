package com.urbanspts.urbanspts.controller.repository

import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.model.AddWishlist.AddWishlistResponse
import com.urbanspts.urbanspts.model.CategoryDetailModel.CategoryDetailResponse
import com.urbanspts.urbanspts.model.ChangePasswordModel.ChangePasswordResponse
import com.urbanspts.urbanspts.model.ContactUsModel.ContactUsResponse
import com.urbanspts.urbanspts.model.ForgetPassword.ForgetPasswordResponse
import com.urbanspts.urbanspts.model.HomeModel.HomeResponse
import com.urbanspts.urbanspts.model.LocationModel.LocationResponse
import com.urbanspts.urbanspts.model.LocationProviderModel.LocationProvoderResponse
import com.urbanspts.urbanspts.model.ProviderDetailModel.ProviderDetailResponse
import com.urbanspts.urbanspts.model.RemoveWishlist.RemoveWishlist
import com.urbanspts.urbanspts.model.RequestModel.HomeRequest.HomeRequestModel
import com.urbanspts.urbanspts.model.RequestModel.ProviderRequest.LocationProviderRequest
import com.urbanspts.urbanspts.model.RequestModel.SubCategoryRequest.SubCategoryRequest
import com.urbanspts.urbanspts.model.RequestModel.categoryRequest.CategoryDetailRequest
import com.urbanspts.urbanspts.model.SendOtp.SendOtpResponse
import com.urbanspts.urbanspts.model.SubCategoryModel.SubCategoryDetailResponse
import com.urbanspts.urbanspts.model.UpdateImageModel.UpdateImageResponse
import com.urbanspts.urbanspts.model.UpdateProfile.UpdateUserProfile
import com.urbanspts.urbanspts.model.VarifyOtp.VarifyOtpResponse
import com.urbanspts.urbanspts.model.WishlistModel.WishlistResponse
import com.urbanspts.urbanspts.model.categoriesModel.CategoryResponse
import com.urbanspts.urbanspts.model.loginModel.LoginResponse
import com.urbanspts.urbanspts.model.registerModel.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by nishant on 03/07/20.
 */
interface APIInterface {
    //getcategory
    @Headers("Content-Type: application/json")
    @POST(Constants.API_CATEGORY)
    fun categories(): Call<CategoryResponse>


    //getLocations
    @Headers("Content-Type: application/json")
    @POST(Constants.API_LOCATIONS)
    fun getLocations(): Call<LocationResponse>

// get categorydetails
     @Headers("Content-Type: application/json")
    @POST(Constants.API_CATEGORY_DETAILS)
    fun getCategoryDetails(
        @Body requestBody: CategoryDetailRequest
    ): Call<CategoryDetailResponse>

    // get homeresponse
    @Headers("Content-Type: application/json")
    @POST(Constants.API_HOME_DETAILS)
    fun gethomeDetails(
        @Body requestBody: HomeRequestModel
    ): Call<HomeResponse>


    // get location details
    @Headers("Content-Type: application/json")
    @POST(Constants.API_LOCATIONS_DETAILS)
    fun getLocationsDetails(
        @Body requestBody: LocationProviderRequest
    ): Call<LocationProvoderResponse>

    // get categorydetails
    @Headers("Content-Type: application/json")
    @POST(Constants.API_SUBCATEGORY_DETAILS)
    fun getSubCategoryDetails(
        @Body requestBody: SubCategoryRequest
    ): Call<SubCategoryDetailResponse>

    //login
    @POST(Constants.API_PROVIDER_DETAILS)
    fun getProviderDetails(
        @Body requestBody: RequestBody
    ): Call<ProviderDetailResponse>

    //login
    @POST(Constants.API_LOGIN)
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>

    //sendOtp
    @POST(Constants.API_SEND_OTP)
    fun sendOtp(
        @Body requestBody: RequestBody
    ): Call<SendOtpResponse>

    //varifyOtp
    @POST(Constants.API_VARIFY_OTP)
    fun varifyOtp(
        @Body requestBody: RequestBody
    ): Call<VarifyOtpResponse>

    //contact us
    @Headers("Content-Type: application/json")
    @POST(Constants.API_CONTACT_US)
    fun contactUs(
        @Header("token") authorization: String?,
        @Header("role") role: String?,
        @Body requestBody: RequestBody
    ): Call<ContactUsResponse>

    @Multipart
    @POST(Constants.API_IMAGE_UPLOAD)
    fun UpdateImage(
        @Header("token") authorization: String?,
        @Part profile_image:MultipartBody.Part?

    ): Call<UpdateImageResponse?>?


    //changepwd
    @Headers("Content-Type: application/json")
    @POST(Constants.API_CHANGE_PWD)
    fun changePwd(
        @Header("token") authorization: String?,
        @Body requestBody: RequestBody
    ): Call<ChangePasswordResponse>


    @POST(Constants.API_FORGET_PWD)
    fun forgetPwd(
        @Header("token") authorization: String?,
        @Body requestBody: RequestBody
    ): Call<ForgetPasswordResponse>


    //updateProfile
    @Headers("Content-Type: application/json")
    @POST(Constants.API_UPDATE_PROFILE)
    fun UpdateProfile(
        @Header("token") authorization: String?,
        @Body requestBody: RequestBody
    ): Call<UpdateUserProfile>

    //contact us
    @Headers("Content-Type: application/json")
    @POST(Constants.API_WISHLIST)
    fun getwishlist(
        @Header("token") authorization: String?,
        @Body requestBody: RequestBody
    ): Call<WishlistResponse>


    //addwishlist
    @Headers("Content-Type: application/json")
    @POST(Constants.API_ADD_WISHLIST)
    fun addWishlist(
        @Header("token") authorization: String?,
        @Body requestBody: RequestBody
    ): Call<AddWishlistResponse>


    //removeWishlist
    @Headers("Content-Type: application/json")
    @POST(Constants.API_REMOVE_WISHLIST)
    fun removeWishlist(
        @Header("token") authorization: String?,
        @Body requestBody: RequestBody
    ): Call<RemoveWishlist>


    //signup

    @Multipart
    @POST(Constants.API_SIGNUP)
    fun signup(
        @Part("lname") lname: RequestBody?,
        @Part("email") email: RequestBody?,
        @Part("password") password: RequestBody?,
        @Part("fname") fname: RequestBody?,
        @Part  cover_image: MultipartBody.Part?

    ): Call<RegisterResponse?>?

}