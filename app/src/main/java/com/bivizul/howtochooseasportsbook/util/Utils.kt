package com.bivizul.howtochooseasportsbook.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import com.bivizul.howtochooseasportsbook.R
import java.util.*

fun getGuideres(context: Context): Locale = context.resources.configuration.locales[0]

fun checkConnect(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun getDER(context: Context, activity: Activity) {
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