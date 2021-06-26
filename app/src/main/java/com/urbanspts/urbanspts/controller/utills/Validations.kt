package com.urbanspts.urbanspts.controller.utills

import android.app.Activity
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import com.urbanspts.urbanspts.R

object Validations {

    /**
     * @param editText - EditText field which need to be validated
     * @return - Returns true if editText is Null or empty
     */
    fun isNullOrEmpty(editText: EditText): Boolean {
        return (editText.text == null
                || editText.text.toString().trim { it <= ' ' }.length == 0)
    }
    private fun validateEmailAddress(
        applicationContext: Activity,
        view: View,
        errMessage: String
    ): Boolean {
        val email = (view as EditText).text.toString().trim { it <= ' ' }
        if (email.contains("[a-zA-Z0-9._-]+") || email.contains("@")) {
            if (email.isEmpty() || !isValidEmail(email)) {



                SnackbarUtil.showWarningShortSnackbar(applicationContext, errMessage)
                // requestFocus(applicationContext, ((EditText) view));
                return true
            }
        } else {
            SnackbarUtil.showWarningShortSnackbar(applicationContext, errMessage)
            // requestFocus(applicationContext, ((EditText) view));
            return true
        }
        return false
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateUsername(
        applicationContext: Activity,
        mEtUsername: EditText,
        errMessage: String
    ): Boolean {
        if (isNullOrEmpty(mEtUsername)) {
            SnackbarUtil.showWarningShortSnackbar(applicationContext, errMessage)
            mEtUsername.isFocusable = true
            mEtUsername.requestFocus()
            return true
        }
        return false
    }



    fun isValidateLogin(applicationContext: Activity, mEtUsername: EditText?, mEtPassword: EditText?): Boolean {
        if (validateEmailAddress(applicationContext, mEtUsername!!, applicationContext.resources.getString(R.string.error_empty_user_id))) {
            return false
        }
        if (validateUsername(applicationContext, mEtPassword!!, applicationContext.resources.getString(R.string.error_empty_password))) {
            return false
        }
        return true
    }

    fun isContactUs(applicationContext: Activity, mEtName: EditText?, mEtEmail: EditText?, mEtmessage: EditText?): Boolean {
        if (validateEmailAddress(applicationContext, mEtEmail!!, applicationContext.resources.getString(R.string.error_empty_user_id))) {
            return false
        }
        if (validateUsername(applicationContext, mEtName!!, applicationContext.resources.getString(R.string.error_empty_first_name))) {
            return false
        }
        if (validateUsername(applicationContext, mEtmessage!!, applicationContext.resources.getString(R.string.error_empty_msg))) {
            return false
        }
        return true
    }

    fun isValidatepwd(applicationContext: Activity, mEtcureentPwd: EditText?, mEtNewPassword: EditText?, mEtCfmPwd: EditText?): Boolean {
        if (validateUsername(applicationContext, mEtcureentPwd!!, applicationContext.resources.getString(R.string.error_empty_current_pwd))) {
            return false
        }
        if (validateUsername(applicationContext, mEtNewPassword!!, applicationContext.resources.getString(R.string.error_empty_new_pwd))) {
            return false
        }
        if (validateUsername(applicationContext, mEtCfmPwd!!, applicationContext.resources.getString(R.string.error_empty_cfm_pwd))) {
            return false
        }
        return true
    }


    fun isValidateSignup(applicationContext: Activity,mEtUsername: EditText?,mEtLastName: EditText?,  mEtEmail: EditText?, mEtPassword: EditText?): Boolean {
        if (validateUsername(applicationContext, mEtUsername!!, applicationContext.resources.getString(R.string.error_empty_first_name))) {
            return false
        }
        if (validateUsername(applicationContext, mEtLastName!!, applicationContext.resources.getString(R.string.error_empty_last_name))) {
            return false
        }
        if (validateEmailAddress(applicationContext, mEtEmail!!, applicationContext.resources.getString(R.string.error_empty_user_id))) {
            return false
        }
        if (validateUsername(applicationContext, mEtPassword!!, applicationContext.resources.getString(R.string.error_empty_password))) {
            return false
        }
        return true
    }


    fun isValidateUpdateProfile(applicationContext: Activity,mEtUsername: EditText?,mEtLastName: EditText?,  mEtEmail: EditText?): Boolean {
        if (validateUsername(applicationContext, mEtUsername!!, applicationContext.resources.getString(R.string.error_empty_first_name))) {
            return false
        }
        if (validateUsername(applicationContext, mEtLastName!!, applicationContext.resources.getString(R.string.error_empty_last_name))) {
            return false
        }
        if (validateEmailAddress(applicationContext, mEtEmail!!, applicationContext.resources.getString(R.string.error_empty_user_id))) {
            return false
        }
        return true
    }
}