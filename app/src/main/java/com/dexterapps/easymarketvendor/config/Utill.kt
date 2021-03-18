package com.dexterapps.easymarketvendor.config

import android.app.Activity
import android.content.Intent

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


}