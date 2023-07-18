package ru.otus.basicarchitecture.ui.address

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.FragmentAddressBinding
import ru.otus.basicarchitecture.databinding.FragmentFirstInfoBinding
import ru.otus.basicarchitecture.ui.firstInfo.FirstInfoViewModel

@AndroidEntryPoint
class AddressFragment : Fragment() {

    private lateinit var binding: FragmentAddressBinding

    private lateinit var viewModel: AddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddressViewModel::class.java]

        binding.buttonNext.setOnClickListener {
            viewModel.setOnClickListener(
                binding.textCountry.text.toString(),
                binding.textCity.text.toString(),
                binding.textAddress.text.toString()
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigate.collect {
                    if (it) {
                        findNavController().navigate(
                            R.id.interestsFragment,
                            savedInstanceState
                        )
                    }
                }
            }
        }
    }
}
