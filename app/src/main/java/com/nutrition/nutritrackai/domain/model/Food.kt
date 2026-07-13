package com.nutrition.nutritrackai.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(

    val name: String,

    val imageUrl: String?,

    val calories: Double,

    val protein: Double,

    val carbs: Double,

    val fat: Double

) : Parcelable