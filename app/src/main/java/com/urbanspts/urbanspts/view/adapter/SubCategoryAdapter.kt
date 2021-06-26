package com.urbanspts.urbanspts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.CategoryDetailModel.SubcategoriesData
import kotlinx.android.synthetic.main.item_categories_listing.view.*
import kotlinx.android.synthetic.main.item_popular_categories.view.*

class SubCategoryAdapter(
    val activity: FragmentActivity?,
    categoryListing: ArrayList<SubcategoriesData>
): RecyclerView.Adapter<SubCategoryAdapter.MyViewHolderFav>() {
    var image:String?=null
    private var subCatListing: ArrayList<SubcategoriesData>? = categoryListing
    var onItemClick: ((pos: Int, view: View) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderFav {
        return MyViewHolderFav(LayoutInflater.from(activity).inflate(R.layout.item_popular_categories, parent, false))
    }

    override fun getItemCount(): Int {
        return  subCatListing?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        holder.itemView.sub_cat_name.text=subCatListing?.get(position)?.name
        image=subCatListing?.get(position)?.photo
        if(image !==null)
        {
            activity?.let {
                Glide.with(it)
                    .load(image)
                    .into(holder.itemView.sub_cat_image)
            }
        } else {
            holder.itemView.iv_category.setImageResource(R.drawable.restaurant2)
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