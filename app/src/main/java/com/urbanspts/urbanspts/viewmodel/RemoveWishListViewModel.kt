package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.AddWishlist.AddWishlistResponse
import com.urbanspts.urbanspts.model.RemoveWishlist.RemoveWishlist
import com.urbanspts.urbanspts.model.loginModel.LoginResponse
import org.json.JSONObject

class RemoveWishListViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun RemoveWishlist(mToken: String?,username: String, password: String): LiveData<RemoveWishlist> {
        var mutableLiveData: MutableLiveData<RemoveWishlist> = MutableLiveData()
        when {
            username.isEmpty() -> {
          //      mutableLiveData.value =
                  //  APIResponse<String>().error(Exception(context.getString(R.string.error_empty_user_id)))
            }
            password.isEmpty() -> {
//                mutableLiveData.value =
//                    APIResponse<String>().error(Exception(context.getString(R.string.error_empty_password)))
            }
            else -> {
                val requestObject = JSONObject()
                requestObject.put("userId", username)
                requestObject.put("providerId", password)
                return APIRepository.removeWishlist(mToken,requestObject)
            }
        }
        return mutableLiveData
    }
}