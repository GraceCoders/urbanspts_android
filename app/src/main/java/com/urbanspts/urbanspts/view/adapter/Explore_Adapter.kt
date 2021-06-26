package com.urbanspts.urbanspts.view.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.HomeModel.Normal
import kotlinx.android.synthetic.main.item_explore.view.*

class Explore_Adapter(
    exploreSpotList: ArrayList<Normal>,
    val activity: FragmentActivity?
) : RecyclerView.Adapter<Explore_Adapter.MyViewHolderFav>() {
    var coverimage:String?=null
    var description:String?=null
    var mLogo:String?=null
    private var providerListing: ArrayList<Normal>? = exploreSpotList
    var onItemClick: ((pos: Int, view: View) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderFav {
        return MyViewHolderFav(LayoutInflater.from(activity).inflate(R.layout.item_explore, parent, false))
    }

    override fun getItemCount(): Int {
        return  providerListing?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        holder.itemView.tv_title.text=providerListing?.get(position)?.providerId?.name
        holder.itemView.tv_location.text=providerListing?.get(position)?.providerId?.address
        description=providerListing?.get(position)?.providerId?.description
        if(!description.isNullOrEmpty())
        {
            holder.itemView?.tv_description?.text= Html.fromHtml(providerListing?.get(position)?.providerId?.description)
        }
        coverimage=providerListing?.get(position)?.providerId?.coverPhoto
        mLogo=providerListing?.get(position)?.providerId?.photo
        if(coverimage !==null)
        {
            activity?.let {
                Glide.with(it)
                    .load(coverimage)
                    .into(holder.itemView.img_cover)
            }
        } else {
            holder.itemView.img_cover.setImageResource(R.drawable.back_bg)
        }

        if(mLogo !==null)
        {
            activity?.let {
                Glide.with(it)
                    .load(mLogo)
                    .into(holder.itemView.img_logo)
            }
        } else {
            holder.itemView.img_logo.setImageResource(R.drawable.restaurant2)
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