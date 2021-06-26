package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.WishlistModel.WishlistResponse
import org.json.JSONObject

class WishlistViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun getWishlist(
        mToken: String?,
        mUserId: String
    ): LiveData<WishlistResponse> {
        var mutableLiveData: MutableLiveData<WishlistResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("userId", mUserId)
                return APIRepository.getWishlist(mToken,requestObject)
            }
        }
        return mutableLiveData
    }
}