package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.ChangePasswordModel.ChangePasswordResponse
import org.json.JSONObject

class ChangePasswordViewModel :AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun changePassword(
        mToken: String?,
        mUserId: String,
        nCurrentPwd: String,
        mNewPwd: String,
        mConfirmPwd: String
    ): LiveData<ChangePasswordResponse> {
        var mutableLiveData: MutableLiveData<ChangePasswordResponse> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("userId", mUserId)
                requestObject.put("currentPassword", nCurrentPwd)
                requestObject.put("newPassword", mNewPwd)
                requestObject.put("confirmPassword", mConfirmPwd)
                return APIRepository.changePwd(mToken,requestObject)
            }
        }
        return mutableLiveData
    }
}