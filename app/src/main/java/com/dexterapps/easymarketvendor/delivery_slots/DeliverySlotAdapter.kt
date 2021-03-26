package com.dexterapps.easymarketvendor.delivery_slots

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
import java.util.*

class DeliverySlotAdapter(private val slotList: List<delivery_slot_model>) :
    RecyclerView.Adapter<DeliverySlotAdapter.DeliveryViewHolder>() {

    var context: Context? = null;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val view = LayoutInflater.from(parent.context)

            .inflate(R.layout.item_delivery_slot, parent, false)
        context = parent.context
        return DeliveryViewHolder(view)

    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val slotList = slotList[position]

        holder.slotNumber.text = "" + (position + 1)
        holder.slotFromTime.text = slotList.slot_from_time
        holder.slotToTime.text = slotList.slot_to_time

        holder.slotFromTime.setOnClickListener {



         if(holder.ivCheck.isVisible) {
             val mTimePicker: TimePickerDialog
             val TimePicker: TimePickerDialog
             val mcurrentTime = Calendar.getInstance()
             val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
             val minute = mcurrentTime.get(Calendar.MINUTE)

             // From Time
             mTimePicker = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                 override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                     var AM_PM: String
                     if (hourOfDay < 12) {
                         AM_PM = "AM";
                     } else {
                         AM_PM = "PM";
                     }


                     holder.slotFromTime.setText(
                         String.format(
                             "%d : %d",
                             hourOfDay,
                             minute
                         ) + " " + AM_PM
                     )


                 }
             }, hour, minute, false)


             mTimePicker.show()
         }
        }
        holder.slotToTime.setOnClickListener {



         if(holder.ivCheck.isVisible) {
             val mTimePicker: TimePickerDialog
             val TimePicker: TimePickerDialog
             val mcurrentTime = Calendar.getInstance()
             val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
             val minute = mcurrentTime.get(Calendar.MINUTE)

             // From Time
             mTimePicker = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                 override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                     var AM_PM: String
                     if (hourOfDay < 12) {
                         AM_PM = "AM";
                     } else {
                         AM_PM = "PM";
                     }


                     holder.slotToTime.setText(
                         String.format(
                             "%d : %d",
                             hourOfDay,
                             minute
                         ) + " " + AM_PM
                     )


                 }
             }, hour, minute, false)


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


        }

    }

    override fun getItemCount() = slotList.size


    class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val slotNumber: TextView = itemView.findViewById(R.id.slot_number)
        val slotFromTime: TextView = itemView.findViewById(R.id.slot_from_time)
        val slotToTime: TextView = itemView.findViewById(R.id.slot_to_time)
        val ivEdit: ImageView = itemView.findViewById(R.id.iv_edit)
        val ivCheck: ImageView = itemView.findViewById(R.id.iv_check)


    }
}