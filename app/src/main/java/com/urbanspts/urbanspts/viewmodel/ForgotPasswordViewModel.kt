package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.ForgetPassword.ForgetPasswordResponse
import org.json.JSONObject

class ForgotPasswordViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun forgetPassword(
        mToken: String?,
        mEmail: String,
        mNewPwd: String
    ): LiveData<ForgetPasswordResponse> {
        var mutableLiveData: MutableLiveData<ForgetPasswordResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("email", mEmail)
                requestObject.put("newPassword", mNewPwd)
                return APIRepository.forgetPwd(mToken,requestObject)
            }
        }
        return mutableLiveData
    }
}