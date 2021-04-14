package com.dexterapps.easymarketvendor.delivery_slots

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.delivery_slots.adapter.DeliverySlotAdapter
import com.dexterapps.easymarketvendor.delivery_slots.interfaces.SlotInterface
import com.dexterapps.easymarketvendor.delivery_slots.model.Data
import com.dexterapps.easymarketvendor.delivery_slots.viewModel.DeliverySlotViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class delivery_slots : Fragment() {

    lateinit var slotViewModel: DeliverySlotViewModel
    var slot_list = ArrayList<Data>()
    lateinit var slotAdapter: DeliverySlotAdapter
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
    var daySelected = "sun"

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor", "WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_delivery_slots, container, false)
        slotViewModel =
            ViewModelProvider(this).get(DeliverySlotViewModel::class.java)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }

        MainActivity.hideShow(Variables.NAME_DELIVERY_SLOT, true)
        val currentDateTime = LocalDateTime.now()


        val mTimePicker: TimePickerDialog
        val TimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        from_time = root.findViewById(R.id.from_time)
        to_time = root.findViewById(R.id.to_time)

        // From Time
        mTimePicker = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                var myHour = hourOfDay
                val AM_PM: String = if (hourOfDay < 12) {
                    "AM";
                } else {
                    "PM";
                }

                if (hourOfDay > 12) {
                    myHour = abs(hourOfDay - 12)

                }


                from_time.text = String.format("%d:%d", myHour, minute) + " " + AM_PM
            }, hour, minute, false
        )

        from_time.setOnClickListener {


            mTimePicker.show()
        }

        // To time

        TimePicker = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                var myHour = hourOfDay
                val AM_PM: String = if (hourOfDay < 12) {
                    "AM";
                } else {
                    "PM";
                }
                if (hourOfDay > 12) {
                    myHour = abs(hourOfDay - 12)

                }


                to_time.text = String.format("%d:%d", myHour, minute) + " " + AM_PM
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


//        slot_list.add(delivery_slot_model(1, "1", from_time.text.toString(), to_time.text.toString()))

        slotAdapter =
            DeliverySlotAdapter(
                slot_list, object : SlotInterface {
                    override fun deleteSlot(id: Int) {
                        Utill.showLoader(context!!)
                        slotViewModel.deleteSlot(id)!!
                            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                                Utill.cancelLoader()
                                Log.d(TAG, "deleteSlot: $it")
                                MainActivity.snack(root, it.message)
                                getSlot()
                            })

                    }

                    override fun updateSlot(
                        tId: Int,
                        t_date: String,
                        t_day: String,
                        t_from: String,
                        t_to: String
                    ) {
                        Utill.showLoader(context!!)
                        slotViewModel.updateSlot(57, tId, t_date, t_day, t_from, t_to)
                            ?.observe(viewLifecycleOwner,
                                androidx.lifecycle.Observer {
                                    Utill.cancelLoader()
                                    Log.d(TAG, "updateSlot: $it")
                                    MainActivity.snack(root, it.message)
                                    getSlot()
                                })
                    }
                }
            )
        rv_delivery_slots_past.layoutManager =
            LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_delivery_slots_past.adapter = slotAdapter

        getSlot()
        val addBtn: AppCompatButton = root.findViewById(R.id.add_btn)

        addBtn.setOnClickListener {


            if (from_time.text.toString() == "" || to_time.text.toString() == "") {

                MainActivity.snack(root, "Fields are required")
            } else {
                Utill.showLoader(context!!)
                slotViewModel.createSlot(
                    57,
                    currentDateTime.format(
                        DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy"
                        )
                    ),
                    daySelected,
                    from_time.text.toString(),
                    to_time.text.toString()
                )!!.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    Utill.cancelLoader()
                    Log.d(TAG, "onCreateView: $it")
                    MainActivity.snack(root, it.message)
                    getSlot()
                })
            }
        }







        return root
    }

    private fun getSlot() {
        Utill.showLoader(context!!)
        slotViewModel.getSlot(57)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Utill.cancelLoader()
            Log.d(TAG, "onCreateView: ${it.data}")
            slot_list.clear()
            for (i in it.data) {
                slot_list.add(i)
            }
            slotAdapter.notifyDataSetChanged()

        })

    }

    private fun test(view: View) {

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
        when (view.id) {
            sunday.id -> {
                daySelected = "sun"

                sunday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                sunday.setTextColor(ContextCompat.getColor(context!!, R.color.white))

            }
            monday.id -> {
                daySelected = "mon"

                monday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                monday.setTextColor(ContextCompat.getColor(context!!, R.color.white))

            }
            Thursday.id -> {
                daySelected = "tue"

                Thursday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                Thursday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


            }
            Wednesday.id -> {
                daySelected = "wed"

                Wednesday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                Wednesday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


            }
            Tuesday.id -> {
                daySelected = "thu"

                Tuesday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                Tuesday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


            }
            Friday.id -> {
                daySelected = "fri"

                Friday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                Friday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


            }
            Saturday.id -> {
                daySelected = "sat"

                Saturday.setBackgroundResource(R.drawable.delivery_slots_time_click)
                Saturday.setTextColor(ContextCompat.getColor(context!!, R.color.white))


            }
        }

    }

}