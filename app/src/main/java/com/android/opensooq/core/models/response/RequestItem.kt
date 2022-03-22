package com.android.opensooq.core.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestItem(
	val query: String? = null,
	val type: String? = null
) : Parcelable