package com.dexterapps.easymarketvendor

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.GoalRow
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.HomeFragment
import com.dexterapps.easymarketvendor.howItWorks.AboutusFragment
import com.dexterapps.easymarketvendor.howItWorks.HowItWorksFragment
import com.dexterapps.easymarketvendor.howItWorks.TAndCFragment
import com.dexterapps.easymarketvendor.myProfile.MyProfile
import com.dexterapps.easymarketvendor.offerCreation.OfferCreation
import com.dexterapps.easymarketvendor.orderHistory.OrderHistoryFragment
import com.google.android.material.navigation.NavigationView

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
        loadFragment(HomeFragment(), Variables.TAG_HOME_FRAGMENT)

        initClicks()
    }

    private fun initClicks() {
        nav_btn.setOnClickListener() {
            if (!drawer!!.isDrawerOpen(GravityCompat.START)) {
                drawer!!.openDrawer(GravityCompat.START)
            }


        }
    }

    fun onNavClick(view: View) {
        when (view.id) {
            R.id.ll_order_history -> {

                loadFragment(OrderHistoryFragment(), Variables.TAG_ORDER_HISTORY)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.ll_offer_creation -> {

                loadFragment(OfferCreation(), Variables.TAG_OFFER_CREATION)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dashboard -> {

                loadFragment(HomeFragment(), Variables.TAG_ORDER_HISTORY)
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


    companion object {
        lateinit var nav_btn: ImageView
        lateinit var nav_back_btn: ImageView
        lateinit var logo: ImageView
        lateinit var center_name: TextView
        lateinit var drawer: DrawerLayout
        lateinit var navigationView: NavigationView
        lateinit var mFragmentManager: FragmentManager
        fun loadFragment(fragment: Fragment, tag: String,id: Int = R.id.frag_host) {
            closeDrawer()

            if (!tag.equals(Variables.TAG_DASHBOARD)) {
                hideShow(tag)


            }
            mFragmentManager.beginTransaction().replace(R.id.frag_host, fragment)
                .addToBackStack(tag)
                .commit()

        }

        fun closeDrawer() {
            if (drawer!!.isDrawerOpen(GravityCompat.START)) {
                drawer!!.closeDrawer(GravityCompat.START)
            }
        }

        fun hideShow(name: String) {
            var displayName: String? = null

            when (name) {
                Variables.TAG_ORDER_HISTORY -> {
                    displayName = "Order History"

                }

                Variables.TAG_MY_PROFILE -> {
                    displayName = "My Profile"

                }
                Variables.TAG_OFFER_CREATION -> {
                    displayName = "Offer Creation"

                }

                Variables.TAG_HOW_IT_WORKS -> {
                    displayName = "How Its Work"

                }
                Variables.TAG_ABOUTUS -> {
                    displayName = "About Us"

                }
                Variables.TAG_T_AND_C -> {
                    displayName = "Terms And Conditions"

                }
            }
            center_name.text = displayName
            nav_btn.visibility = View.GONE
            nav_back_btn.visibility = View.VISIBLE

            logo.visibility = View.GONE
            center_name.visibility = View.VISIBLE
        }

        fun back() {

          
        }


    }


}