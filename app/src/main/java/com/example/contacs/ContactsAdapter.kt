package com.example.contacs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacs.databinding.ItemContactBinding


class ContactsAdapter(private var contacts: List<Person>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemContactBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }


        override fun getItemCount(): Int = contacts.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val person = contacts[position]
            holder.binding.name.text = person.name
            holder.binding.number.text = person.number

            holder.itemView.setOnClickListener{
                if (mListener!=null){
                    mListener?.onItemClick(person)
                }
            }

        }


        class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)


        var mListener : OnItemClickListener? = null
        var onCallClickListener : OnItemClickListener? = null

    fun interface OnItemClickListener{
        fun onItemClick( person: Person)
    }



}