package com.dexterapps.easymarketvendor.home.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.ViewOrderFragment
import com.dexterapps.easymarketvendor.home.model.ViewDeliveryOrderStatusModel


class ViewDeliveryOrderStatusAdapter(newOrderList: ArrayList<ViewDeliveryOrderStatusModel>) :
    RecyclerView.Adapter<ViewDeliveryOrderStatusAdapter.NewOrderViewHolder>(),
    AdapterView.OnItemSelectedListener {
    var context: Context? = null
    var tv_delivery_person_name: TextView? = null
    val deliveryPersonArray = arrayOf("Akshay Gupta", "Shubham Hule", "Maiz Bagwala")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderViewHolder {
        context = parent.context
        return NewOrderViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.item_delivery_order,
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: NewOrderViewHolder, position: Int) {
        holder.btn_view.setOnClickListener {
            MainActivity.loadFragment(ViewOrderFragment(), Variables.TAG_VIEW_ORDER)
        }
        holder.tv_assign_delivery_boy.setOnClickListener {
            var dialog: Dialog? = null;
            dialog = Dialog(context!!)
            dialog.setContentView(R.layout.item_assign_delivery_boy)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)


            val iv_cancel: ImageView? = dialog.findViewById(R.id.iv_cancel)
            val ll_delivery_person: LinearLayout? = dialog.findViewById(R.id.ll_delivery_person)
            val tv_assign_delivery_boy: TextView? = dialog.findViewById(R.id.tv_assign_delivery_boy)
            tv_delivery_person_name = dialog.findViewById(R.id.tv_delivery_person_name)
            iv_cancel!!.setOnClickListener {

                dialog.dismiss()
            }
            tv_assign_delivery_boy!!.setOnClickListener {

                dialog.dismiss()
            }



            dialog.show()
            val sp_delivery_person: Spinner? =
                dialog.findViewById(R.id.sp_delivery_person)
            sp_delivery_person!!.setOnItemSelectedListener(this)


            val aaDeliveryPerson: ArrayAdapter<*> = ArrayAdapter<Any?>(
                context!!,
                android.R.layout.simple_spinner_item,
                deliveryPersonArray
            )
            aaDeliveryPerson.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_delivery_person.setAdapter(aaDeliveryPerson)

            ll_delivery_person!!.setOnClickListener {
                sp_delivery_person.performClick()
            }
            sp_delivery_person.setSelection(0)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    class NewOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var btn_view: TextView = itemView.findViewById(R.id.btn_view)
        var tv_assign_delivery_boy: TextView = itemView.findViewById(R.id.tv_assign_delivery_boy)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        tv_delivery_person_name!!.text = deliveryPersonArray[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}