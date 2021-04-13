package com.dexterapps.easymarketvendor.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.login.viewModel.LoginViewModel
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.RegisterActivity
import com.dexterapps.easymarketvendor.verification.VerificationActivity
import com.dexterapps.easymarketvendor.config.Loader
import com.dexterapps.easymarketvendor.config.Utill

class LoginActivity : AppCompatActivity() {
    private var btnLogin: TextView? = null
    private var imgBack: ImageView? = null
    private var edtPhone: EditText? = null
    private var edtPassword: EditText? = null
    private var model: LoginViewModel? = null
    private var loader: Loader? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        onClick()
    }


    private fun init() {
        btnLogin = findViewById(R.id.btnLogin)
        imgBack = findViewById(R.id.imgBack)
        edtPhone = findViewById(R.id.edtPhone)
        loader = Loader(this)
        model = ViewModelProvider(this).get(LoginViewModel::class.java)
        edtPassword = findViewById(R.id.edtPassword)

    }

    private fun onClick() {
        btnLogin!!.setOnClickListener {
            if (Utill.isEmptyString(edtPhone!!.text.toString().trim() )|| Utill.isEmptyString( edtPassword!!.text.toString())) {
                Toast.makeText(this, "please enter mobile no", Toast.LENGTH_SHORT).show()
            } else {
//                getLoginResponse()
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

        }
//        imgBack!!.setOnClickListener {
//            MainActivity.header_fragment!!.visibility = View.GONE
//            MainActivity.header_home!!.visibility = View.VISIBLE
//            finish()
//        }
    }

    private fun getLoginResponse() {
        loader!!.show()
        model!!.getLogin()!!.observe(this, {
            loader!!.cancel()
            Log.e("Data", it.toString())
            val intent = Intent(this@LoginActivity, VerificationActivity::class.java)
            startActivity(intent)

        })
    }

}