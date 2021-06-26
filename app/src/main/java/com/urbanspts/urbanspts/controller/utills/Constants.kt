package com.urbanspts.urbanspts.controller.utills

/**
 * Created by Md Efteaz on 03/07/20.
 */
object Constants {
    const val BASE_URL_API = "http://195.110.58.156:5004/api/v1/"

    //api endpoint
    const val API_LOGIN = "user/login"
    const val API_SIGNUP = "user/register"
    const val API_CATEGORY = "admin/categories"
    const val API_LOCATIONS = "providers/locations"
    const val API_CATEGORY_DETAILS = "providers/catsubcatwithprovider"
    const val API_SUBCATEGORY_DETAILS = "providers/subcatwithprovider"
    const val API_LOCATIONS_DETAILS = "providers/bylocation"
    const val API_HOME_DETAILS = "home/providers"
    const val API_PROVIDER_DETAILS = "providers/detail"
    const val API_CONTACT_US = "contactus/add"
    const val API_WISHLIST = "wishlist/list"
    const val API_ADD_WISHLIST = "wishlist/add"
    const val API_REMOVE_WISHLIST = "wishlist/remove"
    const val API_CHANGE_PWD = "user/changepassword"
    const val API_UPDATE_PROFILE = "user/updateprofile"
    const val API_IMAGE_UPLOAD = "user/updateProfileImage"
    const val API_SEND_OTP = "user/sendotp"
    const val API_VARIFY_OTP = "user/verifyotp"
    const val API_FORGET_PWD = "user/forgotpassword"

    //preferences
    const val USERNAME = "username"
    const val F_NAME = "first_name"
    const val L_NAME = "lastname"
    const val EMAIL = "useremail"
    const val USER_ID = "user_id"
    const val PROFILE_IMAGE = "profile_image"
    const val TOKEN = "token"

    const val PASSWORD = "password"
}