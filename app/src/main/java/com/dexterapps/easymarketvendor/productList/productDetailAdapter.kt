package com.dexterapps.easymarketvendor.productList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R

class productDetailAdapter(val prdouctDetails : ArrayList<productDetailModel>)  : RecyclerView.Adapter<productDetailAdapter.ProductDetailsViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailsViewHolder {
        return ProductDetailsViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.item_product_details, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ProductDetailsViewHolder, position: Int) {
       val productDetailsList = prdouctDetails[position]

        holder.productType.text = productDetailsList.productType
        holder.productMrp.text =productDetailsList.product_mrp
        holder.productDiscount.text = productDetailsList.product_discount
        holder.productStock.text = productDetailsList.product_stock




    }

    override fun getItemCount() = prdouctDetails.size


    class ProductDetailsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        val productType : TextView = itemView.findViewById(R.id.product_type)
        val productMrp : TextView = itemView.findViewById(R.id.product_mrp)
        val productDiscount : TextView = itemView.findViewById(R.id.product_discount)
        val productStock : TextView = itemView.findViewById(R.id.product_stoke)



    }
}