package com.dexterapps.easymarketvendor

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onNavClick(view: View) {
        when (view.id) {
            R.id.nav_order_history -> {
                Toast.makeText(this, "order history clicked", Toast.LENGTH_SHORT).show()
            }

        }
    }
}