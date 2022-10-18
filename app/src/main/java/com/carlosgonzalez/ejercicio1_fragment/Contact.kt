package com.carlosgonzalez.ejercicio1_fragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact( val name : String,
                    val urlProfilePicture : String,
                    val phoneNumber : String,
                    val email : String)
    : Parcelable {}