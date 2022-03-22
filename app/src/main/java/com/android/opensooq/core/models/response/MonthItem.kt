package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MonthItem(
	val absMaxTemp: String? = null,
	val avgMinTempF: String? = null,
	val name: String? = null,
	val index: String? = null,
	val absMaxTempF: String? = null,
	val avgDailyRainfall: String? = null,
	val avgMinTemp: String? = null
) : Parcelable