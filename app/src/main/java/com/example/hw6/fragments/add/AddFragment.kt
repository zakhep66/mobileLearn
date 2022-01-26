package com.example.hw6.fragments.add

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.TypeConverters
import com.example.hw6.R
import com.example.hw6.data.Node
import com.example.hw6.data.Converter
import com.example.hw6.data.viewModel.NodeViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

@TypeConverters(Converter::class) // указание конвертора

class AddFragment : Fragment() {

    private lateinit var mNodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mNodeViewModel = ViewModelProvider(this)[NodeViewModel::class.java]

        view.add_button.setOnClickListener{
            val nodeValueToAdd = editTextNumber.text.toString().toInt()
            insertDataToDatabase(mNodeViewModel,nodeValueToAdd,mutableListOf())
        }
        return view
    }

    private fun insertDataToDatabase(viewModel: NodeViewModel, id: Int, listNode: MutableList<Node>) {
        val nodeSave = Node(id, listNode)
        viewModel.addNode(nodeSave)

        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    private fun inputCheck(id: Editable): Boolean {
        return id.isNotEmpty()
    }
}
