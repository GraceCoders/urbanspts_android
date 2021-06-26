package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.UpdateImageModel.UpdateImageResponse
import java.io.File

class UpdateImageViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun updateImage(
        token: String,
        mImagefile: File?
    ): LiveData<UpdateImageResponse> {
        var mutableLiveData: MutableLiveData<UpdateImageResponse> = MutableLiveData()
        when {
            else -> {
                return APIRepository.UploadImage(token,mImagefile)
            }
        }
        return mutableLiveData
    }
}