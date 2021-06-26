package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.UpdateProfile.UpdateUserProfile
import org.json.JSONObject

class UpdateProfileViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun Updateprofile(
        mToken: String?,
        mEmail: String,
        nFname: String,
        mLname: String
    ): LiveData<UpdateUserProfile> {
        var mutableLiveData: MutableLiveData<UpdateUserProfile> = MutableLiveData()
        when {

            else -> {
                val requestObject = JSONObject()
                requestObject.put("email", mEmail)
                requestObject.put("fname", nFname)
                requestObject.put("lname", mLname)
                return APIRepository.updateProfile(mToken,requestObject)
            }
        }
        return mutableLiveData
    }
}