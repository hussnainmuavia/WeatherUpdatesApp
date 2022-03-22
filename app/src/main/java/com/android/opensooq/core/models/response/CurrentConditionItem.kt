package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentConditionItem(
	val precipMM: String? = null,
	val observationTime: String? = null,
	val weatherDesc: List<WeatherDescItem?>? = null,
	val visibility: String? = null,
	val weatherCode: String? = null,
	val feelsLikeF: String? = null,
	val pressure: String? = null,
	val tempC: String? = null,
	val tempF: String? = null,
	val cloudcover: String? = null,
	val windspeedMiles: String? = null,
	val winddirDegree: String? = null,
	val feelsLikeC: String? = null,
	val windspeedKmph: String? = null,
	val humidity: String? = null,
	val visibilityMiles: String? = null,
	val precipInches: String? = null,
	val uvIndex: String? = null,
	val winddir16Point: String? = null,
	val weatherIconUrl: List<WeatherIconUrlItem?>? = null,
	val pressureInches: String? = null
) : Parcelable