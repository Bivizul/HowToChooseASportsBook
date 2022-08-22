package com.bivizul.howtochooseasportsbook.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.howtochooseasportsbook.R
import com.bivizul.howtochooseasportsbook.appComponent
import com.bivizul.howtochooseasportsbook.data.Constant
import com.bivizul.howtochooseasportsbook.data.Constant.APP_PREF
import com.bivizul.howtochooseasportsbook.data.Constant.BACK_ACTIVITY
import com.bivizul.howtochooseasportsbook.data.model.SetChoose
import com.bivizul.howtochooseasportsbook.databinding.ActivityMainBinding
import com.bivizul.howtochooseasportsbook.ui.support.WobataViewModel
import com.bivizul.howtochooseasportsbook.ui.support.WobataViewModelFactory
import com.bivizul.howtochooseasportsbook.util.checkConnect
import com.bivizul.howtochooseasportsbook.util.getDialog
import com.bivizul.howtochooseasportsbook.util.getInitId
import com.bivizul.howtochooseasportsbook.util.setChoose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var factory: WobataViewModelFactory.Factory
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val pref by lazy {
        this.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
    }
    private val viewModel by viewModels<WobataViewModel> {
        factory.create(SetChoose(setChoose(this), getInitId(pref)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        if (!checkConnect(this)) {
            getDialog(this, this)
        } else {
            binding.backMain.load(BACK_ACTIVITY)

            this.lifecycleScope.launch(Dispatchers.Main) {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.setChoose()
                    viewModel.choose.collect {
                        delay(1000)
                        if (it.getChoose == "no") {
                            val intent = Intent(this@MainActivity, ScrollingActivity::class.java)
                            intent.putExtra(Constant.KEY_SCROLLING, it.getChoose)
                            startActivity(intent)
                        } else if (it.getChoose == Constant.ERROR_MESSAGE) {
                            getDialog(this@MainActivity, this@MainActivity)
                        } else {
                            val intent = Intent(this@MainActivity, WobataActivity::class.java)
                            intent.putExtra(Constant.KEY_WOBATA, it.getChoose)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}