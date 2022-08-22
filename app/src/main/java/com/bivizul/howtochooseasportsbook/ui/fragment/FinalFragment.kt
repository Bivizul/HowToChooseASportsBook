package com.bivizul.howtochooseasportsbook.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.howtochooseasportsbook.R
import com.bivizul.howtochooseasportsbook.appComponent
import com.bivizul.howtochooseasportsbook.data.Constant
import com.bivizul.howtochooseasportsbook.databinding.FragmentFinalBinding
import com.bivizul.howtochooseasportsbook.ui.support.HowToChooseASBViewModel
import com.bivizul.howtochooseasportsbook.ui.support.HowToChooseASBViewModelFactory
import com.bivizul.howtochooseasportsbook.util.getDialog
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinalFragment : Fragment(R.layout.fragment_final) {

    @Inject
    lateinit var factory: HowToChooseASBViewModelFactory.Factory

    private val binding by viewBinding(FragmentFinalBinding::bind)
    private val viewModel: HowToChooseASBViewModel by viewModels { factory.create() }
    private val num = Constant.FOUR

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.howToChooseASB.collect { howToChooseASB ->
                    if (howToChooseASB == null) {
                        getDialog(requireContext(), requireActivity())
                    }
                    howToChooseASB?.let {
                        binding.titleStep.text = it.howToChooseASB.tips[num].titleTips
                        binding.subtitleStep.text = it.howToChooseASB.tips[num].subtitleTips
                    }
                }
            }
        }
    }
}