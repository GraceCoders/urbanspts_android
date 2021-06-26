package com.urbanspts.urbanspts.view.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.controller.utills.Validations
import com.urbanspts.urbanspts.model.registerModel.RegisterResponse
import com.urbanspts.urbanspts.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(),View.OnClickListener {
    private  var viewModel: RegisterViewModel?=null
    var statusCode:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)
        initView()
    }

    private fun initView() {
        ll_register.setOnClickListener(this)
        tv_login_now.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.ll_register ->
            {

                if(Validations.isValidateSignup(this,et_name,et_last_name,et_email_signup,et_pwd_signup))
                {
                   signUp()
                }
            }
            R.id.tv_login_now ->
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }

    }

    private fun signUp() {
        clLoader_signup.visibility = View.VISIBLE
        viewModel?.signup(et_email_signup.text.toString().trim(),et_pwd_signup.text.toString().trim(),et_name.text.toString().trim(),et_last_name.text.toString().trim())?.observe(
            this,
            object : Observer<RegisterResponse> {
                override fun onChanged(@Nullable registerResponse: RegisterResponse?) {
                    clLoader_signup.visibility = View.GONE
                    statusCode=registerResponse?.statusCode

                    if(statusCode==200)
                    {

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
                    else
                    {
                        showSnackBar(registerResponse?.message)
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
