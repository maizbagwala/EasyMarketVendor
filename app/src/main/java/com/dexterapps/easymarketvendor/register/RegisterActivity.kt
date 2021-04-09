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
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.register.model.businessCategoryModel
import com.dexterapps.easymarketvendor.register.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var context: Context? = null
    var business_category: TextView? = null
    private lateinit var categoryViewModel: RegisterViewModel
    private var category: MutableList<String>? = null
    var list = arrayListOf<String>()

    private val businessCategoryArray = arrayOf("Business Category", "Shubham Hule", "Maiz Bagwala")
    private lateinit var business_category_click: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        categoryViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        business_category = findViewById(R.id.business_category)
        business_category_click = findViewById(R.id.business_category_click)


        getSpinnerData()











        findViewById<TextView>(R.id.btnRegister).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
    }

    private fun getSpinnerData() {
        val dialog: Dialog = Dialog(context!!).apply {
            val wlmp: WindowManager.LayoutParams = window!!.attributes
            wlmp.gravity = Gravity.CENTER_HORIZONTAL
            window!!.attributes = wlmp
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setTitle(null)
            setCancelable(false)
            setOnCancelListener(null)
            val view = LayoutInflater.from(context).inflate(
                R.layout.custom_loader, null
            )
            setContentView(view)
        }
        dialog.show()
        categoryViewModel.getRegCategory()?.observe(this, {
            Log.d(Variables.TAG, "getSpinnerData: $it")
            for (i in it.business_category) {
                list.add(i.name)
            }
            val sp_business_category: Spinner = findViewById(R.id.sp_business_category)

            sp_business_category.onItemSelectedListener = this

            val businessCategory: ArrayAdapter<String> = ArrayAdapter<String>(
                applicationContext!!,
                android.R.layout.simple_spinner_item,
                list
            )

            businessCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_business_category.adapter = businessCategory


            business_category_click.setOnClickListener {

                sp_business_category.performClick()

            }
            sp_business_category.setSelection(0)
            dialog.dismiss()
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        business_category!!.text = businessCategoryArray[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}