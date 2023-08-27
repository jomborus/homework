package otus.gpb.homework.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val ARG_COLOR = "color"

class FragmentAA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val color = it.getInt(ARG_COLOR).or(0)
            view.setBackgroundColor(color)
        }
        view.findViewById<Button>(R.id.button_aa).setOnClickListener {
            parentFragment?.childFragmentManager?.beginTransaction()?.apply {
                replace(R.id.container_ab, FragmentAB.create(ColorGenerator.generateColor()), "ab")
                addToBackStack("frag")
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun create(value: Int) =
            FragmentAA().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLOR, value)
                }
            }
    }
}