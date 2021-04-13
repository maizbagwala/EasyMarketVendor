package com.dexterapps.easymarketvendor.productList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import com.squareup.picasso.Picasso

class productListAdapter(val productList: ArrayList<ProductListModel>) :
    RecyclerView.Adapter<productListAdapter.ProdyctListVeiwHolder>() {

    lateinit var rvDetails: RecyclerView


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdyctListVeiwHolder {

        return ProdyctListVeiwHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ProdyctListVeiwHolder, position: Int) {
        val product = productList[position]

        val list = arrayListOf<productDetailModel>(
            productDetailModel("5 kg ", "45$", "$", "in"),
            productDetailModel("5 kg ", "45$", "$", "out"),
            productDetailModel("5 kg ", "45$", "$", "out"),

        )
        val adapter =
            productDetailAdapter(list)

        Picasso.get().load(productList[position].product_image)
            .placeholder(R.drawable.maggi).into(holder.productImage)

        holder.productName.text = product.product_name
        holder.rvDetail.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.rvDetail.adapter = adapter

    }

    override fun getItemCount() = productList.size


    class ProdyctListVeiwHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val rvDetail: RecyclerView = itemView.findViewById(R.id.rv_product_details)
    }

}