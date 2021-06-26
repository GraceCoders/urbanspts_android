package com.urbanspts.urbanspts.view.activity

import android.app.Activity
import android.content.Intent
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
import com.urbanspts.urbanspts.model.ForgetPassword.ForgetPasswordResponse
import com.urbanspts.urbanspts.viewmodel.ForgotPasswordViewModel
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.et_new_pwd
import kotlinx.android.synthetic.main.activity_forgot_password.iv_back

class   ForgotPassword : AppCompatActivity(),View.OnClickListener {
    var mUser_id:String?=null
    var mToken:String?=null
    var statusCode:Int?=null
    private  var viewModel: ForgotPasswordViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        initView()
    }

    private fun initView() {
        mUser_id = AppPreferences.init(this).getString(Constants.EMAIL)
        mToken = AppPreferences.init(this).getString(Constants.TOKEN)
        viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
        ll_submit.setOnClickListener(this)
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.ll_submit ->
            {
                if(!et_new_pwd.text.isNullOrEmpty())
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
        cpLoaderOtp.visibility = View.VISIBLE
        viewModel?.forgetPassword(mToken,mUser_id.toString(),et_new_pwd.text.toString())?.observe(
            this,
            object : Observer<ForgetPasswordResponse> {
                override fun onChanged(@Nullable forgetPasswordResponse: ForgetPasswordResponse?) {
                    cpLoaderOtp.visibility = View.GONE
                    statusCode=forgetPasswordResponse?.statusCode

                    if(statusCode==200)
                    {

                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        showSnackBar("Password changed successfully")
                        finishAffinity()


                    }
                    else
                    {
                        showSnackBar(forgetPasswordResponse?.message)
                    }

                }
            })

    }

    private fun showSnackBar(message: String?) {
        SnackbarUtil.showWarningShortSnackbar(this,message)
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
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
