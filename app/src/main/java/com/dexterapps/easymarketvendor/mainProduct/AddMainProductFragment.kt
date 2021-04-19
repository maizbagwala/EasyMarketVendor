package com.dexterapps.easymarketvendor.mainProduct

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.config.Utill
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.mainProduct.utils.FileUtil
import com.dexterapps.easymarketvendor.mainProduct.viewModel.AddProductViewModel
import com.dexterapps.easymarketvendor.mainProduct.viewModel.ProductCategoryViewModel
import com.squareup.picasso.Picasso
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URI
import java.util.*

class AddMainProductFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var root: View
    lateinit var btnAddProduct: TextView
    lateinit var et_pname: EditText
    lateinit var et_unit: EditText
    lateinit var et_base_unit: EditText
    lateinit var et_base_unit_price: EditText
    lateinit var et_minimum_qty: EditText
    lateinit var et_unit_price: EditText
    lateinit var et_purchase_price: EditText
    lateinit var et_discount: EditText
    lateinit var et_product_description: EditText
    lateinit var tv_product_category: TextView
    lateinit var tv_discount_type: TextView
    lateinit var ll_product_category_click: LinearLayout
    lateinit var ll_discount_type_click: LinearLayout
    lateinit var sp_product_category: Spinner
    lateinit var sp_discount_type: Spinner
    lateinit var iv_add_image: ImageView
    lateinit var parent_linear_layout: LinearLayout
    lateinit var selectedImage: ImageView
    lateinit var iv_thumb_add_image: ImageView
    lateinit var tv_thumb_delete: TextView
    var discountTypeList = arrayListOf<String>()
    var productCategoryList = arrayListOf<String>()
    var productCategoryIdList = arrayListOf<Int>()
    var productCategoryIdSelected: Int? = null
    var files = arrayListOf<Uri>()
    lateinit var singleFiles: Uri
    lateinit var productCategoryViewModel: ProductCategoryViewModel
    lateinit var addProductViewModel: AddProductViewModel


    private val REQUEST_CODE_ASK_PERMISSIONS = 123
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_add_main_product, container, false)
        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        MainActivity.hideShow(Variables.NAME_ADD_MAIN_PRODUCT, true)
        init(root)
        return root
    }

    private fun init(root: View) {
        productCategoryViewModel = ViewModelProvider(this).get(ProductCategoryViewModel::class.java)
        addProductViewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        btnAddProduct = root.findViewById(R.id.btnAddProduct)
        et_pname = root.findViewById(R.id.et_pname)
        et_unit = root.findViewById(R.id.et_unit)
        et_base_unit = root.findViewById(R.id.et_base_unit)
        et_base_unit_price = root.findViewById(R.id.et_base_unit_price)
        et_minimum_qty = root.findViewById(R.id.et_minimum_qty)
        et_unit_price = root.findViewById(R.id.et_unit_price)
        et_purchase_price = root.findViewById(R.id.et_purchase_price)
        et_discount = root.findViewById(R.id.et_discount)
        et_product_description = root.findViewById(R.id.et_product_description)
        tv_product_category = root.findViewById(R.id.tv_product_category)
        tv_discount_type = root.findViewById(R.id.tv_discount_type)
        ll_product_category_click = root.findViewById(R.id.ll_product_category_click)
        ll_discount_type_click = root.findViewById(R.id.ll_discount_type_click)
        sp_product_category = root.findViewById(R.id.sp_product_category)
        sp_discount_type = root.findViewById(R.id.sp_discount_type)
        iv_add_image = root.findViewById(R.id.iv_add_image)
        parent_linear_layout = root.findViewById(R.id.parent_linear_layout)
        iv_thumb_add_image = root.findViewById(R.id.iv_thumb_add_image)
        tv_thumb_delete = root.findViewById(R.id.tv_thumb_delete)
        setProductCategorySpinnerData()
        setDiscountTypeSpinnerData()
        initClick()
    }

    private fun setProductCategorySpinnerData() {
        Utill.showLoader(context!!)
        productCategoryViewModel.getProductCategory()?.observe(viewLifecycleOwner, {
            Log.d(Variables.TAG, "getSpinnerData: $it")
            for (i in it.data) {
                productCategoryList.add(i.name)
                productCategoryIdList.add(i.id)
            }
            sp_product_category.onItemSelectedListener = this

            val businessCategory: ArrayAdapter<String> = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_spinner_item,
                productCategoryList
            )
            businessCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp_product_category.adapter = businessCategory
            sp_product_category.setSelection(0)
            Utill.cancelLoader()
        })
    }

    private fun setDiscountTypeSpinnerData() {

        discountTypeList.add("Amount")
        discountTypeList.add("Percent")
        sp_discount_type.onItemSelectedListener = this

        val businessCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_spinner_item,
            discountTypeList
        )
        businessCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_discount_type.adapter = businessCategory
        sp_discount_type.setSelection(0)

    }

    private fun initClick() {
        btnAddProduct.setOnClickListener {
            checkValidations()
        }
        ll_product_category_click.setOnClickListener {
            sp_product_category.performClick()
        }
        ll_discount_type_click.setOnClickListener {
            sp_discount_type.performClick()

        }
        iv_add_image.setOnClickListener {
            addImage()

        }
        iv_thumb_add_image.setOnClickListener {
            selectImage(context!!, false)

        }
        tv_thumb_delete.setOnClickListener {
            tv_thumb_delete.visibility = View.GONE
            iv_thumb_add_image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!, // Context
                    R.drawable.ic_input_add // Drawable
                )
            )
            singleFiles = Uri.EMPTY
        }
    }

    //===== add image in layout
    fun addImage() {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.image, null)
        // Add the new row before the add field button.
        parent_linear_layout.addView(rowView, parent_linear_layout.getChildCount() - 1)
        parent_linear_layout.isFocusable()
        selectedImage = rowView.findViewById<ImageView>(R.id.number_edit_text)
        selectImage(context!!, true)
    }

    //===== select image
    private fun selectImage(context: Context, isMultiple: Boolean) {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle("Choose a Media")
        builder.setItems(options) { dialog, item ->
            if (options[item] == "Take Photo") {
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (isMultiple)
                    startActivityForResult(takePicture, 0)
                else
                    startActivityForResult(takePicture, 1)
            } else if (options[item] == "Choose from Gallery") {
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                if (isMultiple)
                    startActivityForResult(pickPhoto, 2) //one can be replaced with any action code
                else
                    startActivityForResult(pickPhoto, 3) //one can be replaced with any action code
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    //for multiple pic set
                    val img = data.extras!!["data"] as Bitmap?
                    selectedImage.setImageBitmap(img)
                    Picasso.get().load(getImageUri(context!!, img!!)).into(selectedImage)
                    val imgPath: String =
                        FileUtil.getPath(context!!, getImageUri(context!!, img)!!)!!
                    files.add(Uri.parse(imgPath))
                    Log.e("image", imgPath)
                }
                1 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    //for single pic set from camera
                    val img = data.extras!!["data"] as Bitmap?
                    iv_thumb_add_image.setImageBitmap(img)
                    Picasso.get().load(getImageUri(context!!, img!!)).into(iv_thumb_add_image)
                    val imgPath: String =
                        FileUtil.getPath(context!!, getImageUri(context!!, img)!!)!!
                    singleFiles = Uri.parse(imgPath)

                    Log.e("image", imgPath)
//                    tv_thumb_delete.visibility = View.VISIBLE
                }
                2 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    //for multiple pic set
                    val img = data.data
                    Picasso.get().load(img).into(selectedImage)
                    val imgPath: String = FileUtil.getPath(context!!, img!!)!!
                    files.add(Uri.parse(imgPath))
                    Log.e("image", imgPath)
                }
                3 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    //for single pic set from gallery
                    val img = data.data
                    Picasso.get().load(img).into(iv_thumb_add_image)
                    val imgPath: String = FileUtil.getPath(context!!, img!!)!!
                    singleFiles = Uri.parse(imgPath)

                    Log.e("image", imgPath)
