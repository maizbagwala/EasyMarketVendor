package com.dexterapps.easymarketvendor.productList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexterapps.easymarketvendor.MainActivity
import com.dexterapps.easymarketvendor.R
import com.dexterapps.easymarketvendor.addproduct.AddProductFragment
import com.dexterapps.easymarketvendor.config.Variables
import com.dexterapps.easymarketvendor.home.NewOrderTabFragment
import com.dexterapps.easymarketvendor.mainProduct.AddMainProductFragment


class productList : Fragment() {

    private var tvAddProduct: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product_list, container, false)

        MainActivity.nav_back_btn.setOnClickListener {
            MainActivity.back()
        }
        MainActivity.hideShow(Variables.NAME_PRODUCT_LIST, true)
        val rvProduct: RecyclerView = view.findViewById(R.id.rv_productList)

        val productList = ArrayList<ProductListModel>()

        productList.add(
            ProductListModel(
                1,
                "https://images-na.ssl-images-amazon.com/images/I/81JI5O0qB5L._SX569_.jpg",
                "maggi"
            )
        )
        productList.add(
            ProductListModel(
                1,
                "https://images-na.ssl-images-amazon.com/images/I/81JI5O0qB5L._SX569_.jpg",
                "maggi"
            )
        )
        productList.add(
            ProductListModel(
                1,
                "https://images-na.ssl-images-amazon.com/images/I/81JI5O0qB5L._SX569_.jpg",
                "maggi"
            )
        )
        productList.add(
            ProductListModel(
                1,
                "https://images-na.ssl-images-amazon.com/images/I/81JI5O0qB5L._SX569_.jpg",
                "maggi"
            )
        )
        productList.add(
            ProductListModel(
                1,
                "https://images-na.ssl-images-amazon.com/images/I/81JI5O0qB5L._SX569_.jpg",
                "maggi"
            )
        )
        val productAdapter = productListAdapter(productList)

        rvProduct.layoutManager = LinearLayoutManager(context)
        rvProduct.adapter = productAdapter



        tvAddProduct = view.findViewById(R.id.tv_add_product)



        tvAddProduct!!.setOnClickListener {

            MainActivity.loadFragment(
                AddMainProductFragment
                    (),
                Variables.TAG_ADD_MAIN_PRODUCT
            )

        }


        return view
    }

}