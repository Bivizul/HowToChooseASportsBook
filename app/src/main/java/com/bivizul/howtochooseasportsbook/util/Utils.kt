package com.bivizul.howtochooseasportsbook.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.bivizul.howtochooseasportsbook.R
import java.text.SimpleDateFormat
import java.util.*

fun setChoose(context: Context): Locale = context.resources.configuration.locales[0]

fun checkConnect(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun getDialog(context: Context, activity: Activity) {
    AlertDialog.Builder(context).apply {
        setTitle(context.getString(R.string.oops))
        setMessage(context.getString(R.string.error_message))

        setPositiveButton(context.getString(R.string.exit)) { _, _ ->
            activity.finishAndRemoveTask()
            System.exit(0)
        }
        setCancelable(true)
    }.create().show()
}

fun getInitId(pref: SharedPreferences): String {
    var inId = pref.getString("initId", "no") ?: "no"
    if (inId == "no") {
        val dNow = Date()
        val ft = SimpleDateFormat("yyMMddhhmmssMs")
        val datetime = ft.format(dNow)
        val randomNum = (10000 until 100000).random()
        inId = datetime + randomNum
        pref.edit().putString("initId", inId).apply()
    }
    return inId
}
