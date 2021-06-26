package com.urbanspts.urbanspts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.LocationModel.Data
import com.urbanspts.urbanspts.model.LocationModel.LocationResponse
import kotlinx.android.synthetic.main.item_categories_listing.view.*
import kotlinx.android.synthetic.main.item_locations_listing.view.*

class LocationAdapter(
    val activity: FragmentActivity?,
    locationList: ArrayList<Data>
): RecyclerView.Adapter<LocationAdapter.MyViewHolderFav>() {
    var image:String?=null
    private var locationList: ArrayList<Data> = locationList
    var onItemClick: ((pos: Int, view: View) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderFav {
        return MyViewHolderFav(LayoutInflater.from(activity).inflate(R.layout.item_locations_listing, parent, false))
    }

    override fun getItemCount(): Int {
        return  locationList.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        holder.itemView.tv_title.text=locationList?.get(position)?.title
        holder.itemView.tv_states.text=locationList?.get(position)?.name
        image=locationList?.get(position)?.photo
        if(image !==null)
        {
            activity?.let {
                Glide.with(it)
                    .load(image)
                    .into(holder.itemView.img_cover)
            }
        } else {
            holder.itemView.iv_category.setImageResource(R.drawable.back_bg)
        }

    }
    inner class MyViewHolderFav(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            onItemClick?.invoke(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}