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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.model.categoriesModel.Category
import com.urbanspts.urbanspts.view.activity.CategoryDetailPage
import com.urbanspts.urbanspts.view.adapter.Categories_Adapter
import com.urbanspts.urbanspts.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_categories.view.*

class Categories_fragment : Fragment() {
    private  var viewModel: CategoryViewModel?=null
    private var categoriesAdapter: Categories_Adapter? = null
    private var categoryList: ArrayList<Category> = ArrayList()
    internal var rootView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView= inflater.inflate(R.layout.fragment_categories, container, false)
        initView()
        return  rootView;
    }

    private fun initView() {
//        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>Categories adapter>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        categoriesAdapter = Categories_Adapter(activity, categoryList)
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        getcategories();
    }

    private fun getcategories() {
        viewModel?.getCategories()?.observe(
            this,
            object : Observer<List<Category>?> {
                override fun onChanged(@Nullable category: List<Category>?) {
                    categoryList= category as ArrayList<Category>
                    rootView?.rv_categories?.layoutManager= GridLayoutManager(context,2) as RecyclerView.LayoutManager?
                    rootView?.rv_categories?.adapter= Categories_Adapter(activity, categoryList)
                    (rootView?.rv_categories?.adapter as Categories_Adapter).onItemClick = { pos, view ->


                        var mCategoryid=categoryList?.get(pos)?.id.toString()
                        var mCategoryName=categoryList?.get(pos)?.name
                        val intent = Intent(activity, CategoryDetailPage::class.java)
                        intent.putExtra("category_id",mCategoryid)
                        intent.putExtra("category_name",mCategoryName)
                        activity?.startActivity(intent)


                    }
                    rootView?.rv_categories?.setNestedScrollingEnabled(false)


                }
            })
    }

    }
