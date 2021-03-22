package com.dexterapps.easymarketvendor.delivery_slots

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R

class DeliverySlotAdapter(private val slotList: List<delivery_slot_model>) :
    RecyclerView.Adapter<DeliverySlotAdapter.DeliveryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delivery_slot, parent, false)

        return DeliveryViewHolder(view)

    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val slotList = slotList[position]

        holder.slotNumber.text = slotList.slot_number
        holder.slotFromTime.text = slotList.slot_from_time
        holder.slotToTime.text = slotList.slot_to_time

    }

    override fun getItemCount() = slotList.size


    class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val slotNumber: TextView = itemView.findViewById(R.id.slot_number)
        val slotFromTime: TextView = itemView.findViewById(R.id.slot_from_time)
        val slotToTime: TextView = itemView.findViewById(R.id.slot_to_time)


    }
}