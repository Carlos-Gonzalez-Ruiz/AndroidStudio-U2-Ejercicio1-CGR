package com.carlosgonzalez.ejercicio1_fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.carlosgonzalez.ejercicio1_fragment.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    companion object {
        const val CONTACT_DATA = "CONTACT_DATA"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        // Obtener "bundle" y coger lo que se haya pasado como "CONTACT_DATA"
        val contact = arguments?.getParcelable<Contact>(CONTACT_DATA)

        if (contact != null) {
            // Establecer título como nombre del contacto.
            (requireActivity() as AppCompatActivity).supportActionBar?.title = contact.name

            // Nombre del contacto.
            binding.tvNameTitle.text = contact.name

            // Foto de perfil.
            Glide
                .with(binding.ivProfilePicture)
                .load(contact.urlProfilePicture)
                .into(binding.ivProfilePicture)

            // Hacer llamada.
            binding.btnCall.setOnClickListener {
                startActivity(  Intent( Intent.ACTION_DIAL,
                                        Uri.parse("tel:" + contact.phoneNumber)
                                )
                )
            }

            // Enviar e-mail.
            binding.btnEmail.setOnClickListener {
                startActivity(  Intent( Intent.ACTION_SENDTO,
                                        Uri.parse("mailto:" + contact.phoneNumber)
                                )
                )
            }
        }
    }
}