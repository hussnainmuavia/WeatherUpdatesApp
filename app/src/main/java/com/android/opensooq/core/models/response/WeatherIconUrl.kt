package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherIconUrl (
    @SerializedName("value")
    @Expose
    var value: String? = null
)