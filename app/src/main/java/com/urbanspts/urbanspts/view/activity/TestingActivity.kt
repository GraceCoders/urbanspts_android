package com.urbanspts.urbanspts.view.activity

import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.categoriesModel.Category
import com.urbanspts.urbanspts.view.adapter.Categories_Adapter
import com.urbanspts.urbanspts.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_categories.*


class TestingActivity : AppCompatActivity() {
    private  var viewModel: CategoryViewModel?=null
    private var categoriesAdapter: Categories_Adapter? = null
    private var categoryList: ArrayList<Category> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_categories)
        getDeals()
    }

    private fun getDeals() {
        categoriesAdapter = Categories_Adapter(this, categoryList)
       viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        rv_categories?.layoutManager= GridLayoutManager(this,2) as RecyclerView.LayoutManager?
        rv_categories?.adapter= Categories_Adapter(this,categoryList)
        (rv_categories?.adapter as Categories_Adapter).onItemClick = { pos, view ->


            //            val intent = Intent(context, JobDetailActivity::class.java)
//            activity?.startActivity(intent)


        }
        rv_categories?.setNestedScrollingEnabled(false)
       getcategories();
    }

    private fun getcategories() {
        viewModel?.getCategories()?.observe(
            this,
            object : Observer<List<Category>?> {
                override fun onChanged(@Nullable category: List<Category>?) {
                    Log.e("sizeeee",category?.get(2)?.name.toString()+"")
                    categoryList= category as ArrayList<Category>
                    rv_categories?.adapter= Categories_Adapter(this@TestingActivity,categoryList)

                }
            })
    }

    }

