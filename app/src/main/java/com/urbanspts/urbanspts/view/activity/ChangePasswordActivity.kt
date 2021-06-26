package com.urbanspts.urbanspts.view.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.controller.utills.Validations
import com.urbanspts.urbanspts.model.ChangePasswordModel.ChangePasswordResponse
import com.urbanspts.urbanspts.model.ContactUsModel.ContactUsResponse
import com.urbanspts.urbanspts.viewmodel.ChangePasswordViewModel
import com.urbanspts.urbanspts.viewmodel.ContactUsViewModel
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_change_password.iv_back
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_login.*

class ChangePasswordActivity : AppCompatActivity(),View.OnClickListener {
    var mUser_id:String?=null
    var mToken:String?=null
    var statusCode:Int?=null
    private  var viewModel: ChangePasswordViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        initView()
    }
    private fun initView() {
        mUser_id = AppPreferences.init(this).getString(Constants.USER_ID)
        mToken = AppPreferences.init(this).getString(Constants.TOKEN)
        viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)
        ll_change.setOnClickListener(this)
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.ll_change ->
            {
                if(Validations.isValidatepwd(this,et_current_pwd,et_new_pwd,et_cfm_new_pwd))
                {
                    doChangePassword()
                }

            }
            R.id.iv_back ->
            {
                onBackPressed()

            }
        }
    }


    //............................................change pwd....................................................


    private fun doChangePassword() {
        cl_changepwd_Loader.visibility = View.VISIBLE
        viewModel?.changePassword(mToken,mUser_id.toString(),et_current_pwd.text.toString().trim(),et_new_pwd.text.toString(),et_cfm_new_pwd.text.toString())?.observe(
            this,
            object : Observer<ChangePasswordResponse> {
                override fun onChanged(@Nullable changePasswordResponse: ChangePasswordResponse?) {
                    cl_changepwd_Loader.visibility = View.GONE
                    statusCode=changePasswordResponse?.statusCode

                    if(statusCode==200)
                    {
                        et_current_pwd.text?.clear()
                        et_new_pwd.text?.clear()
                        et_cfm_new_pwd.text?.clear()
                        showSnackBar("Password changed successfully")

                    }
                    else
                    {
                        showSnackBar(changePasswordResponse?.message)
                    }

                }
            })

    }

    private fun showSnackBar(message: String?) {
        SnackbarUtil.showWarningShortSnackbar(this,message)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
    //................................hide keyboard on touch.................................................
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus
        val ret = super.dispatchTouchEvent(ev)
        if (view is EditText) {
            val w = currentFocus
            val scrcoords = IntArray(2)
            w!!.getLocationOnScreen(scrcoords)
            val x = ev.rawX + w.left - scrcoords[0]
            val y = ev.rawY + w.top - scrcoords[1]
            if (ev.action == MotionEvent.ACTION_UP
                && (x < w.left || x >= w.right || y < w.top || y > w.bottom)
            ) {
                hideSoftKeyboard(this)
            }
        }
        return ret
    }
}
