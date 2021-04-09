package com.dexterapps.easymarketvendor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var context: Context? = null
   var business_category : TextView? = null


    val businessCategoryArray = arrayOf("Business Category", "Shubham Hule", "Maiz Bagwala")
   lateinit var business_category_click : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        business_category = findViewById(R.id.business_category)
business_category_click = findViewById(R.id.business_category_click)


            val sp_business_category: Spinner? = findViewById(R.id.sp_business_category)

            sp_business_category!!.setOnItemSelectedListener(this)

            val businessCategory: ArrayAdapter<String> = ArrayAdapter<String>(
                applicationContext!!,
                android.R.layout.simple_spinner_item,
                businessCategoryArray
            )

            businessCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_business_category.setAdapter(businessCategory)


   business_category_click.setOnClickListener {

       sp_business_category.performClick()

   }
        sp_business_category.setSelection(0)










        findViewById<TextView>(R.id.btnRegister).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        business_category!!.text = businessCategoryArray[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}