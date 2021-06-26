package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.ProviderDetailModel.ProviderDetailResponse
import org.json.JSONObject

class ProviderDetailViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun getProviderDetails(userid: String, providerId: String): LiveData<ProviderDetailResponse> {
        var mutableLiveData: MutableLiveData<ProviderDetailResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("userId", userid)
                requestObject.put("providerId", providerId)
                return APIRepository.getProviderDetails(requestObject)
            }
        }
        return mutableLiveData
    }


}