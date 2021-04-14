package com.dexterapps.easymarketvendor.offerCreation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.offerCreation.interfaces.OfferInterface
import com.dexterapps.easymarketvendor.offerCreation.model.Data

class OfferAdapter(val offerList: ArrayList<Data>, val offerInterface: OfferInterface) :
    RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coupon_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val model: Data = offerList[position]
        holder.tvName.text = model.coupon_name
        holder.tvCode.text = model.code
        holder.tvOff.text = if (model.discount_type == "amount") {
            "${model.discount} $"
        } else {
            "${model.discount} %"
        }

        holder.tvDelete.setOnClickListener {
            offerInterface.deleteOffer(model.id)
        }

        holder.ivEdit.setOnClickListener {
            offerInterface.updateOffer(model)
        }
    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvCode: TextView = itemView.findViewById(R.id.tv_code)
        val tvOff: TextView = itemView.findViewById(R.id.tv_off)
        val tvDelete: TextView = itemView.findViewById(R.id.tv_delete)
        val ivEdit: ImageView = itemView.findViewById(R.id.iv_edit)
    }
}