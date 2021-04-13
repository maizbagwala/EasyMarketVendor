package com.dexterapps.easymarketvendor.offerCreation

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class OfferCreation : Fragment() {


   lateinit var fromDate : TextView
   lateinit var toDate : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_offer_creation, container, false)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        MainActivity.hideShow(Variables.NAME_OFFER_CREATION, true)

        fromDate = root.findViewById(R.id.oc_date_from)
        toDate = root.findViewById(R.id.oc_date_to)


        val calendar = Calendar.getInstance()

        //getting current day,month and year.
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)





 fromDate.setOnClickListener {

     val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener
     { view, year, monthOfYear, dayOfMonth ->


         fromDate.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)

     }, year, month, day)
     datePickerDialog.show()

 }

        toDate.setOnClickListener {

            val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth ->

                toDate.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
            }, year, month, day)

            datePickerDialog.show()

        }

        return root
    }


}