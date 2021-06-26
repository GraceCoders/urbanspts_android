package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.CategoryDetailModel.CategoryDetailResponse
import com.urbanspts.urbanspts.model.RequestModel.categoryRequest.CategoryDetailRequest

class CategoryDetailViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun getCategoryDetails(
        categoryId: String,
        currentLat: Double?,
        currentLong: Double?
    ): LiveData<CategoryDetailResponse> {
        var mutableLiveData: MutableLiveData<CategoryDetailResponse> = MutableLiveData()
        when {
            else -> {
                var requestObject =CategoryDetailRequest()
                requestObject.categoryId=categoryId
                requestObject.startPoint.lat= currentLat.toString()
                requestObject.startPoint.lng= currentLong.toString()
                return APIRepository.getCategoryDetails(requestObject)
            }
        }
        return mutableLiveData
    }
}