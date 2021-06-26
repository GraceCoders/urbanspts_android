package com.urbanspts.urbanspts.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.view.activity.EditProfileActivity
import com.urbanspts.urbanspts.view.activity.RegisterActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.tv_name
import kotlinx.android.synthetic.main.item_categories_listing.view.*

class Profile_fragment : Fragment(),View.OnClickListener {
    internal var rootView: View? = null
    var mFirst_name:String?=null
    var mLast_name:String?=null
    var mEmail_id:String?=null
    var mProfilePic:String?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView= inflater.inflate(R.layout.fragment_profile, container, false)
        initView()
        return  rootView;
    }

    private fun initView() {
        rootView?.ll_edit_profile?.setOnClickListener(this)
        mFirst_name = AppPreferences.init(activity).getString(Constants.F_NAME)
        mLast_name = AppPreferences.init(activity).getString(Constants.L_NAME)
        mEmail_id = AppPreferences.init(activity).getString(Constants.EMAIL)
        mProfilePic = AppPreferences.init(activity).getString(Constants.PROFILE_IMAGE)
        Log.e("profile_pic","pic"+mProfilePic)

        setProfileData()
    }

    ///.......................................set data .............................................
    private fun setProfileData() {
        rootView?.tv_name?.text=mFirst_name
        rootView?.tv_last_name?.text=mLast_name
        rootView?.tv_email?.text=mEmail_id

        if(mProfilePic.equals("null"))
        {
            rootView?.img_profile_pic?.setImageResource(R.drawable.user)


        } else {
            context?.let {
                Glide.with(it)
                    .load(mProfilePic)
                    .into(rootView!!.img_profile_pic)
            }

        }

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.ll_edit_profile ->
            {
                val intent = Intent(activity, EditProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        initView()
        super.onResume()

    }
}