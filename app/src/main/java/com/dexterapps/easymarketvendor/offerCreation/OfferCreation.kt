package com.dexterapps.easymarketvendor.offerCreation

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.offerCreation.viewModel.OfferCreationViewModel
import java.util.*

class OfferCreation : Fragment() {


    private lateinit var fromDate: TextView
    private lateinit var toDate: TextView
    private lateinit var etOfferCode: EditText
    private lateinit var etOfferName: EditText
    private lateinit var etMinOfferValue: EditText
    private lateinit var etMaxDis: EditText
    private lateinit var etDis: EditText
    private lateinit var rbg: RadioGroup
    var disType = "percent"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_offer_creation, container, false)
        val offerCreationViewModel = ViewModelProvider(this).get(OfferCreationViewModel::class.java)
        etOfferCode = root.findViewById(R.id.et_offer_code)
        etOfferName = root.findViewById(R.id.et_offer_name)
        etMinOfferValue = root.findViewById(R.id.et_min_offer_value)
        etMaxDis = root.findViewById(R.id.et_max_dis)
        etDis = root.findViewById(R.id.et_dis)
        rbg = root.findViewById(R.id.rb_g)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        MainActivity.hideShow(Variables.NAME_OFFER_CREATION, true)

        fromDate = root.findViewById(R.id.oc_date_from)
        toDate = root.findViewById(R.id.oc_date_to)


        rbg.setOnCheckedChangeListener { _, checkedId ->

            if (checkedId == R.id.rb_percent) {
                disType = "percent"
            } else if (checkedId == R.id.rb_amount) {
                disType = "amount"
            }

        }
        val calendar = Calendar.getInstance()

        //getting current day,month and year.
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)






        fromDate.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                context!!, { _, year, _, dayOfMonth ->

                    "${month + 1}/$dayOfMonth/$year".also { fromDate.text = it }

                }, year, month, day
            )

            datePickerDialog.show()

        }

        toDate.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                context!!,
                { _, year, _, dayOfMonth ->

                    "${month + 1}/$dayOfMonth/$year".also { toDate.text = it }
                }, year, month, day
            )

            datePickerDialog.show()

        }

        root.findViewById<TextView>(R.id.btnCreateOffer).setOnClickListener {
            if (etOfferCode.text.isEmpty() or etDis.text.isEmpty() or etOfferName.text.isEmpty() or etMaxDis.text.isEmpty() or etMinOfferValue.text.isEmpty() or fromDate.text.isEmpty() or toDate.text.isEmpty()) {
                Toast.makeText(context, "All field must be filled", Toast.LENGTH_SHORT).show()
            } else {
                Utill.showLoader(context!!)
                offerCreationViewModel.createOffer(
                    "57",
                    etOfferCode.text.toString(),
                    etDis.text.toString(),
                    disType,
                    fromDate.text.toString(),
                    toDate.text.toString(),
                    etOfferName.text.toString(),
                    etMinOfferValue.text.toString(),
                    etMaxDis.text.toString()
                )!!
                    .observe(viewLifecycleOwner, {
                        Utill.cancelLoader()
                        Log.d(Variables.TAG, "onCreateView: $it")
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    })
            }

        }


        return root
    }


}