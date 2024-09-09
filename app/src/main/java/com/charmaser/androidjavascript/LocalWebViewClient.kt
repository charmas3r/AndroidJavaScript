package com.charmaser.androidjavascript

import android.webkit.WebView
import android.webkit.WebViewClient

internal class LocalWebViewClient : WebViewClient() {

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            view?.loadUrl(url)
        }
        return true
    }
}