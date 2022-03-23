package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("astronomy")
    @Expose
    var astronomy: List<Astronomy>? = null,

    @SerializedName("maxtempC")
    @Expose
    var maxtempC: String? = null,

    @SerializedName("maxtempF")
    @Expose
    var maxtempF: String? = null,

    @SerializedName("mintempC")
    @Expose
    var mintempC: String? = null,

    @SerializedName("mintempF")
    @Expose
    var mintempF: String? = null,

    @SerializedName("avgtempC")
    @Expose
    var avgtempC: String? = null,

    @SerializedName("avgtempF")
    @Expose
    var avgtempF: String? = null,

    @SerializedName("totalSnow_cm")
    @Expose
    var totalSnowCm: String? = null,

    @SerializedName("sunHour")
    @Expose
    var sunHour: String? = null,

    @SerializedName("uvIndex")
    @Expose
    var uvIndex: String? = null,

    @SerializedName("hourly")
    @Expose
    var hourly: ArrayList<Hourly>? = null
)