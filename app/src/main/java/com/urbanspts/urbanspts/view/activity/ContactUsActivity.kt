package com.urbanspts.urbanspts.view.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.controller.utills.Validations
import com.urbanspts.urbanspts.model.ContactUsModel.ContactUsResponse
import com.urbanspts.urbanspts.viewmodel.ContactUsViewModel
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_login.*

class ContactUsActivity : AppCompatActivity(),View.OnClickListener, OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    var mUser_id:String?=null
    var mToken:String?=null
    var statusCode:Int?=null
    private  var viewModel: ContactUsViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(ContactUsViewModel::class.java)
        mUser_id = AppPreferences.init(this).getString(Constants.USER_ID)
        mToken = AppPreferences.init(this).getString(Constants.TOKEN)
        ll_send_message.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        setUpMapIfNeeded()

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.ll_send_message ->
            {
                if(Validations.isContactUs(this,et_your_name,et_email_signup,et_message))
                {
                    contactUsApi()
                }

            }
            R.id.iv_back ->
            {
                onBackPressed()

            }
        }
    }

    private fun setUpMapIfNeeded() {

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) { // TODO: Consider calling
            return
        }


        val sydney = LatLng(52.291850, -1.548610)
        mMap!!.isMyLocationEnabled = true
        val markerOptions = MarkerOptions().position(sydney).title("we are here!")
        mMap?.animateCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))
        mMap?.addMarker(markerOptions)
    }

    //>..................................api login..........................................>//

    private fun contactUsApi() {
        cl_contactus_Loader.visibility = View.VISIBLE
        viewModel?.contactUs(mToken,et_your_name.text.toString(),et_email_signup.text.toString().trim(),et_message.text.toString())?.observe(
            this,
            object : Observer<ContactUsResponse> {
                override fun onChanged(@Nullable contactUsResponse: ContactUsResponse?) {
                    cl_contactus_Loader.visibility = View.GONE
                    statusCode=contactUsResponse?.statusCode

                    if(statusCode==200)
                    {
                        et_your_name.text?.clear()
                        et_email_signup.text?.clear()
                        et_message.text?.clear()
                        showSnackBar("Message sent sucussfully one of our team member will reach you regarding your queries thanks")

                    }
                    else
                    {
                        showSnackBar(contactUsResponse?.message)
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
