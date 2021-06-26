package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.LocationModel.LocationResponse
import com.urbanspts.urbanspts.model.categoriesModel.Category
import org.json.JSONObject

class LocationViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun getLocations(): LiveData<List<LocationResponse>> {
        var mutableLiveData: MutableLiveData<List<LocationResponse>> = MutableLiveData()
        return APIRepository.getLocations()
        return mutableLiveData
    }
}