package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AstronomyItem(
	val moonset: String? = null,
	val moonIllumination: String? = null,
	val sunrise: String? = null,
	val moonPhase: String? = null,
	val sunset: String? = null,
	val moonrise: String? = null
) : Parcelable