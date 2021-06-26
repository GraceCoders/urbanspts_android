package com.urbanspts.urbanspts.view.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.urbanspts.urbanspts.R
import com.urbanspts.urbanspts.controller.utills.App
import com.urbanspts.urbanspts.controller.utills.AppPreferences
import com.urbanspts.urbanspts.controller.utills.Constants
import com.urbanspts.urbanspts.model.categoriesModel.CategoryResponse
import com.urbanspts.urbanspts.view.adapter.HomeVPAdapter
import com.urbanspts.urbanspts.view.fragment.Categories_fragment
import com.urbanspts.urbanspts.view.fragment.HomeFragment
import com.urbanspts.urbanspts.view.fragment.Location_fragment
import com.urbanspts.urbanspts.view.fragment.Profile_fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_navigation_bar_user.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), DrawerLayout.DrawerListener, View.OnClickListener{
    private var adapterViewPager: HomeVPAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        initView()

    }
    //>>>>>>>>>>>>>>>>>>>>>>>>setting bottom navigation using view pager>>>>>>.>>>>>>>>>>>>
    private fun initView() {

        nav_menu.setOnClickListener(this)
        ll_wishlist.setOnClickListener(this)
        ll_contact_us.setOnClickListener(this)
        ll_change_pwd.setOnClickListener(this)
        ll_logout.setOnClickListener(this)
        close.setOnClickListener(this)
//        ll_contact_us.setOnClickListener(this)
//        ll_logout.setOnClickListener(this)
//        ll_lan.setOnClickListener(this)
//        ll_serach_view.setOnClickListener(this)

        val menuView =
            bottom_navigation.getChildAt(0) as BottomNavigationMenuView

        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            val activeLabel =
                item.findViewById<View>(R.id.largeLabel)
            (activeLabel as? TextView)?.setPadding(0, 0, 0, 0)
        }


        val flList: MutableList<FrameLayout> = ArrayList()
        flList.add(fl_home)
        flList.add(fl_categories)
        flList.add(fl_location)
        flList.add(fl_profile)

        val fragList: MutableList<Fragment> =
            ArrayList()
        fragList.add(HomeFragment())
        fragList.add(Categories_fragment())
        fragList.add(Location_fragment())
        fragList.add(Profile_fragment())


        adapterViewPager = HomeVPAdapter(supportFragmentManager, flList, fragList)
        vp_main.setOffscreenPageLimit(4)
        vp_main.setAdapter(adapterViewPager)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>bottom navigation menu>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val menuNav: Menu = bottom_navigation.getMenu()
            val menuItem = menuNav.findItem(R.id.nav_home)
            when (item.itemId) {
                R.id.nav_home -> {
                    vp_main.setCurrentItem(0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_categories -> {
                    vp_main.setCurrentItem(1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_location -> {
                    vp_main.setCurrentItem(2)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profile-> {
                    vp_main.setCurrentItem(3)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>side bar menu>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    override fun onDrawerStateChanged(newState: Int) {

    }
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }
    override fun onDrawerClosed(drawerView: View) {

    }

    override fun onDrawerOpened(drawerView: View) {

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.nav button click to open the drawer>>>>>>>>>>>>>>>>>>>>>>>>
    @SuppressLint("WrongConstant")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.nav_menu -> {
                drawer_layout.openDrawer(Gravity.END)
            }
            R.id.close -> {
                drawer_layout.closeDrawer(Gravity.END)
            }
            R.id.ll_logout ->

            {

                AppPreferences.init(App.getAppContext())
                    .putString(Constants.USERNAME, "")
                AppPreferences.init(App.getAppContext())
                    .putString(Constants.USER_ID, "")
                AppPreferences.init(App.getAppContext())
                    .putString(Constants.USERNAME, "")
                AppPreferences.init(App.getAppContext())
                    .putString(Constants.TOKEN, "")
                AppPreferences.init(App.getAppContext()).putString(
                    Constants.PROFILE_IMAGE,
                    "")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
//            R.id.ll_profile ->
//            {
//                val intent = Intent(this, ProfileActivity::class.java)
//                startActivity(intent)
//            }
            R.id.ll_contact_us ->
            {
                val intent = Intent(this, ContactUsActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_change_pwd ->
            {
                val intent = Intent(this, ChangePasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_wishlist ->
            {
                val intent = Intent(this, WhishlistActivity::class.java)
                startActivity(intent)
            }
        }

    }




    //................................hide keyboard on touch.................................................
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus
        val ret = super.dispatchTouchEvent(ev)
        if (view is EditText) {
            val w = currentFocus
            val scrcoords = IntArray(2)
            w!!.getLocationOnScreen(scrcoords)
            val x = ev.rawX + w.left - scrcoords[0]
            val y = ev.rawY + w.top - scrcoords[1]
            if (ev.action == MotionEvent.ACTION_UP
                && (x < w.left || x >= w.right || y < w.top || y > w.bottom)
            ) {
                hideSoftKeyboard(this)
            }
        }
        return ret
    }
}
