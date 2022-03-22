package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherItem(
	val date: String? = null,
	val sunHour: String? = null,
	val mintempF: String? = null,
	val avgtempF: String? = null,
	val mintempC: String? = null,
	val totalSnowCm: String? = null,
	val maxtempF: String? = null,
	val hourly: List<HourlyItem?>? = null,
	val avgtempC: String? = null,
	val astronomy: List<AstronomyItem?>? = null,
	val uvIndex: String? = null,
	val maxtempC: String? = null
) : Parcelable