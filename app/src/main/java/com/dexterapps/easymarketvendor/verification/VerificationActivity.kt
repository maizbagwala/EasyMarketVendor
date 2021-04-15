package com.dexterapps.easymarketvendor.verification

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dexterapps.easymarketvendor.register.RegisterActivity
import com.dexterapps.easymarketvendor.R

class VerificationActivity : AppCompatActivity() {
    private var btnVerify: TextView? = null
    private var imgBack: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        init()
        onClick()
    }

    private fun init() {
        btnVerify = findViewById(R.id.btnVerify)
        imgBack = findViewById(R.id.imgBack)
    }

    private fun onClick() {
        btnVerify!!.setOnClickListener {
            val intent = Intent(this@VerificationActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        imgBack!!.setOnClickListener {
            finish()
        }
    }

}