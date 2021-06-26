package com.urbanspts.urbanspts.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
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
import com.urbanspts.urbanspts.model.AddWishlist.AddWishlistResponse
import com.urbanspts.urbanspts.model.ProviderDetailModel.ProviderDetailResponse
import com.urbanspts.urbanspts.model.RemoveWishlist.RemoveWishlist
import com.urbanspts.urbanspts.model.WishlistModel.Data
import com.urbanspts.urbanspts.model.WishlistModel.WishlistResponse
import com.urbanspts.urbanspts.viewmodel.AddWishListViewModel
import com.urbanspts.urbanspts.viewmodel.ProviderDetailViewModel
import com.urbanspts.urbanspts.viewmodel.RemoveWishListViewModel
import kotlinx.android.synthetic.main.activity_provider_detail.*
import kotlinx.android.synthetic.main.activity_provider_detail.iv_back
import kotlinx.android.synthetic.main.activity_sub_category_details.*
import kotlinx.android.synthetic.main.activity_whishlist.*
import kotlinx.android.synthetic.main.item_categories_listing.view.*

class ProviderDetailActivity : AppCompatActivity(),View.OnClickListener, OnMapReadyCallback{
    private var mMap: GoogleMap? = null
    var mUser_id:String?=null
    var mProvider_id:String?=null
    var mCoverImage:String?=null
    var mProvider_name:String?=null
    var mLatlong:Double?=null
    var mlong:String?=null
    var maddress:String?=null
    var mMobilenumber:String?=null
    var mOpeningtime:String?=null
    var mOpeningday:String?=null
    var mClosingDay:String?=null
    var mClosingtime:String?=null
    var mInstagram:String?=null
    var mFacebook:String?=null
    var mTwitter:String?=null
    var mYouTube:String?=null
    var mProviderUrl:String?=null
    var mAmenties:String?=null
    var mTags:String?=null
    var mDescriptions:String?=null
    var mWishlist:String?=null
    var mLat:Double?=null
    var mLong:Double?=null
    var statusCode:Int?=null
    var isWishlist:String?="0"
    var mToken:String?=null
    private var mLatlongList: ArrayList<Double> = ArrayList()
    private  var viewModel: ProviderDetailViewModel?=null
    private  var viewModelAddWishlist: AddWishListViewModel?=null
    private  var viewModelRemoveWishlist: RemoveWishListViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_detail)
        initView()
    }

    private fun initView() {
        mProvider_id=intent.getStringExtra("provider_id")
        mProvider_name=intent.getStringExtra("provider_name")
        mUser_id = AppPreferences.init(this).getString(Constants.USER_ID)
        mToken = AppPreferences.init(this).getString(Constants.TOKEN)
        viewModel = ViewModelProviders.of(this).get(ProviderDetailViewModel::class.java)
        viewModelAddWishlist = ViewModelProviders.of(this).get(AddWishListViewModel::class.java)
        viewModelRemoveWishlist = ViewModelProviders.of(this).get(RemoveWishListViewModel::class.java)
        tv_provider_name.text=mProvider_name
        setlistner()
        hitProviderDetailsApi()

    }

    //.........................................set click listner......................................
    private fun setlistner() {
        img_fb.setOnClickListener(this)
        img_instagram.setOnClickListener(this)
        img_twitter.setOnClickListener(this)
        img_you_tube.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        tv_book.setOnClickListener(this)
        img_fav.setOnClickListener(this)

    }

    //.................................hit provider detail api...............................

    private fun hitProviderDetailsApi() {

        clProviderdetails.visibility = View.VISIBLE
        viewModel?.getProviderDetails(mUser_id.toString(),mProvider_id.toString())?.observe(
            this,
            object : Observer<ProviderDetailResponse> {
                override fun onChanged(@Nullable providerDetailResponse: ProviderDetailResponse?) {
                    clProviderdetails.visibility = View.GONE
                    statusCode= providerDetailResponse?.statusCode

                    if(statusCode==200)
                    {

                        mCoverImage=providerDetailResponse?.data?.providerId?.coverPhoto
                        maddress=providerDetailResponse?.data?.providerId?.address
                        mMobilenumber=providerDetailResponse?.data?.providerId?.phoneNumber
                        mDescriptions=providerDetailResponse?.data?.providerId?.description
                        mOpeningday=providerDetailResponse?.data?.providerId?.openingDay
                        mClosingDay=providerDetailResponse?.data?.providerId?.closingDay
                        mOpeningtime=providerDetailResponse?.data?.providerId?.openingTime
                        mClosingtime=providerDetailResponse?.data?.providerId?.closingTime
                        mFacebook=providerDetailResponse?.data?.providerId?.facebook
                        mYouTube=providerDetailResponse?.data?.providerId?.youtube
                        mTwitter=providerDetailResponse?.data?.providerId?.twitter
                        mInstagram=providerDetailResponse?.data?.providerId?.instagram
                        isWishlist=providerDetailResponse?.data?.isWishList.toString()
                        mAmenties=providerDetailResponse?.data?.providerId?.subcategoriesparse?.get(0)?.name
                        mTags=providerDetailResponse?.data?.providerId?.categoriesparse?.get(0)?.name
                        mLatlongList= providerDetailResponse?.data?.providerId?.providerLocation?.coordinates as ArrayList<Double>
                        mProviderUrl=providerDetailResponse?.data?.providerId?.url
                        setData()
                        for(i in 0 until mLatlongList.size)
                        {
                            if(i==0)
                            {
                                mLat=mLatlongList.get(i)
                                Log.e("lat",mLatlongList.get(i).toString())
                            }
                            else
                            {
                                mLong=mLatlongList.get(i)
                                Log.e("long",mLatlongList.get(i).toString())
                            }



                        }
                    setUpMapIfNeeded()

                    }
                    else
                    {
                        showSnackBar(providerDetailResponse?.message)
                    }



                }
            })
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


        val sydney = LatLng(mLat!!.toDouble(), mLong!!.toDouble())
        mMap!!.isMyLocationEnabled = true
        val markerOptions = MarkerOptions().position(sydney).title("we are here!")
        mMap?.animateCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17f))
        mMap?.addMarker(markerOptions)
    }

    //.....................................set all data.....................................

    private fun setData() {
        if(mCoverImage !==null)
        {
            this?.let {
                Glide.with(it)
                    .load(mCoverImage)
                    .into(img_cover)
            }
        } else {
           img_cover.setImageResource(R.drawable.deal_img)

        }
        if(!mDescriptions.isNullOrEmpty())
        {
            tv_description?.text= Html.fromHtml(mDescriptions)
        }
        tv_amenties.text=mAmenties
        tv_tags.text=mTags
        tv_address.text=maddress
        tv_mobile_number.text=mMobilenumber
        tv_opening_days.text=mOpeningday+" To "+mClosingDay
        tv_timings.text=mOpeningtime+" To "+mClosingtime

        if(isWishlist.equals("1"))
        {
            img_fav.setImageResource(R.drawable.wishlist_active)
        }
        else
        {
            img_fav.setImageResource(R.drawable.wishlist)
        }

    }

    private fun showSnackBar(message: String?) {
        SnackbarUtil.showWarningShortSnackbar(this,message)
    }

    //......................................click listner................................
    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.img_fb->
            {
                      if(!mFacebook.isNullOrEmpty())
                      {
                          val intent = Intent(this, WebViewActivity::class.java)
                          intent.putExtra("url", mFacebook)
                          startActivity(intent)
                      }
                else
                      {
                          showSnackBar("Spot not provided detail for Facebook account yet")
                      }
            }
            R.id.img_instagram->
            {
                if(!mInstagram.isNullOrEmpty())
                {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("url", mInstagram)
                    startActivity(intent)
                }
                else
                {
                    showSnackBar("Spot not provided detail for Instagram account yet")
                }
            }
            R.id.img_twitter ->
            {
                if(!mTwitter.isNullOrEmpty())
                {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("url", mTwitter)
                    startActivity(intent)
                }
                else
                {
                    showSnackBar("Spot not provided detail for Twitter account yet")
                }
            }
            R.id.img_you_tube ->
            {
                if(!mYouTube.isNullOrEmpty())
                {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("url", mYouTube)
                    startActivity(intent)
                }
                else
                {
                    showSnackBar("Spot not provided detail for You tube account yet")
                }
            }
            R.id.iv_back ->
            {
                     onBackPressed()
            }
            R.id.tv_book ->
            {
                if(!mProviderUrl.isNullOrEmpty())
                {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("url", mProviderUrl)
                    startActivity(intent)
                }
                else
                {
                    showSnackBar("Spot not provided detail for their website url yet")
                }
            }
            R.id.tv_book ->
            {
                if(!mProviderUrl.isNullOrEmpty())
                {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("url", mProviderUrl)
                    startActivity(intent)
                }
                else
                {
                    showSnackBar("Spot not provided detail for their website url yet")
                }
            }
            R.id.img_fav ->
            {
                if(isWishlist.equals("1"))
                {
                    removeFromWishlist()
                }
                else
                {
                    addWishlist()
                }
            }
        }

    }

    //.......................................add to wishlist.................................................

    private fun addWishlist() {

        clProviderdetails.visibility = View.VISIBLE
        viewModelAddWishlist?.AddWishlist(mToken,mUser_id.toString(),mProvider_id.toString())?.observe(
            this,
            object : Observer<AddWishlistResponse> {
                override fun onChanged(@Nullable addWishlistResponse:AddWishlistResponse) {
                    clProviderdetails.visibility = View.GONE
                    val status=addWishlistResponse.statusCode
                    if(status==200)
                    {
                        img_fav.setImageResource(R.drawable.wishlist_active)
                        isWishlist="1"
                        showSnackBar("Added To Wishlist Successfully")
                    }
                    else
                    {
                        showSnackBar(addWishlistResponse.message)
                    }

                }
            })

    }

    //....................................remove from wishlist..............................................

    private fun removeFromWishlist() {

        clProviderdetails.visibility = View.VISIBLE
        viewModelRemoveWishlist?.RemoveWishlist(mToken,mUser_id.toString(),mProvider_id.toString())?.observe(
            this,
            object : Observer<RemoveWishlist> {
                override fun onChanged(@Nullable removeWishlist:RemoveWishlist) {
                    clProviderdetails.visibility = View.GONE
                    val status=removeWishlist.statusCode
                    if(status==200)
                    {
                        img_fav.setImageResource(R.drawable.wishlist)
                        isWishlist="0"
                        showSnackBar("Removed Successfully")
                    }
                    else
                    {
                        showSnackBar(removeWishlist.message)
                    }

                }
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

}
