package com.urbanspts.urbanspts.controller.services


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.urbanspts.urbanspts.controller.repository.APIClient
import com.urbanspts.urbanspts.controller.repository.APIInterface
import com.urbanspts.urbanspts.controller.utills.App
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.model.AddWishlist.AddWishlistResponse
import com.urbanspts.urbanspts.model.CategoryDetailModel.CategoryDetailResponse
import com.urbanspts.urbanspts.model.ChangePasswordModel.ChangePasswordResponse
import com.urbanspts.urbanspts.model.ContactUsModel.ContactUsResponse
import com.urbanspts.urbanspts.model.ForgetPassword.ForgetPasswordResponse
import com.urbanspts.urbanspts.model.HomeModel.HomeResponse
import com.urbanspts.urbanspts.model.LocationModel.LocationResponse
import com.urbanspts.urbanspts.model.LocationProviderModel.LocationProvoderResponse
import com.urbanspts.urbanspts.model.ProviderDetailModel.ProviderDetailResponse
import com.urbanspts.urbanspts.model.RemoveWishlist.RemoveWishlist
import com.urbanspts.urbanspts.model.RequestModel.HomeRequest.HomeRequestModel
import com.urbanspts.urbanspts.model.RequestModel.ProviderRequest.LocationProviderRequest
import com.urbanspts.urbanspts.model.RequestModel.SubCategoryRequest.SubCategoryRequest
import com.urbanspts.urbanspts.model.RequestModel.categoryRequest.CategoryDetailRequest
import com.urbanspts.urbanspts.model.SendOtp.SendOtpResponse
import com.urbanspts.urbanspts.model.SubCategoryModel.SubCategoryDetailResponse
import com.urbanspts.urbanspts.model.UpdateImageModel.UpdateImageResponse
import com.urbanspts.urbanspts.model.UpdateProfile.UpdateUserProfile
import com.urbanspts.urbanspts.model.VarifyOtp.VarifyOtpResponse
import com.urbanspts.urbanspts.model.WishlistModel.WishlistResponse
import com.urbanspts.urbanspts.model.categoriesModel.Category
import com.urbanspts.urbanspts.model.categoriesModel.CategoryResponse
import com.urbanspts.urbanspts.model.loginModel.LoginResponse
import com.urbanspts.urbanspts.model.registerModel.RegisterResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


object APIRepository {
    private var apiInterface: APIInterface = APIClient.retrofit(Constants.BASE_URL_API)
    private var categoryList: ArrayList<Category> = ArrayList()
    private var locationsList: ArrayList<LocationResponse> = ArrayList()

