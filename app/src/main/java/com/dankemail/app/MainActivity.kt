
package com.dankemail.app

import android.app.Activity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.content.Intent
import android.net.Uri

class MainActivity : Activity() {

    private lateinit var webView: WebView

    private val appUrl =
        "https://imap-production-5bceae6f.up.railway.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val uri = request.url

                return if (uri.host == "imap-production-5bceae6f.up.railway.app") {
                    false
                } else {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, uri)
                    )
                    true
                }
            }
        }

        setContentView(webView)
        webView.loadUrl(appUrl)
    }

    @Deprecated("Deprecated in Android")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
