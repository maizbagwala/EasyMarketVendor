package com.dexterapps.easymarketvendor.delivery_slots

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.dexterapps.easymarketvendor.R
import java.util.*

class delivery_slots : Fragment() {


    lateinit var from_time: TextView
    lateinit var to_time: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_delivery_slots, container, false)

        val mTimePicker: TimePickerDialog
        val TimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        from_time=root.findViewById(R.id.from_time)
        to_time=root.findViewById(R.id.to_time)

   // From Time
        mTimePicker = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                var AM_PM : String
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }


                from_time.setText(String.format("%d : %d", hourOfDay, minute) + " " + AM_PM )


            }
        }, hour, minute, false)

        from_time.setOnClickListener {



            mTimePicker.show()
        }

        // To time

        TimePicker = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                var AM_PM : String
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }

                to_time.setText(String.format("%d : %d", hourOfDay, minute)+ " " + AM_PM )




            }
        }, hour, minute, false)



        to_time.setOnClickListener {

            TimePicker.show()
        }





    return  root
    }
}