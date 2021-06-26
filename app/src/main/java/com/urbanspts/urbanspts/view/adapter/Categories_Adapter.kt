package com.urbanspts.urbanspts.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.categoriesModel.Category
import kotlinx.android.synthetic.main.item_categories_listing.view.*


class Categories_Adapter(
    val activity: FragmentActivity?,

    categoryList: ArrayList<Category>
): RecyclerView.Adapter<Categories_Adapter.MyViewHolderFav>() {
    var image:String?=null
    private var categoryList: ArrayList<Category>? = categoryList
    var onItemClick: ((pos: Int, view: View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderFav {
        return MyViewHolderFav(LayoutInflater.from(activity).inflate(R.layout.item_categories_listing, parent, false))
    }

    override fun getItemCount(): Int {
      return categoryList!!.size

    }



    override fun onBindViewHolder(holder: MyViewHolderFav, position: Int) {
        Log.e("nameee",categoryList?.get(position)?.name+"")
        holder.itemView.tv_name.text=categoryList?.get(position)?.name
        image=categoryList?.get(position)?.photo
        if(image !==null)
        {
            activity?.let {
                Glide.with(it)
                    .load(image)
                    .into(holder.itemView.iv_category)
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
