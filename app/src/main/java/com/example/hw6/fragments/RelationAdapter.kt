package com.example.hw6.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.R
import com.example.hw6.data.Node
import kotlinx.android.synthetic.main.relation_row.view.*


class RelationAdapter(myElement: Node, private var is_child: Boolean, frag: FragmentManager): RecyclerView.Adapter<RelationAdapter.MyViewHolder>() {

    private var nodeList = mutableListOf<Node>()
    private var firstNode = myElement
    private var firstEl = myElement.id
    private var fragmentM = frag


    fun checkRelationNode(nodeValue: Node, nodeNodes: Node, isContaining:Boolean): Boolean {
        return if (isContaining)
            nodeValue.id in nodeNodes.nodes.map { it.id }
        else
            nodeValue.id !in nodeNodes.nodes.map { it.id }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "низя так #${position + 1}", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.relation_row, parent, false)
        return MyViewHolder(v)

    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = nodeList[position]
        holder.itemView.id_value.text = firstEl.toString()
        holder.itemView.textView2.text = currentItem.id.toString()


        if (is_child){
            holder.itemView.id_value.text = firstEl.toString()
            holder.itemView.textView2.text = currentItem.id.toString()
            holder.itemView.textView.text = "-->"
        } else {
            holder.itemView.id_value.text = currentItem.id.toString()
            holder.itemView.textView2.text = firstEl.toString()
            holder.itemView.textView.text = "<--"
        }


        if ((checkRelationNode(firstNode, currentItem, false) && is_child) || (checkRelationNode(currentItem, firstNode, false) && !is_child)){


            //If connection exists make it green and listener
            if ((checkRelationNode(currentItem, firstNode, true) && is_child) || (checkRelationNode(firstNode, currentItem, true) && !is_child)){

                holder.itemView.setBackgroundColor(Color.GREEN)

                holder.itemView.setOnClickListener {
                    var dialogDel = DeleteRelationFragment(firstNode, currentItem, is_child)
                    dialogDel.show(fragmentM, "tag")
                    Toast.makeText(holder.itemView.context, "success #${position + 1}", Toast.LENGTH_SHORT).show()

                }
            } else {

                holder.itemView.setOnClickListener{
                    var dialogAdd = AddRelationFragment(firstNode, currentItem, is_child)
                    dialogAdd.show(fragmentM, "tag")


                }
            }

        }

    }
    // set data and filter (delete duplicate, examole 9 --- 9)
    fun setData(node: MutableList<Node>) {
        this.nodeList = node.filter { it.id != firstEl } as MutableList<Node>
        notifyDataSetChanged()
    }
}
