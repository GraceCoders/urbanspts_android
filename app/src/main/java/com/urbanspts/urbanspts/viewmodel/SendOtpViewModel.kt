package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.SendOtp.SendOtpResponse
import com.urbanspts.urbanspts.model.loginModel.LoginResponse
import org.json.JSONObject

class SendOtpViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun sendOtp(email: String): LiveData<SendOtpResponse> {
        var mutableLiveData: MutableLiveData<SendOtpResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("email", email)
                return APIRepository.sendOtp(requestObject)
            }
        }
        return mutableLiveData
    }
}