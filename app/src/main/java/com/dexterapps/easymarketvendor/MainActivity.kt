package com.dexterapps.easymarketvendor

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.delivery_slots.delivery_slots


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_btn = findViewById(R.id.nav_btn)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        mFragmentManager = supportFragmentManager
//        loadFragment(HomeFragment(), Variables.TAG_HOME_FRAGMENT)

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
            R.id.nav_order_history -> {
                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_delivery_slots -> {

                loadFragment(fragment = delivery_slots(), Variables.TAG_DELIVERY_SLOT)


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
        fun loadFragment(fragment: Fragment, tag: String) {
            mFragmentManager.beginTransaction().replace(R.id.frag_host, fragment)
                .addToBackStack(tag)
                .commit()

            if (drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.closeDrawer(Gravity.LEFT)
            }
        }
    }

}