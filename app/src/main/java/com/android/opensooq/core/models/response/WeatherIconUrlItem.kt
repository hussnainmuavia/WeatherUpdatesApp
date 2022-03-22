package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherIconUrlItem(
	val value: String? = null
) : Parcelable