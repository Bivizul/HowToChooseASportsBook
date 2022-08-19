@file:Suppress("DEPRECATION")

package com.bivizul.questionsaboutsports.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.webkit.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.bivizul.howtochooseasportsbook.R

@Suppress("DEPRECATION")
class WobataFragment : Fragment() {

    private lateinit var wobata: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_wobata, container, false)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        wobata = root.findViewById(R.id.wobata)

        wobata.webViewClient = WebViewClient()

        wobata.webChromeClient = MyChromeClient()
        wobata.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        wobata.isScrollbarFadingEnabled = false

        setSettings()

        //val url = arguments?.getString(KEY_OUT_RESPONSE) ?: DEF_OUT_RESPONSE
        val url = "vk.com"

        if (savedInstanceState==null){
            wobata.post {
                kotlin.run { wobata.loadUrl(url)  }
            }
        }

        wobata.canGoBack()
        wobata.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                wobata.canGoBack()) {
                wobata.goBack()
                return@OnKeyListener true
            }
            false
        })

        return root
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings(){
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

        override fun onShowFileChooser(view: WebView, filePath: ValueCallback<Array<Uri>>, fileChooserParams: FileChooserParams): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }


        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null
        private var mOriginalOrientation = 0
        private var mOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (mCustomView == null) {
                null
            } else BitmapFactory.decodeResource(activity!!.applicationContext.resources, 2130837573)
        }

        override fun onHideCustomView() {
            (activity!!.window.decorView as FrameLayout).removeView(mCustomView)
            mCustomView = null
            activity!!.window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
            activity?.requestedOrientation = mOriginalOrientation
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?
        ) {
            if (mCustomView != null) {
                onHideCustomView()
                return
            }
            mCustomView = paramView
            mOriginalSystemUiVisibility = activity!!.window.decorView.systemUiVisibility
            mOriginalOrientation = activity?.requestedOrientation!!
            mCustomViewCallback = paramCustomViewCallback
            (activity!!.window.decorView as FrameLayout).addView(
                mCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            activity!!.window.decorView.systemUiVisibility = 3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == REQUEST_CODE) {
            filePathCallback!!.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent))
            filePathCallback = null
        }
    }

}