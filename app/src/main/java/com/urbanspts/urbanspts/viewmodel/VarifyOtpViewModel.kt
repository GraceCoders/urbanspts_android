package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.VarifyOtp.VarifyOtpResponse
import org.json.JSONObject

class VarifyOtpViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun varifyOtp(mEmail: String?, otpCode: String?): LiveData<VarifyOtpResponse> {
        var mutableLiveData: MutableLiveData<VarifyOtpResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("email", mEmail)
                requestObject.put("OTP", otpCode)
                return APIRepository.vaifyOtp(requestObject)
            }
        }
        return mutableLiveData
    }
}