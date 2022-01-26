package com.example.hw6.fragments.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import com.example.hw6.data.Node
import kotlinx.android.synthetic.main.custom_row.view.*


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var nodeList = mutableListOf<Node>()

    fun сhildNode(node: Node, nodesList: MutableList<Node>): Boolean {
        val allChildrenOfNodes = nodesList.map { it -> it.nodes.map { it.id } }
        val containingOfValueInNodes = allChildrenOfNodes.map { it.contains(node.id) }
        return true in containingOfValueInNodes
    }
    fun parentNode(node: Node): Boolean {
        return node.nodes.isNullOrEmpty().not()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent,false))
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = nodeList[position]
        holder.itemView.id_value.text = currentItem.id.toString()
        if (parentNode(currentItem)&&(сhildNode(currentItem, nodeList))) { // если и родитель и ребёнок
            holder.itemView.setBackgroundColor(Color.parseColor("#db7093")) // приятный красноватый цвет - лиловый
        } else if (parentNode(currentItem)) { // если только родитель
            holder.itemView.setBackgroundColor(Color.parseColor("#87ceeb")) // приятно синий цвет
        } else if (сhildNode(currentItem, nodeList)) { // если же только ребёнок
            holder.itemView.setBackgroundColor(Color.parseColor("#ffcc00")) // жёлто-оранжевый цвет
        }

        //nav
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToRelationFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(node: MutableList<Node>) {
        this.nodeList = node
        notifyDataSetChanged()
    }
}
