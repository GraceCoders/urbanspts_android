package com.urbanspts.urbanspts.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.model.WishlistModel.Data
import com.urbanspts.urbanspts.model.WishlistModel.WishlistResponse
import com.urbanspts.urbanspts.view.adapter.Wishlist_Adapter
import com.urbanspts.urbanspts.viewmodel.WishlistViewModel
import kotlinx.android.synthetic.main.activity_sub_category_details.*
import kotlinx.android.synthetic.main.activity_whishlist.*
import kotlinx.android.synthetic.main.activity_whishlist.iv_back

class WhishlistActivity : AppCompatActivity(), View.OnClickListener {
    var mUser_id:String?=null
    var mToken:String?=null
    var statusCode:Int?=null
    private  var viewModel: WishlistViewModel?=null
    private var wishlisting: ArrayList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_whishlist)
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(WishlistViewModel::class.java)
        mUser_id = AppPreferences.init(this).getString(Constants.USER_ID)
        mToken = AppPreferences.init(this).getString(Constants.TOKEN)
        iv_back.setOnClickListener(this)
        getWishlistApi()


    }

    //..............................get wishlist api........................................


    private fun getWishlistApi() {
        clwishlistloader.visibility = View.VISIBLE
        viewModel?.getWishlist(mToken,mUser_id.toString())?.observe(
            this,
            object : Observer<WishlistResponse> {
                override fun onChanged(@Nullable wishlistResponse: WishlistResponse) {
                    clwishlistloader.visibility = View.GONE
                    val status=wishlistResponse.statusCode
                    wishlisting =
                            wishlistResponse.data as ArrayList<Data>
                    if(wishlisting.size>0)
                    {

                        setWishlistAdapter()
                    }
                    else
                    {
                        rv_wishlist.visibility = View.GONE
                        tv_no_data_wishlist.visibility = View.VISIBLE
                    }

                }
            })

    }

    //.............................set wishlist adapter.......................................

    private fun setWishlistAdapter() {
        rv_wishlist?.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL ,false)
        rv_wishlist?.adapter= Wishlist_Adapter(this,wishlisting)
        (rv_wishlist?.adapter as Wishlist_Adapter).onItemClick = { pos, view ->


            var mProviderId = wishlisting?.get(pos)?.providerId.id
            var mProviderName = wishlisting?.get(pos)?.providerId.name
            val intent = Intent(this, ProviderDetailActivity::class.java)
            intent.putExtra("provider_id", mProviderId)
            intent.putExtra("provider_name", mProviderName)
            startActivity(intent)


        }
        rv_wishlist?.setNestedScrollingEnabled(false)
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
        this.finish()
    }
}
