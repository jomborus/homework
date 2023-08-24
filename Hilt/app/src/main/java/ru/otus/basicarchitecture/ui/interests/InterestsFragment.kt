package ru.otus.basicarchitecture.ui.interests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.FragmentInterestsBinding

@AndroidEntryPoint
class InterestsFragment : Fragment() {

    private lateinit var binding: FragmentInterestsBinding

    private lateinit var viewModel: InterestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInterestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[InterestsViewModel::class.java]

        binding.buttonNext.setOnClickListener {
            val chips = mutableListOf<String>()
            binding.chipGroup.forEach {
                if ((it as Chip).isChecked) {
                    chips.add(it.text.toString())
                }
            }

            viewModel.onClickListener(chips)
        }
        binding.chipGroup.rootView
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigate.collect {
                    findNavController().navigate(
                        R.id.infoFragment,
                        savedInstanceState
                    )
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.interests.collect { interests ->
                    interests.forEach {
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
