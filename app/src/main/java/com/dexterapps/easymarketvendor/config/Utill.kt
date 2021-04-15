package com.dexterapps.easymarketvendor.config

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import com.dexterapps.easymarketvendor.R


object Utill {
    fun isEmptyString(text: String?): Boolean {
        return text == null || text.trim { it <= ' ' } == "null" || text.trim { it <= ' ' }
            .isEmpty()
    }

    fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
        Intent(this, activity).also {
            startActivity(it)
        }
    }

    var dialog: Dialog? = null

    fun showLoader(context: Context) {
        dialog = Dialog(context)
        dialog!!.setContentView(R.layout.custom_loader)
        val wlmp: WindowManager.LayoutParams = dialog!!.window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        dialog!!.window!!.attributes = wlmp
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false)
        dialog!!.show()

    }

    fun cancelLoader() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }


}