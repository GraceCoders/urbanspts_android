package com.urbanspts.urbanspts.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.controller.utills.PermissionCheckUtil

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L
    var user_id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView()
    }
    private fun initView() {
        user_id = AppPreferences.init(this).getString(Constants.USER_ID)
        PermissionCheckUtil.create(this@SplashActivity, object :
            PermissionCheckUtil.onPermissionCheckCallback {
            override fun onPermissionSuccess() {
                splash()
            }
        })



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionCheckUtil.PERMISSION_REQUEST_CODE) {
            splash()
        }
    }


    private fun splash() {

        Handler().postDelayed(
            {
                if(user_id.isNullOrEmpty())

                {
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else
                {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }

            }, SPLASH_TIME_OUT)
    }
}
