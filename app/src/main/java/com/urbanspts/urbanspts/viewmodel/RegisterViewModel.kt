package com.urbanspts.urbanspts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.services.APIRepository
import com.urbanspts.urbanspts.model.registerModel.RegisterResponse

class RegisterViewModel : AndroidViewModel {
    private lateinit var context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }
    fun signup(
        username: String,
        password: String,
        fname: String,
        lname: String
    ): LiveData<RegisterResponse> {
        var mutableLiveData: MutableLiveData<RegisterResponse> = MutableLiveData()
        when {
            else -> {
                return APIRepository.register(username,password,fname,lname)
            }
        }
        return mutableLiveData
    }
}