package com.dexterapps.easymarketvendor.delivery_slots

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R

class delivery_slot_adapter (val slot_list:List<delivery_slot_model>): RecyclerView.Adapter<delivery_slot_adapter.deliveryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): deliveryViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery_slot, parent, false)

return  deliveryViewHolder(view)

    }

    override fun onBindViewHolder(holder: deliveryViewHolder, position: Int) {
        val slotlist= slot_list[position]

        holder.slot_number.text = slotlist.slot_number
        holder.slot_from_time.text = slotlist.slot_from_time
        holder.slot_to_time.text = slotlist.slot_to_time

    }

    override fun getItemCount() = slot_list.size


    class  deliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val slot_number : TextView = itemView.findViewById(R.id.slot_number)
        val slot_from_time : TextView = itemView.findViewById(R.id.slot_from_time)
        val slot_to_time : TextView = itemView.findViewById(R.id.slot_to_time)



    }
}