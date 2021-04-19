package com.dexterapps.easymarketvendor.myDeliveryPerson

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.config.Variables.TAG
import com.dexterapps.easymarketvendor.myDeliveryPerson.viewModel.DeliveryPersonViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class AddDeliveryPersonFragment : Fragment() {

    lateinit var ivLicense: ImageView
    lateinit var ivProfile: ImageView
    lateinit var tvUploadLicense: TextView
    lateinit var tvUploadProfile: TextView

    lateinit var dName: TextView
    lateinit var dEmail: TextView
    lateinit var dMobile: TextView
    lateinit var dPassword: TextView
    lateinit var dLicNo: TextView
    lateinit var dAdd: TextView
    lateinit var deliveryPersonViewModel: DeliveryPersonViewModel
    lateinit var imageUri: Uri
    lateinit var licUri: Uri
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_delilvery_person, container, false)
        deliveryPersonViewModel = ViewModelProvider(this).get(DeliveryPersonViewModel::class.java)
        ivLicense = root.findViewById(R.id.iv_upload_license)
        ivProfile = root.findViewById(R.id.iv_upload_profile)
        tvUploadLicense = root.findViewById(R.id.tv_upload_license)
        tvUploadProfile = root.findViewById(R.id.tv_upload_profile)
        dName = root.findViewById(R.id.d_name)
        dEmail = root.findViewById(R.id.d_email)
        dMobile = root.findViewById(R.id.d_mobile)
        dPassword = root.findViewById(R.id.d_password)
        dLicNo = root.findViewById(R.id.d_lic_no)
        dAdd = root.findViewById(R.id.d_add)
        MainActivity.hideShow(Variables.NAME_ADD_DELIVERY_PERSON, true)
        root.findViewById<CardView>(R.id.license_upload_card).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )


            intent.type = "image/*"
            intent.putExtra("crop", "true")
            intent.putExtra("scale", true)
            intent.putExtra("outputX", 256)
            intent.putExtra("outputY", 256)
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("return-data", true)
            startActivityForResult(intent, 1001)
        }
        root.findViewById<CardView>(R.id.profile_upload_card).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )


            intent.type = "image/*"
            intent.putExtra("crop", "true")
            intent.putExtra("scale", true)
            intent.putExtra("outputX", 256)
            intent.putExtra("outputY", 256)
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("return-data", true)
            startActivityForResult(intent, 1002)
        }

        root.findViewById<TextView>(R.id.btnRegisterDelivery).setOnClickListener {
            if (dName.text.toString().isEmpty() || dEmail.text.toString()
                    .isEmpty() || dMobile.text.toString().isEmpty() || dPassword.text.toString()
                    .isEmpty() || dLicNo.text.toString().isEmpty() || dAdd.text.toString().isEmpty()
            ) {
                Toast.makeText(context, "All fields are requeired", Toast.LENGTH_SHORT).show()
            } else if (ivLicense.drawable == null || ivProfile.drawable == null) {
                Toast.makeText(context, "Please Select image first", Toast.LENGTH_SHORT).show()
            } else {

                val fileName1 = File(licUri.path)
//                val fileName1 = licUri.toFile()
                val requestFile = RequestBody.create(
                    MediaType.parse("multipart/form-data"), fileName1
                )
                val fileName2 = File(imageUri.path)
//                val fileName2 = imageUri.toFile()
                val requestFile2 = RequestBody.create(
                    MediaType.parse("multipart/form-data"), fileName2
                )

                Utill.showLoader(context!!)
                deliveryPersonViewModel.addDeliveryPerson(
                    RequestBody.create(
                        MediaType.parse("text/plain"),
                        "57"
                    ), RequestBody.create(
                        MediaType.parse("text/plain"),
                        dName.text.toString()
                    ), RequestBody.create(
                        MediaType.parse("text/plain"),
                        dEmail.text.toString()
                    ), RequestBody.create(
                        MediaType.parse("text/plain"),
                        dMobile.text.toString()
                    ), RequestBody.create(
                        MediaType.parse("text/plain"),
                        dPassword.text.toString()
                    ), RequestBody.create(
                        MediaType.parse("text/plain"),
                        dLicNo.text.toString()
                    ), RequestBody.create(
                        MediaType.parse("text/plain"),
                        dAdd.text.toString()
                    ), MultipartBody.Part.createFormData("lic_img", fileName1.name, requestFile)
                    , MultipartBody.Part.createFormData("profile_img", fileName2.name, requestFile2)
                )!!
                    .observe(viewLifecycleOwner, Observer {
                        Utill.cancelLoader()
                        Log.d(TAG, "onCreateView: $it")
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    })
            }
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {

            if (requestCode == 1001) {
                val uri = data.data
                licUri = data.data!!
                ivLicense.setImageURI(uri)
                tvUploadLicense.visibility = GONE
            } else if (requestCode == 1002) {
                val uri = data.data
                imageUri = data.data!!
                ivProfile.setImageURI(uri)
                tvUploadProfile.visibility = GONE
            }
        }
    }
}