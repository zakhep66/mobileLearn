package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigation.databinding.FirsctFragmentBinding


class Fragment1 : Fragment() {

    lateinit var binding: FirsctFragmentBinding

    private var uuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirsctFragmentBinding.inflate(inflater, container, false)
        nextButtonClick()
        return binding.root
    }

    private fun nextButtonClick() {
        binding.nextButton.setOnClickListener {
            val activityFunctions = requireActivity() as ActivityFunctions
            try {
                Calculate.field1 = binding.firstTextView.text.toString().toInt()
            } catch (e: Exception) {
                Calculate.field1 = null
            }
            activityFunctions.showNextFragment(uuid)
        }
    }


}