//                    tv_thumb_delete.visibility = View.VISIBLE

                }
            }
        }
    }

    //===== bitmap to Uri
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "intuenty",
            null
        )
        Log.d("image uri", path)
        return Uri.parse(path)
    }

    private fun checkValidations() {
        if (et_pname.text.isEmpty() ||
            et_unit.text.isEmpty() ||
            et_base_unit.text.isEmpty() ||
            et_base_unit_price.text.isEmpty() ||
            et_minimum_qty.text.isEmpty() ||
            et_unit_price.text.isEmpty() ||
            et_purchase_price.text.isEmpty() ||
            et_discount.text.isEmpty() ||
            et_product_description.text.isEmpty() ||
            Utill.isEmptyString(sp_product_category.selectedItem.toString().trim()) ||
            Utill.isEmptyString(sp_discount_type.selectedItem.toString().trim()) ||
            files.size == 0 ||
            singleFiles.equals(Uri.EMPTY)
        ) {
            MainActivity.snack(root, Variables.fields_required)

        } else {


            requestPermission()


        }


    }

    // this is all you need to grant your application external storage permision
    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_ASK_PERMISSIONS
            )
        } else {
            uploadImages()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                uploadImages()
            } else {
                MainActivity.snack(root, "Permission denied")
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    //===== Upload files to server
    fun uploadImages() {

        val list: MutableList<MultipartBody.Part> = ArrayList()
        for (uri in files) {
            Log.i("uris", uri.path!!)
            list.add(prepareFilePart("file", uri))
        }

        Utill.showLoader(context!!)
        val listt = ArrayList<MultipartBody.Part>()
        if (files.size > 0) {
            for (uri in files) {
                val fileName1 = File(uri.path)
                val requestFile = RequestBody.create(
                    MediaType.parse("multipart/form-data"), fileName1
                )
                val imageRequest =
                    MultipartBody.Part.createFormData("photos[]", fileName1.name, requestFile)
                listt.add(imageRequest)
            }
        }

        val fileName1 = File(singleFiles.path!!)
        val requestFile = RequestBody.create(
            MediaType.parse("multipart/form-data"), fileName1
        )
        val imageRequest =
            MultipartBody.Part.createFormData("thumbnail_img", fileName1.name, requestFile)


        addProductViewModel.addProduct(
            RequestBody.create(MediaType.parse("text/plain"), et_pname.text.toString()),
            RequestBody.create(MediaType.parse("text/plain"), "57"),
            RequestBody.create(MediaType.parse("text/plain"), productCategoryIdSelected.toString()),
            listt,
            imageRequest,
            RequestBody.create(MediaType.parse("text/plain"), et_unit.text.toString()),
            RequestBody.create(MediaType.parse("text/plain"), et_minimum_qty.text.toString()),
            RequestBody.create(MediaType.parse("text/plain"), et_base_unit.text.toString()),
            RequestBody.create(
                MediaType.parse("text/plain"),
                et_product_description.text.toString()
            ),
            RequestBody.create(MediaType.parse("text/plain"), et_unit_price.text.toString()),
            RequestBody.create(MediaType.parse("text/plain"), et_purchase_price.text.toString()),
            RequestBody.create(MediaType.parse("text/plain"), et_discount.text.toString()),
            RequestBody.create(
                MediaType.parse("text/plain"),
                sp_discount_type.selectedItem.toString().toLowerCase(Locale.ENGLISH)
            ),
            RequestBody.create(MediaType.parse("text/plain"), et_base_unit_price.text.toString())

        )!!
            .observe(viewLifecycleOwner, {
                Utill.cancelLoader()
                Log.d(Variables.TAG, "onCreateView: $it")
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })

    }

    private fun prepareFilePart(partName: String, fileUri: Uri): MultipartBody.Part {
        val file = File(fileUri.path)
        Log.i("here is error", file.absolutePath)
        // create RequestBody instance from file
        val requestFile = RequestBody.create(
            MediaType.parse("image/*"),
            file
        )

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent!!.id == sp_discount_type.id) {
            tv_discount_type.text = discountTypeList[position]
        } else if (parent.id == sp_product_category.id) {
            tv_product_category.text = productCategoryList[position]
            productCategoryIdSelected = productCategoryIdList[position]
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}