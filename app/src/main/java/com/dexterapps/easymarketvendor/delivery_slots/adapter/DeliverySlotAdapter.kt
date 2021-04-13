package com.dexterapps.easymarketvendor.delivery_slots.adapter

import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.delivery_slots.interfaces.SlotInterface
import com.dexterapps.easymarketvendor.delivery_slots.model.Data
import com.dexterapps.easymarketvendor.delivery_slots.model.delivery_slot_model
import java.util.*
import kotlin.math.abs

class DeliverySlotAdapter(
    private val slotList: List<Data>,
    private val slotInterface: SlotInterface
) :
    RecyclerView.Adapter<DeliverySlotAdapter.DeliveryViewHolder>() {

    var context: Context? = null;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val view = LayoutInflater.from(parent.context)

            .inflate(R.layout.item_delivery_slot, parent, false)
        context = parent.context
        return DeliveryViewHolder(
            view
        )

    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val slotList = slotList[position]
        var updatedF: String = slotList.t_from
        var updatedT: String = slotList.t_to
        holder.slotNumber.text = "" + (position + 1)
        holder.slotFromTime.text = slotList.t_from
        holder.slotToTime.text = slotList.t_to

        holder.slotFromTime.setOnClickListener {


            if (holder.ivCheck.isVisible) {
                val mTimePicker: TimePickerDialog
                val TimePicker: TimePickerDialog
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
                val minute = mcurrentTime.get(Calendar.MINUTE)

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


                        updatedF = String.format(
                            "%d:%d",
                            myHour,
                            minute
                        ) + " " + AM_PM
                        holder.slotFromTime.text = String.format(
                            "%d:%d",
                            myHour,
                            minute
                        ) + " " + AM_PM
                    }, hour, minute, false
                )


                mTimePicker.show()
            }
        }
        holder.slotToTime.setOnClickListener {


            if (holder.ivCheck.isVisible) {
                val mTimePicker: TimePickerDialog
                val TimePicker: TimePickerDialog
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
                val minute = mcurrentTime.get(Calendar.MINUTE)

                // From Time
                mTimePicker =
                    TimePickerDialog(
                        context,
                        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                            var myHour = hourOfDay

                            var AM_PM: String = if (hourOfDay < 12) {
                                "AM";
                            } else {
                                "PM";
                            }
                            if (hourOfDay > 12) {
                                myHour = abs(hourOfDay - 12)

                            }
                            holder.slotToTime.text = String.format(
                                "%d:%d",
                                myHour,
                                minute
                            ) + " " + AM_PM
                            updatedT = String.format(
                                "%d:%d",
                                myHour,
                                minute
                            ) + " " + AM_PM
                        }, hour, minute, false
                    )


                mTimePicker.show()
            }
        }

        holder.ivEdit.setOnClickListener {
            holder.ivEdit.visibility = View.GONE
            holder.ivCheck.visibility = View.VISIBLE


        }
        holder.ivCheck.setOnClickListener {

            holder.ivCheck.visibility = View.GONE
            holder.ivEdit.visibility = View.VISIBLE
            slotInterface.updateSlot(
                slotList.id,
                slotList.t_date,
                slotList.t_day,
                updatedF,
                updatedT
            )

        }
        holder.ivDelete.setOnClickListener {
            slotInterface.deleteSlot(slotList.id)
        }

    }

    override fun getItemCount() = slotList.size


    class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val slotNumber: TextView = itemView.findViewById(R.id.slot_number)
        val slotFromTime: TextView = itemView.findViewById(R.id.slot_from_time)
        val slotToTime: TextView = itemView.findViewById(R.id.slot_to_time)
        val ivEdit: ImageView = itemView.findViewById(R.id.iv_edit)
        val ivCheck: ImageView = itemView.findViewById(R.id.iv_check)
        val ivDelete: ImageView = itemView.findViewById(R.id.iv_delete)


    }
}