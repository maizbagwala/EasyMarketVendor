package com.dexterapps.easymarketvendor.delivery_slots

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import java.util.*
import kotlin.collections.ArrayList

class delivery_slots : Fragment() {


    lateinit var from_time: TextView
    lateinit var to_time: TextView
    lateinit var sunday: AppCompatButton
    lateinit var monday: AppCompatButton
    lateinit var Tuesday: AppCompatButton
    lateinit var Wednesday: AppCompatButton
    lateinit var Thursday: AppCompatButton
    lateinit var Friday: AppCompatButton
    lateinit var Saturday: AppCompatButton
    lateinit var root: View

    @SuppressLint("ResourceAsColor", "WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_delivery_slots, container, false)

        val mTimePicker: TimePickerDialog
        val TimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        from_time = root.findViewById(R.id.from_time)
        to_time = root.findViewById(R.id.to_time)

        // From Time
        mTimePicker = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                var AM_PM: String
                if (hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }


                from_time.setText(String.format("%d : %d", hourOfDay, minute) + " " + AM_PM)


            }
        }, hour, minute, false)

        from_time.setOnClickListener {


            mTimePicker.show()
        }

        // To time

        TimePicker = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                var AM_PM: String
                if (hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }

                to_time.setText(String.format("%d : %d", hourOfDay, minute) + " " + AM_PM)


            }
        }, hour, minute, false)



        to_time.setOnClickListener {

            TimePicker.show()
        }


        // Day  click set

        sunday = root.findViewById(R.id.sunday)
        monday = root.findViewById(R.id.monday)
        Thursday = root.findViewById(R.id.Thursday)
        Wednesday = root.findViewById(R.id.Wednesday)
        Tuesday = root.findViewById(R.id.Tuesday)
        Friday = root.findViewById(R.id.Friday)
        Saturday = root.findViewById(R.id.Saturday)

        sunday.setOnClickListener {
            test(it)
//            sunday.setBackgroundResource(R.drawable.delivery_slots_time_click)
//
//            monday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)
//
//            //text color
//
//            sunday.setTextColor(getResources().getColor(R.color.white))
//            monday.setTextColor(getResources().getColor(R.color.black))
//            Thursday.setTextColor(getResources().getColor(R.color.black))
//            Wednesday.setTextColor(getResources().getColor(R.color.black))
//            Tuesday.setTextColor(getResources().getColor(R.color.black))
//            Friday.setTextColor(getResources().getColor(R.color.black))
//            Saturday.setTextColor(getResources().getColor(R.color.black))
        }
        monday.setOnClickListener {
            test(it)
//            monday.setBackgroundResource(R.drawable.delivery_slots_time_click)
//            sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)
//
//            //text color
//
//            sunday.setTextColor(getResources().getColor(R.color.black))
//            monday.setTextColor(getResources().getColor(R.color.white))
//            Thursday.setTextColor(getResources().getColor(R.color.black))
//            Wednesday.setTextColor(getResources().getColor(R.color.black))
//            Tuesday.setTextColor(getResources().getColor(R.color.black))
//            Friday.setTextColor(getResources().getColor(R.color.black))
//            Saturday.setTextColor(getResources().getColor(R.color.black))
        }

        Thursday.setOnClickListener {
            monday.setBackgroundResource(R.drawable.delivery_slots_bg)
            sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Thursday.setBackgroundResource(R.drawable.delivery_slots_time_click)
            Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)

            //text color

            sunday.setTextColor(getResources().getColor(R.color.black))
            monday.setTextColor(getResources().getColor(R.color.black))
            Thursday.setTextColor(getResources().getColor(R.color.white))
            Wednesday.setTextColor(getResources().getColor(R.color.black))
            Tuesday.setTextColor(getResources().getColor(R.color.black))
            Friday.setTextColor(getResources().getColor(R.color.black))
            Saturday.setTextColor(getResources().getColor(R.color.black))
        }
        Wednesday.setOnClickListener {
            monday.setBackgroundResource(R.drawable.delivery_slots_bg)
            sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Wednesday.setBackgroundResource(R.drawable.delivery_slots_time_click)
            Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)

            //text color

            sunday.setTextColor(getResources().getColor(R.color.black))
            monday.setTextColor(getResources().getColor(R.color.black))
            Thursday.setTextColor(getResources().getColor(R.color.black))
            Wednesday.setTextColor(getResources().getColor(R.color.white))
            Tuesday.setTextColor(getResources().getColor(R.color.black))
            Friday.setTextColor(getResources().getColor(R.color.black))
            Saturday.setTextColor(getResources().getColor(R.color.black))
        }
        Tuesday.setOnClickListener {
            monday.setBackgroundResource(R.drawable.delivery_slots_bg)
            sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Tuesday.setBackgroundResource(R.drawable.delivery_slots_time_click)
            Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)
            //text color

            sunday.setTextColor(getResources().getColor(R.color.black))
            monday.setTextColor(getResources().getColor(R.color.black))
            Thursday.setTextColor(getResources().getColor(R.color.black))
            Wednesday.setTextColor(getResources().getColor(R.color.black))
            Tuesday.setTextColor(getResources().getColor(R.color.white))
            Friday.setTextColor(getResources().getColor(R.color.black))
            Saturday.setTextColor(getResources().getColor(R.color.black))
        }
        Friday.setOnClickListener {
            monday.setBackgroundResource(R.drawable.delivery_slots_bg)
            sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Friday.setBackgroundResource(R.drawable.delivery_slots_time_click)
            Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)

            //text color

            sunday.setTextColor(getResources().getColor(R.color.black))
            monday.setTextColor(getResources().getColor(R.color.black))
            Thursday.setTextColor(getResources().getColor(R.color.black))
            Wednesday.setTextColor(getResources().getColor(R.color.black))
            Tuesday.setTextColor(getResources().getColor(R.color.black))
            Friday.setTextColor(getResources().getColor(R.color.white))
            Saturday.setTextColor(getResources().getColor(R.color.black))
        }

        Saturday.setOnClickListener {
            monday.setBackgroundResource(R.drawable.delivery_slots_bg)
            sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
            Saturday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            //text color

            sunday.setTextColor(getResources().getColor(R.color.black))
            monday.setTextColor(getResources().getColor(R.color.black))
            Thursday.setTextColor(getResources().getColor(R.color.black))
            Wednesday.setTextColor(getResources().getColor(R.color.black))
            Tuesday.setTextColor(getResources().getColor(R.color.black))
            Friday.setTextColor(getResources().getColor(R.color.black))
            Saturday.setTextColor(getResources().getColor(R.color.white))
        }


        // past View

        val rv_delivery_slots_past: RecyclerView = root.findViewById(R.id.rv_delivery_slots_past)


        val slot_list = ArrayList<delivery_slot_model>()

        slot_list.add(delivery_slot_model(1, "1", "12:00 PM", "12:00 AM"))
        slot_list.add(delivery_slot_model(2, "2", "12:00 PM", "12:00 AM"))
        slot_list.add(delivery_slot_model(3, "3", "12:00 PM", "12:00 AM"))

        val slotAdapter: delivery_slot_adapter = delivery_slot_adapter(slot_list)

        rv_delivery_slots_past.layoutManager =
            LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_delivery_slots_past.adapter = slotAdapter




        return root
    }

    fun test(view: View) {

        monday.setBackgroundResource(R.drawable.delivery_slots_bg)
        sunday.setBackgroundResource(R.drawable.delivery_slots_bg)
        Thursday.setBackgroundResource(R.drawable.delivery_slots_bg)
        Wednesday.setBackgroundResource(R.drawable.delivery_slots_bg)
        Tuesday.setBackgroundResource(R.drawable.delivery_slots_bg)
        Friday.setBackgroundResource(R.drawable.delivery_slots_bg)
        Saturday.setBackgroundResource(R.drawable.delivery_slots_bg)
//            Log.d("idhere", "test: "+sunday.id,)
        if (view.id == sunday.id) {

            sunday.setBackgroundResource(R.drawable.delivery_slots_time_click)
            sunday.setTextColor(ContextCompat.getColor(context!!, R.color.white))

        } else if (view.id == monday.id) {
            monday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            monday.setTextColor(ContextCompat.getColor(context!!, R.color.white))

        }

    }

}