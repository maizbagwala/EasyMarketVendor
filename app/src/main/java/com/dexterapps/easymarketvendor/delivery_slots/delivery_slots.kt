package com.dexterapps.easymarketvendor.delivery_slots

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
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
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_delivery_slots, container, false)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }

        MainActivity.hideShow(Variables.NAME_DELIVERY_SLOT, true)

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

        TimePicker = TimePickerDialog(
            context,
            { view, hourOfDay, minute ->
                val AM_PM: String = if (hourOfDay < 12) {
                    "AM";
                } else {
                    "PM";
                }

                to_time.text = String.format("%d : %d", hourOfDay, minute) + " " + AM_PM
            }, hour, minute, false
        )



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

        }
        monday.setOnClickListener {
            test(it)
        }

        Thursday.setOnClickListener {
            test(it)
        }
        Wednesday.setOnClickListener {
            test(it)
        }
        Tuesday.setOnClickListener {
            test(it)
        }
        Friday.setOnClickListener {
            test(it)
        }

        Saturday.setOnClickListener {
            test(it)
        }


        // past View
        val rv_delivery_slots_past: RecyclerView = root.findViewById(R.id.rv_delivery_slots_past)


        val slot_list = ArrayList<delivery_slot_model>()

//        slot_list.add(delivery_slot_model(1, "1", from_time.text.toString(), to_time.text.toString()))

        val slotAdapter = DeliverySlotAdapter(slot_list)
        rv_delivery_slots_past.layoutManager =
            LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_delivery_slots_past.adapter = slotAdapter

        val addBtn: AppCompatButton


        addBtn = root.findViewById(R.id.add_btn)

        addBtn.setOnClickListener {


            if (from_time.text.toString().equals("") || to_time.text.toString().equals("")) {

                MainActivity.Snack(root,"Fields are required")
            } else {
                slot_list.add(
                    delivery_slot_model(
                        4,
                        "3",
                        from_time.text.toString(),
                        to_time.text.toString()
                    )
                )
                slotAdapter.notifyDataSetChanged()
            }
        }







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


        sunday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        monday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        Thursday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        Wednesday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        Tuesday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        Friday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        Saturday.setTextColor(ContextCompat.getColor(context!!, R.color.black))
//            Log.d("idhere", "test: "+sunday.id,)
        if (view.id == sunday.id) {

            sunday.setBackgroundResource(R.drawable.delivery_slots_time_click)
            sunday.setTextColor(ContextCompat.getColor(context!!, R.color.white))

        } else if (view.id == monday.id) {
            monday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            monday.setTextColor(ContextCompat.getColor(context!!, R.color.white))

        } else if (view.id == Thursday.id) {

            Thursday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            Thursday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


        } else if (view.id == Wednesday.id) {

            Wednesday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            Wednesday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


        } else if (view.id == Tuesday.id) {

            Tuesday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            Tuesday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


        } else if (view.id == Friday.id) {

            Friday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            Friday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


        } else if (view.id == Saturday.id) {

            Saturday.setBackgroundResource(R.drawable.delivery_slots_time_click)

            Saturday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


        }

    }

}