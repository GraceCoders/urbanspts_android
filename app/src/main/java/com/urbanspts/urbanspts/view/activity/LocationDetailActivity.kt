package com.urbanspts.urbanspts.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.Gps
import com.urbanspts.urbanspts.model.LocationProviderModel.LocationProvoderResponse
import com.urbanspts.urbanspts.model.LocationProviderModel.ProviderId
import com.urbanspts.urbanspts.model.SubCategoryModel.ProviderData
import com.urbanspts.urbanspts.model.SubCategoryModel.SubCategoryDetailResponse
import com.urbanspts.urbanspts.view.adapter.Locations_Provider_Adapter
import com.urbanspts.urbanspts.view.adapter.SubCategory_Provider_Adapter
import com.urbanspts.urbanspts.viewmodel.LocationProviderModel
import com.urbanspts.urbanspts.viewmodel.SubCategoryDetailViewModel
import kotlinx.android.synthetic.main.activity_sub_category_details.*
import kotlinx.android.synthetic.main.activity_sub_category_details.iv_back
import kotlinx.android.synthetic.main.activity_whishlist.*

class  LocationDetailActivity : AppCompatActivity(),View.OnClickListener {
    var gps: Gps? = null
    var current_lat:Double?=null
    var current_long:Double?=null
    var status_code:String?=null
    var authToken = ""
    var mCategory_id:String?=null
    var mCategory_name:String?=null
    private  var viewModel: LocationProviderModel?=null
    private var providerListing: ArrayList<ProviderId> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category_details)
        initView()
    }
    private fun initView() {
        mCategory_id=intent.getStringExtra("category_id")
        mCategory_name=intent.getStringExtra("category_name")
        tv_category_name.text=mCategory_name
        iv_back.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this).get(LocationProviderModel::class.java)
        getLocations()
        getProviderApi()
    }

    //..........................sub category detail api...................................

    private fun getProviderApi() {
        clLoaderSubCategory.visibility = View.VISIBLE
        viewModel?.getLocationProviderDetails(mCategory_id.toString(),current_lat,current_long)?.observe(
            this,
            object : Observer<LocationProvoderResponse> {
                override fun onChanged(@Nullable locationProvoderResponse: LocationProvoderResponse) {
                    clLoaderSubCategory.visibility = View.GONE
                    Log.e("status", locationProvoderResponse.status)
                    providerListing =
                        locationProvoderResponse.data.providerId as ArrayList<ProviderId>

                    //.....sub category Provider adapter....................
                    if (providerListing.size > 0) {
                        setSubCategoryProviderAdapter()

                    } else {
                        rv_spots_sub_category.visibility = View.GONE
                        tv_no_data.visibility = View.VISIBLE

                    }



                }
            })
    }

    //.....................................set sub category adapter..............................

    private fun setSubCategoryProviderAdapter() {
        rv_spots_sub_category?.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL ,false)
        rv_spots_sub_category?.adapter= Locations_Provider_Adapter(this,providerListing)
        (rv_spots_sub_category?.adapter as Locations_Provider_Adapter).onItemClick = { pos, view ->


            var mProviderId = providerListing?.get(pos)?.id
            var mProviderName = providerListing?.get(pos)?.name
            val intent = Intent(this, ProviderDetailActivity::class.java)
            intent.putExtra("provider_id", mProviderId)
            intent.putExtra("provider_name", mProviderName)
            startActivity(intent)

        }
        rv_spots_sub_category?.setNestedScrollingEnabled(false)
    }
    //.......................get current locations.......................................

    private fun getLocations() {
        gps = Gps(this)

        if (gps!!.canGetLocation()) {
            current_lat = gps!!.getLatitude()
            current_long = gps!!.getLongitude()
            Log.e("current_lat", current_lat.toString() + "")
            Log.e("current_long", current_long.toString() + "")
        } else {
            gps!!.showSettingsAlert()
        }

    }
    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.iv_back ->
            {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
