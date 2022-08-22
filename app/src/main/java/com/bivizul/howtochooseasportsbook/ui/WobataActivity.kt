@file:Suppress("DEPRECATION")

package com.bivizul.howtochooseasportsbook.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.bivizul.howtochooseasportsbook.R
import com.bivizul.howtochooseasportsbook.data.Constant.DEF_WOBATA
import com.bivizul.howtochooseasportsbook.data.Constant.KEY_WOBATA
import im.delight.android.webview.AdvancedWebView

class WobataActivity : ComponentActivity(), AdvancedWebView.Listener {

    lateinit var wobata: AdvancedWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wobata)

        val url = intent.getStringExtra(KEY_WOBATA) ?: DEF_WOBATA

        wobata = findViewById<View>(R.id.wobata) as AdvancedWebView

        wobata.webViewClient = WebViewClient()
        wobata.webChromeClient = MyChromeClient()

        wobata.setListener(this, this)
        wobata.setMixedContentAllowed(false)

        setSettings()

        if (savedInstanceState == null) {
            wobata.post {
                kotlin.run { wobata.loadUrl(url) }
            }
        }

        wobata.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                wobata.canGoBack()
            ) {
                wobata.goBack()
                return@OnKeyListener true
            }
            false
        })

    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        wobata.onResume()
        // ...
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        wobata.onPause()
        // ...
        super.onPause()
    }

    override fun onDestroy() {
        wobata.onDestroy()
        // ...
        super.onDestroy()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
//        if (requestCode == REQUEST_CODE) {
//            filePathCallback!!.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(
//                resultCode,
//                intent))
//            filePathCallback = null
//        }
        wobata.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onBackPressed() {
        if (!wobata.onBackPressed()) {
            return
        }
        finishAndRemoveTask()
        System.exit(0)
    }


    override fun onPageStarted(url: String?, favicon: Bitmap?) {}

    override fun onPageFinished(url: String?) {}

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?,
    ) {
    }

    override fun onExternalPageRequest(url: String?) {}


    // NotePad

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val webSettings = wobata.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
//        webSettings.setAppCacheEnabled(true)
        webSettings.userAgentString = webSettings.userAgentString.replace("; wv", "")
    }

    var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 100

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        wobata.saveState(outState)
    }


    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams,
        ): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }

        private var wobataCustomView: View? = null
        private var wobataCustomViewCallback: CustomViewCallback? = null
        private var wobataOriginalOrientation = 0
        private var wobataOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (wobataCustomView == null) {
                null
            } else BitmapFactory.decodeResource(
                this@WobataActivity.applicationContext.resources,
                2130837573
            )
        }

        override fun onHideCustomView() {
            (this@WobataActivity.window.decorView as FrameLayout).removeView(wobataCustomView)
            wobataCustomView = null
            this@WobataActivity.window.decorView.systemUiVisibility =
                wobataOriginalSystemUiVisibility
            this@WobataActivity.requestedOrientation = wobataOriginalOrientation
            wobataCustomViewCallback!!.onCustomViewHidden()
            wobataCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?,
        ) {
            if (wobataCustomView != null) {
                onHideCustomView()
                return
            }
            wobataCustomView = paramView
            wobataOriginalSystemUiVisibility =
                this@WobataActivity.window.decorView.systemUiVisibility
            wobataOriginalOrientation = this@WobataActivity.requestedOrientation
            wobataCustomViewCallback = paramCustomViewCallback
            (this@WobataActivity.window.decorView as FrameLayout).addView(
                wobataCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            this@WobataActivity.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}