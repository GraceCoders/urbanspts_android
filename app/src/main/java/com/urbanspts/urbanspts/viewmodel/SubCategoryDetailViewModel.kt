package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.RequestModel.SubCategoryRequest.SubCategoryRequest
import com.urbanspts.urbanspts.model.SubCategoryModel.SubCategoryDetailResponse

class SubCategoryDetailViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun getSubCategoryDetails(
        categoryId: String,
        currentLat: Double?,
        currentLong: Double?
    ): LiveData<SubCategoryDetailResponse> {
        var mutableLiveData: MutableLiveData<SubCategoryDetailResponse> = MutableLiveData()
        when {
            else -> {
                var requestObject = SubCategoryRequest()
                requestObject.subcategoryId=categoryId
                requestObject.startPoint.lat= currentLat.toString()
                requestObject.startPoint.lng= currentLong.toString()
                return APIRepository.getSubCategoryDetails(requestObject)
            }
        }
        return mutableLiveData
    }
}