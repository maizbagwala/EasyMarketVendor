package com.dexterapps.easymarketvendor.addproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R


class AddProductAdapter() :
    RecyclerView.Adapter<AddProductAdapter.AddProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductViewHolder {
        return AddProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AddProductViewHolder, position: Int) {

        val types = arrayOf("GM", "KG")
        val adapterType = ArrayAdapter(
            holder.itemView.context,
            R.layout.my_simple_spinner_item,
            types
        )
        holder.typeSpinner.adapter = adapterType

        val discountType = arrayOf("%", "$")
        val adapterDisc = ArrayAdapter(
            holder.itemView.context,
            R.layout.my_simple_spinner_item,
            discountType
        )
        holder.discountSpinner.adapter = adapterDisc


    }


    override fun getItemCount(): Int {
        return 3
    }

    class AddProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etType: EditText = itemView.findViewById(R.id.et_type)
        val etMrp: EditText = itemView.findViewById(R.id.et_mrp)
        val etDiscount: EditText = itemView.findViewById(R.id.et_discount)
        val etDescription: EditText = itemView.findViewById(R.id.et_description)
        val btnDelete: ImageView = itemView.findViewById(R.id.iv_delete)
        val inStock: SwitchCompat = itemView.findViewById(R.id.switch_inStock)
        val typeSpinner: Spinner = itemView.findViewById(R.id.spinner_type)
        val discountSpinner: Spinner = itemView.findViewById(R.id.spinner_discount)

    }
}