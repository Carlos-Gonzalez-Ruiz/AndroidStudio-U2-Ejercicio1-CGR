package com.carlosgonzalez.ejercicio1_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosgonzalez.ejercicio1_fragment.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val contactList = listOf(
        Contact("Carlos González Ruiz",
                "https://loremflickr.com/240/320/paris?lock=0",
                "999 999 999",
                "carlosgonzrz@gmail.com"),
        Contact("Luisa Pérez",
                "https://loremflickr.com/240/320/paris?lock=1",
                "888 888 888",
                "luisa.perez@gmail.com"),
        Contact("Mario Casanova",
                "https://loremflickr.com/240/320/paris?lock=2",
                "777 777 777",
                "mario.casanova@gmail.com"),
    )

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view).apply {
            // Establecer titulo de arriba al nombre de la aplicación.
            (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

            // Solución para error en "logcat": E/RecyclerView: No layout manager attached; skipping layout
            val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this@MainFragment.context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL // Ha de ser VERTICAL para que se muestren en filas.
            recycler.layoutManager = linearLayoutManager

            recycler.adapter = ContactAdapter(contactList) { contact ->
                // En este lambda cargamos el FragmentDetail y pasamos el contacto mediante un "Bundle"
                val fragment = DetailFragment()

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()

                fragment.arguments = bundleOf(DetailFragment.CONTACT_DATA to contact)
            }
        }
    }
}