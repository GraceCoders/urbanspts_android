package com.urbanspts.urbanspts.view.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mukesh.OnOtpCompletionListener
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.model.VarifyOtp.VarifyOtpResponse
import com.urbanspts.urbanspts.viewmodel.VarifyOtpViewModel
import kotlinx.android.synthetic.main.activity_otp.*

class OtpActivity : AppCompatActivity(), View.OnClickListener {
    var mobileNumber:String?=null
    var countryCode:String?=null
    var otpCode:String?=null
    var mEmail:String?=null
    private  var viewModel: VarifyOtpViewModel?=null
    var statusCode:Int?=null
    var isExist:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        initView()
    }

    private fun initView() {
        mEmail = AppPreferences.init(this).getString(Constants.EMAIL)
        viewModel = ViewModelProviders.of(this).get(VarifyOtpViewModel::class.java)
        ll_submit.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        otp_view!!.setOtpCompletionListener(object : OnOtpCompletionListener {
            override fun onOtpCompleted(otp: String?) {
                otpCode=otp
                Log.e("code",otpCode+"")
            }


        })

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_submit -> {
                if (otpCode!!.isNotEmpty()) {


                    VarifyOtp()
                } else {
                    showSnackBar("Please Enter OTP Before Submit")
                }
            }
            R.id.iv_back ->
            {
                onBackPressed()
            }
        }
    }
//..............................varify otp api.............................................

    private fun VarifyOtp() {
        clLoaderOtp.visibility = View.VISIBLE
        viewModel?.varifyOtp(mEmail,otpCode)?.observe(
            this,
            object : Observer<VarifyOtpResponse> {
                override fun onChanged(@Nullable varifyOtpResponse : VarifyOtpResponse?) {
                    clLoaderOtp.visibility = View.GONE
                    statusCode=varifyOtpResponse?.statusCode

                    if(statusCode==200)
                    {
                        val intent = Intent(applicationContext, ForgotPassword::class.java)
                        startActivity(intent)

                    }
                    else
                    {
                        showSnackBar(varifyOtpResponse?.message)
                    }

                }
            })

    }

    private fun showSnackBar(message: String?) {
        SnackbarUtil.showWarningShortSnackbar(this,message)
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
