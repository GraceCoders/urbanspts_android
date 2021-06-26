package com.urbanspts.urbanspts.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.Gps
import com.urbanspts.urbanspts.model.HomeModel.CategoriesData
import com.urbanspts.urbanspts.model.HomeModel.HomeResponse
import com.urbanspts.urbanspts.model.HomeModel.Normal
import com.urbanspts.urbanspts.model.HomeModel.Popular
import com.urbanspts.urbanspts.view.activity.CategoryDetailPage
import com.urbanspts.urbanspts.view.activity.ProviderDetailActivity
import com.urbanspts.urbanspts.view.adapter.*
import com.urbanspts.urbanspts.viewmodel.HomeViewModel
import com.urbanspts.urbanspts.viewmodel.ProviderDetailViewModel
import kotlinx.android.synthetic.main.activity_whishlist.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment()  {
    var gps: Gps? = null
    var current_lat:Double?=null
    var current_long:Double?=null
    private  var viewModel: HomeViewModel?=null
    private var categoryList: ArrayList<CategoriesData> = ArrayList()
    private var popularspotList: ArrayList<Popular> = ArrayList()
    private var exploreSpotList : ArrayList<Normal> = ArrayList()
    internal var rootView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView= inflater.inflate(R.layout.fragment_home, container, false)
        initView()
        return  rootView;
    }

    private fun initView() {

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        getLocations()
        getHomeApiData()
    }

    //...............................get api home data...................................

    private fun getHomeApiData() {
        rootView?.clHome?.visibility = View.VISIBLE
        viewModel?.gethomeDetails(current_lat,current_long)?.observe(
            this,
            object : Observer<HomeResponse> {
                override fun onChanged(@Nullable homeResponse: HomeResponse) {
                    rootView?.clHome?.visibility = View.GONE
                        categoryList = homeResponse.data.categoriesData as ArrayList<CategoriesData>
                        popularspotList = homeResponse.data.populars as ArrayList<Popular>
                        exploreSpotList = homeResponse.data.normals as ArrayList<Normal>
                        setCategoryAdapter()
                        setNormalAdapter()
                    if (popularspotList.size > 0) {
                           setPopularAdapter()
                    } else {
                         ll_popular_spot.visibility=View.GONE
                    }

                        Log.e("size",categoryList.size.toString()+popularspotList.size.toString()+exploreSpotList.size.toString())
                }
            })
    }

//......................................set popular spot adapter..............................................
    private fun setPopularAdapter() {
        rootView?.rv_popular_spots?.layoutManager= LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL ,false)
        rootView?.rv_popular_spots?.adapter= Spot_Adapter(activity,popularspotList)
        (rootView?.rv_popular_spots?.adapter as Spot_Adapter).onItemClick = { pos, view ->


            var mProviderId=popularspotList?.get(pos)?.providerId.id.toString()
            var mProviderName=popularspotList?.get(pos)?.providerId.name
            val intent = Intent(activity, ProviderDetailActivity::class.java)
            intent.putExtra("provider_id", mProviderId)
            intent.putExtra("provider_name", mProviderName)
            startActivity(intent)


        }
        rootView?.rv_popular_spots?.setNestedScrollingEnabled(false)

    }

    //................................set normal adapter.......................................

    private fun setNormalAdapter() {

        rootView?.rv_explore?.layoutManager= LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL ,false)
        rootView?.rv_explore?.adapter= Explore_Adapter(exploreSpotList,activity)
        (rootView?.rv_explore?.adapter as Explore_Adapter).onItemClick = { pos, view ->


            var mProviderId=exploreSpotList?.get(pos)?.providerId.id.toString()
            var mProviderName=exploreSpotList?.get(pos)?.providerId.name
            val intent = Intent(activity, ProviderDetailActivity::class.java)
            intent.putExtra("provider_id", mProviderId)
            intent.putExtra("provider_name", mProviderName)
            startActivity(intent)


        }
        rootView?.rv_explore?.setNestedScrollingEnabled(false)

    }

    //.............................set popular category adapter..............................

    private fun setCategoryAdapter() {

        rootView?.rv_popular_categories?.layoutManager= LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL ,false)
        rootView?.rv_popular_categories?.adapter= Popular_Adapter(activity,categoryList)
        (rootView?.rv_popular_categories?.adapter as Popular_Adapter).onItemClick = { pos, view ->


            var mCategoryid=categoryList?.get(pos)?.id.toString()
            var mCategoryName=categoryList?.get(pos)?.name
            val intent = Intent(activity, CategoryDetailPage::class.java)
            intent.putExtra("category_id",mCategoryid)
            intent.putExtra("category_name",mCategoryName)
            activity?.startActivity(intent)


        }
        rootView?.rv_popular_categories?.setNestedScrollingEnabled(false)

    }


    //.......................get current locations.......................................

    private fun getLocations() {
        gps = Gps(activity)

        if (gps!!.canGetLocation()) {
            current_lat = gps!!.getLatitude()
            current_long = gps!!.getLongitude()
            Log.e("current_lat", current_lat.toString() + "")
            Log.e("current_long", current_long.toString() + "")
        } else {
            gps!!.showSettingsAlert()
        }

    }
}