package com.urbanspts.urbanspts.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.controller.utills.Validations
import com.urbanspts.urbanspts.model.SendOtp.SendOtpResponse
import com.urbanspts.urbanspts.model.loginModel.LoginResponse
import com.urbanspts.urbanspts.viewmodel.LoginViewModel
import com.urbanspts.urbanspts.viewmodel.SendOtpViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

     private  var viewModel: LoginViewModel?=null
     private  var viewModelSendOtp: SendOtpViewModel?=null
    var statusCode:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        initView()

    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModelSendOtp = ViewModelProviders.of(this).get(SendOtpViewModel::class.java)
        ll_login.setOnClickListener(this)
        tv_register_now.setOnClickListener(this)
        tv_forget_pwd.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
            when(v?.id)
            {
                 R.id.ll_login ->
                 {
                     if(Validations.isValidateLogin(this,et_email,et_pwd))
                     {
                         doLogin()
                     }

                 }
                R.id.tv_register_now ->
                {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }

                R.id.tv_forget_pwd ->
                {

                    if(!et_email.text.isNullOrEmpty())
                    {
                        sendOtpApi()
                    }
                    else
                    {
                        showSnackBar("Please Enter Your Registered Email Id")
                    }

                }
            }
    }

    private fun sendOtpApi() {
        clLoader.visibility = View.VISIBLE
        viewModelSendOtp?.sendOtp(et_email.text.toString().trim())?.observe(
            this,
            object : Observer<SendOtpResponse> {
                override fun onChanged(@Nullable sendOtpResponse: SendOtpResponse?) {
                    clLoader.visibility = View.GONE
                    statusCode=sendOtpResponse?.statusCode

                    if(statusCode==200)
                    {

                        val intent = Intent(applicationContext, OtpActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
                    else
                    {
                        showSnackBar(sendOtpResponse?.message)
                    }

                }
            })


    }

    //>..................................api login..........................................>//

    private fun doLogin() {
        clLoader.visibility = View.VISIBLE
        viewModel?.login(et_email.text.toString().trim(),et_pwd.text.toString().trim())?.observe(
            this,
            object : Observer<LoginResponse> {
                override fun onChanged(@Nullable loginResponse: LoginResponse?) {
                    clLoader.visibility = View.GONE
                    statusCode=loginResponse?.statusCode

                    if(statusCode==200)
                    {

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
                    else
                    {
                        showSnackBar(loginResponse?.message)
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


}
