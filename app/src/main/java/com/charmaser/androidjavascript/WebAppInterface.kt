package com.charmaser.androidjavascript

import android.content.Context
import android.content.pm.PackageManager
import android.webkit.JavascriptInterface
import android.telephony.TelephonyManager;
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService


class WebAppInterface(private val context: Context) {

    @JavascriptInterface
    fun GetPhoneNumber(): String? {
        Log.d("WebAppInterface", "GetPhoneNumber is called")
        val isPhoneStatePermissionGranted = ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.READ_PHONE_STATE,
        ) == PackageManager.PERMISSION_GRANTED

        Log.d("WebAppInterface", "READ_PHONE_STATE granted: $isPhoneStatePermissionGranted")

        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        return telephonyManager?.getLine1Number()
    }
}