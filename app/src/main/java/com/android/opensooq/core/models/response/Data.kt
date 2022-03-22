package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("request")
    @Expose
    var request: List<Request>? = null,

    @SerializedName("current_condition")
    @Expose
    var currentCondition: List<CurrentCondition>? = null,

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null,

    @SerializedName("ClimateAverages")
    @Expose
    var climateAverages: List<ClimateAverage>? = null
)