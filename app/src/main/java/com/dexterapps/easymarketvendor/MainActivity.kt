package com.dexterapps.easymarketvendor

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.HomeFragment
import com.dexterapps.easymarketvendor.orderHistory.OrderHistoryFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_btn = findViewById(R.id.nav_btn)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        mFragmentManager = supportFragmentManager

        loadFragment(HomeFragment(), Variables.TAG_HOME_FRAGMENT)


        nav_btn.setOnClickListener {
            if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.openDrawer(Gravity.LEFT)
            } else {
                drawer.closeDrawer(Gravity.LEFT)
            }
        }
    }

    fun onNavClick(view: View) {
        when (view.id) {
            R.id.ll_order_history -> {

                loadFragment(OrderHistoryFragment(), Variables.TAG_ORDER_HISTORY)
//                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT)
        } else {

            super.onBackPressed()
        }
    }

    companion object {
        lateinit var nav_btn: ImageView
        lateinit var drawer: DrawerLayout
        lateinit var navigationView: NavigationView
        lateinit var mFragmentManager: FragmentManager
        fun loadFragment(fragment: Fragment, tag: String, id: Int = R.id.frag_host) {
            mFragmentManager.beginTransaction().replace(id, fragment)
                .addToBackStack(tag)
                .commit()
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            }
        }

    }
}