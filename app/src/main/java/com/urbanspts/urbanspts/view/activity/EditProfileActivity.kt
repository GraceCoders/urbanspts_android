package com.urbanspts.urbanspts.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.controller.utills.SnackbarUtil
import com.urbanspts.urbanspts.controller.utills.Validations
import com.urbanspts.urbanspts.model.UpdateImageModel.UpdateImageResponse
import com.urbanspts.urbanspts.model.UpdateProfile.UpdateUserProfile
import com.urbanspts.urbanspts.viewmodel.UpdateImageViewModel
import com.urbanspts.urbanspts.viewmodel.UpdateProfileViewModel
import kotlinx.android.synthetic.main.activity_edit_profile.*
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.security.AccessController.getContext

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    var mFirst_name:String?=null
    var mLast_name:String?=null
    var mEmail_id:String?=null
    var mProfilePic:String?=null
    var mToken:String?=null
    var statusCode:Int?=null
    private val PICK_FROM_GALLARY = 1
    var mImagefile: File? = null
    private  var viewModel: UpdateProfileViewModel?=null
    private  var viewModelImage: UpdateImageViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_edit_profile)
        initView()
    }

    private fun initView() {
        mToken = AppPreferences.init(this).getString(Constants.TOKEN)
        mFirst_name = AppPreferences.init(this).getString(Constants.F_NAME)
        mLast_name = AppPreferences.init(this).getString(Constants.L_NAME)
        mEmail_id = AppPreferences.init(this).getString(Constants.EMAIL)
        mProfilePic = AppPreferences.init(this).getString(Constants.PROFILE_IMAGE)
        viewModel = ViewModelProviders.of(this).get(UpdateProfileViewModel::class.java)
        viewModelImage = ViewModelProviders.of(this).get(UpdateImageViewModel::class.java)
        setProfileData()
        iv_back.setOnClickListener(this)
        ll_save.setOnClickListener(this)
        ll_upload.setOnClickListener(this)
    }

    ///.......................................set data .............................................
    private fun setProfileData() {
       tv_name.setText(mFirst_name)
        tv_last_name?.setText(mLast_name)
        tv_email?.setText(mEmail_id)

        if(mProfilePic.equals("null"))
        {
            img_profile_pic?.setImageResource(R.drawable.user)


        } else {
            this?.let {
                Glide.with(it)
                    .load(mProfilePic)
                    .into(img_profile_pic)
            }

        }

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.iv_back ->
            {
                onBackPressed()
            }
            R.id.ll_save ->
            {
                if(Validations.isValidateUpdateProfile(this,tv_name,tv_last_name,tv_email))
                {
                    updateProfileApi()
                }

            }

            R.id.ll_upload ->
            {

                pickPicture()
            }
        }

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>image from camera>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private fun pickPicture() {
        if (EasyPermissions.hasPermissions(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(
                galleryIntent, PICK_FROM_GALLARY
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Need Permission to access your Gallery and Camera", PICK_FROM_GALLARY,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }

    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>on activity result for camera and gallery>>>>>>>>>>>>>>>>>>>>>>>>>

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_FROM_GALLARY -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = data?.data
                mImagefile = File(getRealPathFromURI(selectedImage))
                Log.e("imagefile", mImagefile.toString())
                img_profile_pic?.setImageURI(selectedImage)
                if(mImagefile!=null)
                {
                    uploadProfileimage()
                }

            }
        }
    }

    //.........................................upload profile image............................................


    private fun uploadProfileimage() {
        cl_update_profile?.visibility = View.VISIBLE
        viewModelImage?.updateImage(mToken.toString(),mImagefile)?.observe(
            this,
            object : Observer<UpdateImageResponse> {
                override fun onChanged(@Nullable updateImageResponse: UpdateImageResponse?) {
                    cl_update_profile?.visibility = View.GONE
                    statusCode=updateImageResponse?.statusCode
                    Log.e("code",statusCode.toString()+"")

                    if(statusCode==200)
                    {
                        showSnackBar(updateImageResponse?.message)

                    }
                    else
                    {
                        showSnackBar(updateImageResponse?.message)
                    }

                }
            })

    }

    //.................................getting real path of image....................................

    fun getRealPathFromURI(uri: Uri?): String? {
        @SuppressLint("Recycle") val cursor: Cursor =
            this.getContentResolver()?.query(uri!!, null, null, null, null)!!
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }

    //...............................update profile api.................................

    private fun updateProfileApi() {
        cl_update_profile.visibility = View.VISIBLE
        viewModel?.Updateprofile(mToken,tv_email.text.toString(),tv_name.text.toString(),tv_last_name.text.toString())?.observe(
            this,
            object : Observer<UpdateUserProfile> {
                override fun onChanged(@Nullable updateUserProfile: UpdateUserProfile?) {
                    cl_update_profile.visibility = View.GONE
                    statusCode=updateUserProfile?.statusCode

                    if(statusCode==200)
                    {
                        showSnackBar("Profile Updated successfully")

                    }
                    else
                    {
                        showSnackBar(updateUserProfile?.message)
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
}
