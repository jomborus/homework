package otus.gpb.homework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val ARG_IS_TABLET = "isTablet"

class FragmentBA : Fragment() {
    private var isTablet: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            isTablet = it.getBoolean(ARG_IS_TABLET)
        }
        if (!isTablet) {
            view.findViewById<Button>(R.id.button_ba).apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.container_ba, FragmentBB(), "BB")
                        addToBackStack("fragB")
                        commit()
                    }
                }
            }
        }
        parentFragmentManager.setFragmentResultListener("color", this) {_, bundle ->
            view.setBackgroundColor(bundle.getInt("color"))
        }
    }

    companion object {
        @JvmStatic
        fun create(isTablet: Boolean) =
            FragmentBA().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_TABLET, isTablet)
                }
            }
    }
}