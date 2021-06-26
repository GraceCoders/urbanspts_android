package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.LocationProviderModel.LocationProvoderResponse
import com.urbanspts.urbanspts.model.RequestModel.ProviderRequest.LocationProviderRequest

class LocationProviderModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun getLocationProviderDetails(
        categoryId: String,
        currentLat: Double?,
        currentLong: Double?
    ): LiveData<LocationProvoderResponse> {
        var mutableLiveData: MutableLiveData<LocationProvoderResponse> = MutableLiveData()
        when {
            else -> {
                var requestObject = LocationProviderRequest()
                requestObject.stateId=categoryId
                requestObject.startPoint.lat= currentLat.toString()
                requestObject.startPoint.lng= currentLong.toString()
                return APIRepository.getLocationDetails(requestObject)
            }
        }
        return mutableLiveData
    }
}