package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.Exception
import com.example.navigation.databinding.SecondFragmentBinding


class Fragment2 : Fragment() {

    lateinit var binding: SecondFragmentBinding

    private var uuid = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SecondFragmentBinding.inflate(inflater, container, false)

        nextButtonClick()
        prevButtonClick()

        return binding.root
    }

    private fun nextButtonClick() {
        binding.next.setOnClickListener {
            val activityFunctions = requireActivity() as ActivityFunctions
            try {
                Calculate.field2 = binding.secondTextView.text.toString().toInt()
            } catch (e: Exception) {
                Calculate.field2 = null
            }
            activityFunctions.showNextFragment(uuid)
        }
    }

    private fun prevButtonClick() {
        binding.prev.setOnClickListener {
            val activityFunctions = requireActivity() as ActivityFunctions
            activityFunctions.showPreviousFragment(uuid)
        }
    }

}