    //get categories
    fun getCategories(
    ): LiveData<List<Category>> {
        val mutableLiveData: MutableLiveData<List<Category>> = MutableLiveData()
        val call = apiInterface.categories()
        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val categoryResponse: CategoryResponse? = response.body()
                        Log.e("msg", categoryResponse?.data?.categories?.get(1)?.name)

                        categoryList = categoryResponse?.data?.categories as ArrayList<Category>
                        mutableLiveData.value = categoryList
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //get Locations
    fun getLocations(
    ): LiveData<List<LocationResponse>> {
        val mutableLiveData: MutableLiveData<List<LocationResponse>> = MutableLiveData()
        val call = apiInterface.getLocations()
        call.enqueue(object : Callback<LocationResponse> {
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val locationResponse: LocationResponse? = response.body()
                        locationsList = locationResponse?.data as ArrayList<LocationResponse>
                        mutableLiveData.value = locationsList
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }


    //get categories details
    fun getCategoryDetails(requestObject: CategoryDetailRequest): LiveData<CategoryDetailResponse> {
        val mutableLiveData: MutableLiveData<CategoryDetailResponse> = MutableLiveData()
        val call = apiInterface.getCategoryDetails(requestObject)
        call.enqueue(object : Callback<CategoryDetailResponse> {
            override fun onResponse(
                call: Call<CategoryDetailResponse>,
                response: Response<CategoryDetailResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val categoryResponse: CategoryDetailResponse? = response.body()
                        Log.e("msg", categoryResponse?.status.toString())
//
//                        categoryList = categoryResponse?.data?.categories as ArrayList<Category>
                        mutableLiveData.value = categoryResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<CategoryDetailResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message + "")
            }
        })

        return mutableLiveData
    }


    //get home
    fun getHomeDetails(requestObject: HomeRequestModel): LiveData<HomeResponse> {
        val mutableLiveData: MutableLiveData<HomeResponse> = MutableLiveData()
        val call = apiInterface.gethomeDetails(requestObject)
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val homeResponse: HomeResponse? = response.body()
                        Log.e("msg", homeResponse?.status.toString())
//
//                        categoryList = categoryResponse?.data?.categories as ArrayList<Category>
                        mutableLiveData.value = homeResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message + "")
            }
        })

        return mutableLiveData
    }


    //get Location Provider details
    fun getLocationDetails(requestObject: LocationProviderRequest): LiveData<LocationProvoderResponse> {
        val mutableLiveData: MutableLiveData<LocationProvoderResponse> = MutableLiveData()
        val call = apiInterface.getLocationsDetails(requestObject)
        call.enqueue(object : Callback<LocationProvoderResponse> {
            override fun onResponse(
                call: Call<LocationProvoderResponse>,
                response: Response<LocationProvoderResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val locationProvoderResponse: LocationProvoderResponse? = response.body()
                        Log.e("msg", locationProvoderResponse?.status.toString())
//
//                        categoryList = categoryResponse?.data?.categories as ArrayList<Category>
                        mutableLiveData.value = locationProvoderResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<LocationProvoderResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message + "")
            }
        })

        return mutableLiveData
    }


    //get subcategories details
    fun getSubCategoryDetails(requestObject: SubCategoryRequest): LiveData<SubCategoryDetailResponse> {
        val mutableLiveData: MutableLiveData<SubCategoryDetailResponse> = MutableLiveData()
        val call = apiInterface.getSubCategoryDetails(requestObject)
        call.enqueue(object : Callback<SubCategoryDetailResponse> {
            override fun onResponse(
                call: Call<SubCategoryDetailResponse>,
                response: Response<SubCategoryDetailResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val subCategoryResponse: SubCategoryDetailResponse? = response.body()
                        Log.e("msg", subCategoryResponse?.status.toString())
//
//                        categoryList = categoryResponse?.data?.categories as ArrayList<Category>
                        mutableLiveData.value = subCategoryResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<SubCategoryDetailResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message + "")
            }
        })

        return mutableLiveData
    }

    //getproviderdetails
    fun getProviderDetails(
        requestObject: JSONObject
    ): LiveData<ProviderDetailResponse> {
        val mutableLiveData: MutableLiveData<ProviderDetailResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.getProviderDetails(requestBody)
        call.enqueue(object : Callback<ProviderDetailResponse> {
            override fun onResponse(
                call: Call<ProviderDetailResponse>,
                response: Response<ProviderDetailResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val providerDetailResponse: ProviderDetailResponse? = response.body()
                        if (response.body()?.statusCode == 200) {

                            mutableLiveData.value = providerDetailResponse
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<ProviderDetailResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //contact us
    fun ContactUs(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<ContactUsResponse> {
        val mutableLiveData: MutableLiveData<ContactUsResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.contactUs("Bearer " + mToken, "USER", requestBody)
        call.enqueue(object : Callback<ContactUsResponse> {
            override fun onResponse(
                call: Call<ContactUsResponse>,
                response: Response<ContactUsResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val contactUsResponse: ContactUsResponse? = response.body()
                        mutableLiveData.value = contactUsResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<ContactUsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //contact us
    fun UploadImage(
        authToken: String,
        mImagefile: File?
    ): LiveData<UpdateImageResponse> {
        val mutableLiveData: MutableLiveData<UpdateImageResponse> = MutableLiveData()

        val part = MultipartBody.Part.createFormData(
            "profileImage", mImagefile?.name, RequestBody.create(
                "image/jpeg".toMediaTypeOrNull(), mImagefile!!
            )
        )
        val call = apiInterface.UpdateImage("Bearer "+authToken,part)
        call?.enqueue(object : Callback<UpdateImageResponse?> {
            override fun onResponse(
                call: Call<UpdateImageResponse?>,
                response: Response<UpdateImageResponse?>
            ) {
                if (response.isSuccessful) {
                    try {
                        val updateImageResponse: UpdateImageResponse? = response.body()
                        if (response.body()?.statusCode == 200) {
                            mutableLiveData.value = updateImageResponse
                            AppPreferences.init(App.getAppContext()).putString(
                                Constants.PROFILE_IMAGE,
                                updateImageResponse?.data?.profileImage.toString()
                            )
                        }
                    } catch (e: Exception) {
                        Log.e("error",e.message+"")
                        e.printStackTrace()
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<UpdateImageResponse?>, t: Throwable) {
                Log.e("error",t.message+"")
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //forget pwd
    fun forgetPwd(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<ForgetPasswordResponse> {
        val mutableLiveData: MutableLiveData<ForgetPasswordResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.forgetPwd("Bearer " + mToken, requestBody)
        call.enqueue(object : Callback<ForgetPasswordResponse> {
            override fun onResponse(
                call: Call<ForgetPasswordResponse>,
                response: Response<ForgetPasswordResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val ForgetPasswordResponse: ForgetPasswordResponse? = response.body()
                        mutableLiveData.value = ForgetPasswordResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<ForgetPasswordResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }


    //change pwd
    fun changePwd(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<ChangePasswordResponse> {
        val mutableLiveData: MutableLiveData<ChangePasswordResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.changePwd("Bearer " + mToken, requestBody)
        call.enqueue(object : Callback<ChangePasswordResponse> {
            override fun onResponse(
                call: Call<ChangePasswordResponse>,
                response: Response<ChangePasswordResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val changePasswordResponse: ChangePasswordResponse? = response.body()
                        mutableLiveData.value = changePasswordResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }


    //updateProfile
    fun updateProfile(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<UpdateUserProfile> {
        val mutableLiveData: MutableLiveData<UpdateUserProfile> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.UpdateProfile("Bearer " + mToken, requestBody)
        call.enqueue(object : Callback<UpdateUserProfile> {
            override fun onResponse(
                call: Call<UpdateUserProfile>,
                response: Response<UpdateUserProfile>
            ) {
                if (response.isSuccessful) {
                    try {
                        val updateUserProfile: UpdateUserProfile? = response.body()
                        if (response.body()?.statusCode == 200) {

                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.EMAIL, updateUserProfile?.data?.email)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.USER_ID, updateUserProfile?.data?.id)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.F_NAME, updateUserProfile?.data?.fname)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.L_NAME, updateUserProfile?.data?.lname)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.TOKEN, updateUserProfile?.data?.token)
                            AppPreferences.init(App.getAppContext()).putString(
                                Constants.PROFILE_IMAGE,
                                updateUserProfile?.data?.profileImage.toString()
                            )
                        }
                        mutableLiveData.value = updateUserProfile
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<UpdateUserProfile>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //getwishlist
    fun getWishlist(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<WishlistResponse> {
        val mutableLiveData: MutableLiveData<WishlistResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.getwishlist("Bearer " + mToken, requestBody)
        call.enqueue(object : Callback<WishlistResponse> {
            override fun onResponse(
                call: Call<WishlistResponse>,
                response: Response<WishlistResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val wishlistResponse: WishlistResponse? = response.body()
                        mutableLiveData.value = wishlistResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<WishlistResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //addWishlist
    fun addWishlist(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<AddWishlistResponse> {
        val mutableLiveData: MutableLiveData<AddWishlistResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.addWishlist("Bearer " + mToken, requestBody)
        call.enqueue(object : Callback<AddWishlistResponse> {
            override fun onResponse(
                call: Call<AddWishlistResponse>,
                response: Response<AddWishlistResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        val addWishlistResponse: AddWishlistResponse? = response.body()
                        mutableLiveData.value = addWishlistResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<AddWishlistResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }


    //remove wishlist
    fun removeWishlist(
        mToken: String?,
        requestObject: JSONObject
    ): LiveData<RemoveWishlist> {
        val mutableLiveData: MutableLiveData<RemoveWishlist> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.removeWishlist("Bearer " + mToken, requestBody)
        call.enqueue(object : Callback<RemoveWishlist> {
            override fun onResponse(
                call: Call<RemoveWishlist>,
                response: Response<RemoveWishlist>
            ) {
                if (response.isSuccessful) {
                    try {
                        val removeWishlist: RemoveWishlist? = response.body()
                        mutableLiveData.value = removeWishlist
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<RemoveWishlist>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //send Otp
    fun sendOtp(
        requestObject: JSONObject
    ): LiveData<SendOtpResponse> {
        val mutableLiveData: MutableLiveData<SendOtpResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.sendOtp(requestBody)
        call.enqueue(object : Callback<SendOtpResponse> {
            override fun onResponse(call: Call<SendOtpResponse>, response: Response<SendOtpResponse>) {
                if (response.isSuccessful) {
                    try {
                        val sendOtpResponse: SendOtpResponse? = response.body()
                        if (response.body()?.statusCode == 200) {

                        }
                        mutableLiveData.value = sendOtpResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<SendOtpResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }


    //send Otp
    fun vaifyOtp(
        requestObject: JSONObject
    ): LiveData<VarifyOtpResponse> {
        val mutableLiveData: MutableLiveData<VarifyOtpResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.varifyOtp(requestBody)
        call.enqueue(object : Callback<VarifyOtpResponse> {
            override fun onResponse(call: Call<VarifyOtpResponse>, response: Response<VarifyOtpResponse>) {
                if (response.isSuccessful) {
                    try {
                        val VarifyOtpResponse: VarifyOtpResponse? = response.body()
                        if (response.body()?.statusCode == 200) {

                        }
                        mutableLiveData.value = VarifyOtpResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<VarifyOtpResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }

    //login
    fun login(
        requestObject: JSONObject
    ): LiveData<LoginResponse> {
        val mutableLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
        val requestBody: RequestBody =
            requestObject.toString().toRequestBody("application/json".toMediaType())
        val call = apiInterface.login(requestBody)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    try {
                        val loginResponse: LoginResponse? = response.body()
                        if (response.body()?.statusCode == 200) {

                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.EMAIL, loginResponse?.data?.email)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.USER_ID, loginResponse?.data?.id)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.F_NAME, loginResponse?.data?.fname)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.L_NAME, loginResponse?.data?.lname)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.TOKEN, loginResponse?.data?.token)
                            AppPreferences.init(App.getAppContext()).putString(
                                Constants.PROFILE_IMAGE,
                                loginResponse?.data?.profileImage.toString()
                            )
                        }
                        mutableLiveData.value = loginResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData
    }


    //Register
    fun register(
        username: String,
        password: String,
        fname: String,
        lname: String
    ): LiveData<RegisterResponse> {
        val mutableLiveData: MutableLiveData<RegisterResponse> = MutableLiveData()
        val username: RequestBody = RequestBody.create("text/plain".toMediaType(), username)
        val password: RequestBody = RequestBody.create("text/plain".toMediaType(), password)
        val fname: RequestBody = RequestBody.create("text/plain".toMediaType(), fname)
        val lname: RequestBody = RequestBody.create("text/plain".toMediaType(), lname)
        val part = MultipartBody.Part.createFormData(
            "profileImage", "", RequestBody.create(
                "multipart/form-data".toMediaType(), ""
            )
        )
        val call = apiInterface.signup(lname, username, password, fname, part)
        call!!.enqueue(object : Callback<RegisterResponse?> {
            override fun onResponse(
                call: Call<RegisterResponse?>,
                response: Response<RegisterResponse?>
            ) {
                if (response.isSuccessful) {
                    try {
                        val registerResponse: RegisterResponse? = response.body()
                        if (response.body()?.statusCode == 200) {

                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.EMAIL, registerResponse?.data?.email)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.USER_ID, registerResponse?.data?.id)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.F_NAME, registerResponse?.data?.fname)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.L_NAME, registerResponse?.data?.lname)
                            AppPreferences.init(App.getAppContext())
                                .putString(Constants.TOKEN, registerResponse?.data?.token)
                            AppPreferences.init(App.getAppContext()).putString(
                                Constants.PROFILE_IMAGE,
                                registerResponse?.data?.profileImage.toString()
                            )
                        }
                        mutableLiveData.value = registerResponse
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return mutableLiveData


    }
}





