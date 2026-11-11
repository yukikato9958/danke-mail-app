
package com.dankemail.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.WindowInsets
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.content.Intent

class MainActivity : Activity() {

    private lateinit var webView: WebView

    private val appUrl =
        "https://imap-production-5bceae6f.up.railway.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE
        window.decorView.systemUiVisibility =
            android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
            android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

        webView = WebView(this)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true

        webView.setBackgroundColor(Color.WHITE)

        webView.setOnApplyWindowInsetsListener { view, insets ->
            val bars = insets.getInsets(
                WindowInsets.Type.systemBars()
            )

            view.setPadding(
                0,
                bars.top,
                0,
                bars.bottom
            )

            insets
        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val uri = request.url

                return if (
                    uri.host == "imap-production-5bceae6f.up.railway.app"
                ) {
                    false
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                }
            }
        }

        setContentView(webView)
        webView.requestApplyInsets()
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
