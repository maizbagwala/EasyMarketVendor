package com.dexterapps.easymarketvendor.register

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.register.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var business_category: TextView? = null
    private lateinit var categoryViewModel: RegisterViewModel
    private var category: MutableList<String>? = null
    var list = arrayListOf<String>()
    var listId = arrayListOf<Int>()
    var selectedId: Int = 0

    lateinit var fName: EditText
    lateinit var lName: EditText
    lateinit var phone: EditText
    lateinit var email: EditText
    lateinit var address: EditText
    lateinit var shopName: EditText
    lateinit var password: EditText
    lateinit var cPassword: EditText

    //    private val businessCategoryArray = arrayOf("Business Category", "Shubham Hule", "Maiz Bagwala")
    private lateinit var business_category_click: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fName = findViewById(R.id.et_fname)
        lName = findViewById(R.id.et_lname)
        phone = findViewById(R.id.et_mobile)
        email = findViewById(R.id.et_email)
        address = findViewById(R.id.et_address)
        shopName = findViewById(R.id.et_business_name)
        password = findViewById(R.id.et_password)
        cPassword = findViewById(R.id.et_c_password)
        categoryViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        business_category = findViewById(R.id.business_category)
        business_category_click = findViewById(R.id.business_category_click)

        getSpinnerData()



        findViewById<TextView>(R.id.btnRegister).setOnClickListener {
            Utill.showLoader(this)
            categoryViewModel.registerVendor(
                fName.text.toString(),
                lName.text.toString(),
                phone.text.toString(),
                email.text.toString(),
                address.text.toString(),
                selectedId.toString(),
                shopName.text.toString(),
                password.text.toString(),
                cPassword.text.toString()
            )!!.observe(this, Observer {
                Utill.cancelLoader()
                Log.d(Variables.TAG, "onCreate: $it")
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            })

        }
    }

    private fun getSpinnerData() {
        Utill.showLoader(this)
        categoryViewModel.getRegCategory()?.observe(this, Observer {
            Log.d(Variables.TAG, "getSpinnerData: $it")
            for (i in it.business_category) {
                list.add(i.name)
                listId.add(i.id)
            }


            val spBusinessCategory: Spinner = findViewById(R.id.sp_business_category)

            spBusinessCategory.onItemSelectedListener = this

            val businessCategory: ArrayAdapter<String> = ArrayAdapter<String>(
                applicationContext!!,
                android.R.layout.simple_spinner_item,
                list
            )

            businessCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spBusinessCategory.adapter = businessCategory


            business_category_click.setOnClickListener {

                spBusinessCategory.performClick()

            }
            spBusinessCategory.setSelection(0)
            Utill.cancelLoader()
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        business_category!!.text = list[position]
        selectedId = listId[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}