package com.dexterapps.easymarketvendor

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.deliveryStatus.DeliveryStatusFragment
import com.dexterapps.easymarketvendor.delivery_slots.delivery_slots
import com.dexterapps.easymarketvendor.home.DashboardTabFragment

import com.dexterapps.easymarketvendor.howItWorks.AboutusFragment
import com.dexterapps.easymarketvendor.howItWorks.HowItWorksFragment
import com.dexterapps.easymarketvendor.howItWorks.TAndCFragment
import com.dexterapps.easymarketvendor.myDeliveryPerson.MyDeliveryPersonFragment
import com.dexterapps.easymarketvendor.myProfile.MyProfile
import com.dexterapps.easymarketvendor.offerCreation.ShowOfferFragment
import com.dexterapps.easymarketvendor.orderHistory.OrderHistoryFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_btn = findViewById(R.id.nav_btn)

        nav_back_btn = findViewById(R.id.nav_back_btn)
        logo = findViewById(R.id.logo)
        center_name = findViewById(R.id.name)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        mFragmentManager = supportFragmentManager
        loadFragment(DashboardTabFragment(), Variables.TAG_DASHBOARD)

        initClicks()
    }

    private fun initClicks() {
        nav_btn.setOnClickListener() {
            if (!drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.openDrawer(GravityCompat.START)
            }


        }
    }

    fun onNavClick(view: View) {
        when (view.id) {

            R.id.ll_my_delivery_person -> {

                loadFragment(MyDeliveryPersonFragment(), Variables.TAG_MY_DELIVERY_PERSON)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_delivery_status -> {

                loadFragment(DeliveryStatusFragment(), Variables.TAG_DELIVERY_STATUS)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_delivery_slot -> {

                loadFragment(delivery_slots(), Variables.TAG_DELIVERY_SLOT)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_order_history -> {

                loadFragment(OrderHistoryFragment(), Variables.TAG_ORDER_HISTORY)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_offer_creation -> {

                loadFragment(ShowOfferFragment(), Variables.TAG_SHOW_OFFER)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dashboard -> {

                loadFragment(DashboardTabFragment(), Variables.TAG_DASHBOARD)
            }
            R.id.ll_my_profile -> {

                loadFragment(MyProfile(), Variables.TAG_MY_PROFILE)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_how_it_works -> {

                loadFragment(HowItWorksFragment(), Variables.TAG_HOW_IT_WORKS)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_aboutus -> {

                loadFragment(AboutusFragment(), Variables.TAG_ABOUTUS)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_t_and_c -> {

                loadFragment(TAndCFragment(), Variables.TAG_T_AND_C)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {

        if (mFragmentManager.backStackEntryCount > 0) {
            if (mFragmentManager.backStackEntryCount == 1) {
                if (doubleBackToExitPressedOnce) {
                    finish()
                    return
                }

                this.doubleBackToExitPressedOnce = true
                snack(logo, "Please click BACK again to exit")
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)


            } else {
//                center_name.text = ""
//                nav_btn.visibility = View.VISIBLE
//                nav_back_btn.visibility = View.GONE
//
//                logo.visibility = View.VISIBLE
//                center_name.visibility = View.GONE

                mFragmentManager.popBackStackImmediate();
            }
        } else {
            super.onBackPressed();
        }

    }

    companion object {
        lateinit var nav_btn: ImageView
        lateinit var nav_back_btn: ImageView
        lateinit var logo: ImageView
        lateinit var center_name: TextView
        lateinit var drawer: DrawerLayout
        lateinit var navigationView: NavigationView
        lateinit var mFragmentManager: FragmentManager

        fun snack(layoutView: View, msg: String) {
            val snackBarView = Snackbar.make(layoutView, msg, Snackbar.LENGTH_LONG)
            val view = snackBarView.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM
            view.layoutParams = params
//                view.background = ContextCompat.getDrawable(context,R.drawable.custom_drawable) // for custom background
            snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackBarView.show()

        }

        fun loadFragment(fragment: Fragment, tag: String) {

            closeDrawer()
            val fragmentA: Fragment? = mFragmentManager.findFragmentByTag(tag)
            if (fragmentA == null) {

                mFragmentManager.beginTransaction().replace(R.id.frag_host, fragment)
                    .addToBackStack(tag)
                    .commit()
            }


        }

        private fun closeDrawer() {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            }
        }

        fun hideShow(name: String, showName: Boolean) {

            if (showName) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                center_name.text = name
                nav_btn.visibility = View.GONE
                nav_back_btn.visibility = View.VISIBLE

                logo.visibility = View.GONE
                center_name.visibility = View.VISIBLE
            } else {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                center_name.text = ""
                nav_btn.visibility = View.VISIBLE
                nav_back_btn.visibility = View.GONE

                logo.visibility = View.VISIBLE
                center_name.visibility = View.GONE
            }

        }

        fun back() {
            Log.d(Variables.TAG, "back: ")

            if (mFragmentManager.backStackEntryCount > 0) {
                if (mFragmentManager.backStackEntryCount == 1) {

                } else {

                    mFragmentManager.popBackStackImmediate();
                }
            } else {
                mFragmentManager.popBackStack()
            }
        }


    }


}