package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
	val request: ArrayList<RequestItem?>? = null,
	val currentCondition: ArrayList<CurrentConditionItem?>? = null,
	val weather: ArrayList<WeatherItem?>? = null,
	val climateAverages: ArrayList<ClimateAveragesItem?>? = null
) : Parcelable