package com.example.hw5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5.databinding.StringTempBinding


class Adapter(
    private var personList: List<Person>
) : RecyclerView.Adapter<Adapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = StringTempBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val person = personList[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return personList.size // сколько будет объектов данных - столько и выведет
    }

    inner class Holder internal constructor(
        private val binding: StringTempBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) = binding.run {
            name.text = person.name
        }
    }
}
