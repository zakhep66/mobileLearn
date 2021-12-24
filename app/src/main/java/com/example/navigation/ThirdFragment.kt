package com.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigation.databinding.ThirdFragmentBinding

class Fragment3 : Fragment() {

    lateinit var binding: ThirdFragmentBinding

    private var uuid = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ThirdFragmentBinding.inflate(inflater, container, false)

        nextButtonClick()
        prevButtonClick()

        return binding.root
    }

    private fun nextButtonClick() {
        binding.next.setOnClickListener {
            val activityFunctions = requireActivity() as ActivityFunctions
            Calculate.operation = binding.operation.text.toString()
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