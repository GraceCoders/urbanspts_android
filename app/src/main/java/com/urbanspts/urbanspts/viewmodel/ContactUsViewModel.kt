package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.ContactUsModel.ContactUsResponse
import org.json.JSONObject

class ContactUsViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun contactUs(
        mToken: String?,
        nName: String,
        mEmail: String,
        mMessage: String
    ): LiveData<ContactUsResponse> {
        var mutableLiveData: MutableLiveData<ContactUsResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("email", mEmail)
                requestObject.put("name", nName)
                requestObject.put("message", mMessage)
                return APIRepository.ContactUs(mToken,requestObject)
            }
        }
        return mutableLiveData
    }
}