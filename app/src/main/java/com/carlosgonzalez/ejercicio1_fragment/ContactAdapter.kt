package com.carlosgonzalez.ejercicio1_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.carlosgonzalez.ejercicio1_fragment.databinding.ContactViewBinding

class ContactAdapter(val contacts : List<Contact>, val onClickListener : (Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): ViewHolder {
        val view = LayoutInflater
                    .from(parent.context)
                    .inflate(   R.layout.contact_view,
                                parent,
                               false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        val contact = contacts[position]

        holder.bind(contact)
        holder.itemView.setOnClickListener {
            onClickListener(contact)
        }
    }

    override fun getItemCount() = contacts.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ContactViewBinding.bind(view)
        fun bind (contact : Contact) {
            binding.tvName.text = contact.name
            binding.tvPhoneNumber.text = contact.phoneNumber
            binding.tvEmail.text = contact.email

            Glide
                .with(binding.ivProfilePicture)
                .load(contact.urlProfilePicture)
                .into(binding.ivProfilePicture)
        }
    }
}