package com.urbanspts.urbanspts.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_locations.view.*
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.LocationModel.Data
import com.urbanspts.urbanspts.model.LocationModel.LocationResponse
import com.urbanspts.urbanspts.view.activity.LocationDetailActivity
import com.urbanspts.urbanspts.view.activity.SubCategoryDetails
import com.urbanspts.urbanspts.view.adapter.LocationAdapter
import com.urbanspts.urbanspts.viewmodel.LocationViewModel

class Location_fragment : Fragment() {
    private  var viewModel: LocationViewModel?=null
    private var categoriesAdapter: LocationAdapter? = null
    private var locationList: ArrayList<Data> = ArrayList()
    internal var rootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_locations, container, false)
        initView()
        return rootView;
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        getLocaionsApi()
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>locations adapter>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    }

    private fun getLocaionsApi() {

        viewModel?.getLocations()?.observe(
            this,
            object : Observer<List<LocationResponse>?> {
                override fun onChanged(@Nullable locationResponse: List<LocationResponse>?) {
                    locationList= locationResponse as ArrayList<Data>
                    if(locationList.size>0)
                    {
                        setAdapter()
                    }

                }
            })

    }

    //.....................................set location adapter.............................

    private fun setAdapter() {
        rootView?.rv_locations?.layoutManager= LinearLayoutManager(context)
        rootView?.rv_locations?.adapter= LocationAdapter(activity,locationList)
        (rootView?.rv_locations?.adapter as LocationAdapter).onItemClick = { pos, view ->


            var mLocationId=locationList?.get(pos)?.id.toString()
            var mLocationName=locationList?.get(pos)?.name
            val intent = Intent(activity, LocationDetailActivity::class.java)
            intent.putExtra("category_id",mLocationId)
            intent.putExtra("category_name",mLocationName)
            startActivity(intent)


        }
        rootView?.rv_locations?.setNestedScrollingEnabled(false)
    }
}