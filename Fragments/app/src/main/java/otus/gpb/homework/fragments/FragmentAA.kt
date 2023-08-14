package otus.gpb.homework.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import otus.gpb.homework.fragments.databinding.FragmentAaBinding

class FragmentAA : Fragment() {

    lateinit var binding: FragmentAaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAaBinding.inflate(inflater, container, false)


        Log.d("Frag", "start")
        return binding.root
    }
}