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
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.model.CategoryDetailModel.CategoryDetailResponse
import com.urbanspts.urbanspts.model.CategoryDetailModel.ProviderData
import com.urbanspts.urbanspts.model.CategoryDetailModel.SubcategoriesData
import com.urbanspts.urbanspts.view.adapter.Category_Provider_Adapter
import com.urbanspts.urbanspts.view.adapter.SubCategoryAdapter
import com.urbanspts.urbanspts.viewmodel.CategoryDetailViewModel
import kotlinx.android.synthetic.main.activity_category_detail_page.*

class CategoryDetailPage : AppCompatActivity(),View.OnClickListener {
    var gps: Gps? = null
    var current_lat:Double?=null
    var current_long:Double?=null
    var status_code:String?=null
    var authToken = ""
    var mCategory_id:String?=null
    var mCategory_name:String?=null
    private  var viewModel: CategoryDetailViewModel?=null
    private var categoryListing: ArrayList<SubcategoriesData> = ArrayList()
    private var providerListing: ArrayList<ProviderData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail_page)
        initView()
    }

    private fun initView() {
        mCategory_id=intent.getStringExtra("category_id")
        mCategory_name=intent.getStringExtra("category_name")
        tv_category_name.text=mCategory_name
        iv_back.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this).get(CategoryDetailViewModel::class.java)
        getLocations()
        getCategoryDetailApi()


    }


    //................................category detail api...........................................

    private fun getCategoryDetailApi() {
        clLoaderCategory.visibility = View.VISIBLE
        viewModel?.getCategoryDetails(mCategory_id.toString(),current_lat,current_long)?.observe(
            this,
            object : Observer<CategoryDetailResponse> {
                override fun onChanged(@Nullable categoryDetailResponse: CategoryDetailResponse) {
                    clLoaderCategory.visibility = View.GONE
                    Log.e("status", categoryDetailResponse.status)
                    categoryListing =
                        categoryDetailResponse.data.subcategoriesData as ArrayList<SubcategoriesData>
                    providerListing =
                        categoryDetailResponse.data.providerData as ArrayList<ProviderData>

                    //.....sub category adapter....................
                    if (categoryListing.size > 0) {
                        setSubCategoryAdapter()

                    } else {
                        rv_sub_categories.visibility = View.GONE
                    }

                    //................provider adapter..................................

                    if(providerListing.size>0)
                    {
                              providerListingDetails()
                    }

                    Log.e("listingsize",categoryListing.size.toString())


                }
            })
    }

    //............................provider listing details.................................
    private fun providerListingDetails() {
        rv_spots?.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL ,false)
        rv_spots?.adapter= Category_Provider_Adapter(this,providerListing)
        (rv_spots?.adapter as Category_Provider_Adapter).onItemClick = { pos, view ->


            var mProviderId = providerListing?.get(pos)?.providerId.id
            var mProviderName = providerListing?.get(pos)?.providerId.name
            val intent = Intent(this, ProviderDetailActivity::class.java)
            intent.putExtra("provider_id", mProviderId)
            intent.putExtra("provider_name", mProviderName)
            startActivity(intent)


        }
        rv_spots?.setNestedScrollingEnabled(false)
    }


    //...................................sub category adapter.......................................

    private fun setSubCategoryAdapter() {
        rv_sub_categories?.layoutManager= LinearLayoutManager(applicationContext,
            LinearLayoutManager.HORIZONTAL ,false)
        rv_sub_categories?.adapter= SubCategoryAdapter(this@CategoryDetailPage,categoryListing)
        (rv_sub_categories?.adapter as SubCategoryAdapter).onItemClick = { pos, view ->


            var mCategoryid=categoryListing?.get(pos)?.id.toString()
            var mCategoryName=categoryListing?.get(pos)?.name
            val intent = Intent(this, SubCategoryDetails::class.java)
            intent.putExtra("category_id",mCategoryid)
            intent.putExtra("category_name",mCategoryName)
            startActivity(intent)


        }
        rv_sub_categories?.setNestedScrollingEnabled(false)
    }

    private fun showSnackBar(message: String?) {
        SnackbarUtil.showWarningShortSnackbar(this,message)
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
