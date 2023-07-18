package ru.otus.basicarchitecture.ui.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.FragmentAddressBinding
import ru.otus.basicarchitecture.databinding.FragmentInfoBinding
import ru.otus.basicarchitecture.databinding.FragmentInterestsBinding
import ru.otus.basicarchitecture.ui.interests.InterestsViewModel

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[InfoViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.textNameInfo.text = state?.name
                    binding.textSurnameInfo.text = state?.surname
                    binding.textBirthdayInfo.text = state?.birthday
                    binding.textAddressInfo.text = state?.address
                        state?.interests?.forEach {
                            val chip = Chip(binding.chipGroup.context)
                            chip.text = it
                            chip.isClickable = true
                            chip.isCheckable = true

                            binding.chipGroup.addView(chip)
                        }
                }
            }
        }
    }

}