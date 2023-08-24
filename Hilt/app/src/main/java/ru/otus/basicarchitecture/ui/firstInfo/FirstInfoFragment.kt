package ru.otus.basicarchitecture.ui.firstInfo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.FragmentFirstInfoBinding
import java.util.Calendar


@AndroidEntryPoint
class FirstInfoFragment : Fragment() {

    private lateinit var binding: FragmentFirstInfoBinding

    private lateinit var viewModel: FirstInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FirstInfoViewModel::class.java]

        binding.textBirthday.doOnTextChanged { text, _, _, _ ->
            viewModel.doOnTextChanged(text.toString())
        }

        binding.buttonNext.setOnClickListener {
            viewModel.onClickListener(
                binding.textName.text.toString(),
                binding.textSurname.text.toString(),
                binding.textBirthday.text.toString()
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigate.collect {
                    findNavController().navigate(
                        R.id.addressFragment,
                        savedInstanceState
                    )
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorText.collect {
                    if (it) {
                        binding.textBirthday.setTextColor(Color.RED)
                    } else {
                        binding.textBirthday.setTextColor(Color.GREEN)
                    }
                }
            }
        }
    }
}
