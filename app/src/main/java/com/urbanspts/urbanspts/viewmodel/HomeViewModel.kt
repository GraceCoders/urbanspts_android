package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.CategoryDetailModel.CategoryDetailResponse
import com.urbanspts.urbanspts.model.HomeModel.HomeResponse
import com.urbanspts.urbanspts.model.RequestModel.HomeRequest.HomeRequestModel

class HomeViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun gethomeDetails(
        currentLat: Double?,
        currentLong: Double?
    ): LiveData<HomeResponse> {
        var mutableLiveData: MutableLiveData<HomeResponse> = MutableLiveData()
        when {
            else -> {
                var requestObject = HomeRequestModel()
                requestObject.startPoint.lat= currentLat.toString()
                requestObject.startPoint.lng= currentLong.toString()
                return APIRepository.getHomeDetails(requestObject)
            }
        }
        return mutableLiveData
    }


}