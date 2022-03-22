package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HourlyItem(
	val weatherCode: String? = null,
	val chanceofhightemp: String? = null,
	val feelsLikeF: String? = null,
	val cloudcover: String? = null,
	val windChillC: String? = null,
	val windspeedMiles: String? = null,
	val winddirDegree: String? = null,
	val dewPointC: String? = null,
	val chanceofthunder: String? = null,
	val chanceoffrost: String? = null,
	val dewPointF: String? = null,
	val humidity: String? = null,
	val winddir16Point: String? = null,
	val windChillF: String? = null,
	val weatherIconUrl: List<WeatherIconUrlItem?>? = null,
	val tempF: String? = null,
	val precipMM: String? = null,
	val weatherDesc: List<WeatherDescItem?>? = null,
	val chanceofrain: String? = null,
	val chanceofovercast: String? = null,
	val visibility: String? = null,
	val pressure: String? = null,
	val chanceofsunshine: String? = null,
	val windGustMiles: String? = null,
	val chanceofsnow: String? = null,
	val feelsLikeC: String? = null,
	val windspeedKmph: String? = null,
	val windGustKmph: String? = null,
	val chanceoffog: String? = null,
	val visibilityMiles: String? = null,
	val heatIndexC: String? = null,
	val time: String? = null,
	val precipInches: String? = null,
	val chanceofwindy: String? = null,
	val uvIndex: String? = null,
	val tempC: String? = null,
	val pressureInches: String? = null,
	val heatIndexF: String? = null,
	val chanceofremdry: String? = null
) : Parcelable