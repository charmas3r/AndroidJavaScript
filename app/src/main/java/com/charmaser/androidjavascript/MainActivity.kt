package com.charmaser.androidjavascript

import android.content.pm.PackageManager
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext

class MainActivity : AppCompatActivity() {
    lateinit var url: EditText
    lateinit var browser: WebView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = findViewById(R.id.etURL)
        browser = findViewById(R.id.wvURL)
        button = findViewById(R.id.buGo)

        browser.getSettings().setJavaScriptEnabled(true)

        browser.addJavascriptInterface(WebAppInterface(this), "Android");
        browser.webViewClient = LocalWebViewClient()

        button.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.READ_PHONE_STATE,
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (!shouldShowRequestPermissionRationale(android.Manifest.permission.READ_PHONE_STATE)) {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_PHONE_STATE),
                        REQUEST_CODE_ASK_PERMISSIONS,
                    )
                }
            } else {
                loadUrl()
            }
        }
    }

    private fun loadUrl() {
        browser.loadUrl(url.text.toString())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadUrl()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_ASK_PERMISSIONS = 123
    }
}