package com.dexterapps.easymarketvendor.addproduct

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.addproduct.adapter.AddProductAdapter
import com.jama.carouselview.CarouselView
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [AddProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProductFragment : Fragment() {
    var viewData = arrayOfNulls<View>(5)
    lateinit var uri: Uri
    var clicked_position: Int = -1

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add_product, container, false)
        val rvSubCategory: RecyclerView = view.findViewById(R.id.rv_sub_category)
        rvSubCategory.layoutManager = LinearLayoutManager(context)
        rvSubCategory.adapter = AddProductAdapter()
        val carouselView = view.findViewById<CarouselView>(R.id.carousel)
        carouselView.apply {
            size = 5
            resource = R.layout.image_carousel_item
            indicatorAnimationType = IndicatorAnimationType.THIN_WORM
            carouselOffset = OffsetType.CENTER


            setCarouselViewListener { view, position ->

                view.findViewById<CardView>(R.id.image_upload_card).setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI
                    )

                    viewData[position] = view


                    intent.type = "image/*"
                    intent.putExtra("crop", "true")
                    intent.putExtra("scale", true)
                    intent.putExtra("outputX", 256)
                    intent.putExtra("outputY", 256)
                    intent.putExtra("aspectX", 1)
                    intent.putExtra("aspectY", 1)
                    intent.putExtra("return-data", true)
                    startActivityForResult(intent, position)

                }
            }
            // After you finish setting up, show the CarouselView
            show()
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                0 -> {
                    clicked_position = 0
                    uri = data.data!!
                    Toast.makeText(context, "0", Toast.LENGTH_SHORT).show()
                    viewData[0]?.findViewById<ImageView>(R.id.iv_upload_image)?.setImageURI(uri)
//                    viewData[0].findViewById<View>(R.id.iv_upload_image)!!
//                        .resources(context!!.getDrawable(R.drawable.app_logo))
                }
                1 -> {
                    clicked_position = 1

                    uri = data.data!!
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                    viewData[1]?.findViewById<ImageView>(R.id.iv_upload_image)?.setImageURI(uri)

                }
                2 -> {
                    clicked_position = 2

                    uri = data.data!!
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
                    viewData[2]?.findViewById<ImageView>(R.id.iv_upload_image)?.setImageURI(uri)

                }
                3 -> {
                    clicked_position = 3

                    uri = data.data!!
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
                    viewData[3]?.findViewById<ImageView>(R.id.iv_upload_image)?.setImageURI(uri)

                }
                4 -> {

                    clicked_position = 4
                    uri = data.data!!
                    Toast.makeText(context, "4", Toast.LENGTH_SHORT).show()
                    viewData[4]?.findViewById<ImageView>(R.id.iv_upload_image)?.setImageURI(uri)

                }
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}