package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentCondition(
    @SerializedName("observation_time")
    @Expose
    var observationTime: String? = null,

    @SerializedName("temp_C")
    @Expose
    var tempC: String? = null,

    @SerializedName("temp_F")
    @Expose
    var tempF: String? = null,

    @SerializedName("weatherCode")
    @Expose
    var weatherCode: String? = null,

    @SerializedName("weatherIconUrl")
    @Expose
    var weatherIconUrl: List<WeatherIconUrl>? = null,

    @SerializedName("weatherDesc")
    @Expose
    var weatherDesc: List<WeatherDesc>? = null,

    @SerializedName("windspeedMiles")
    @Expose
    var windspeedMiles: String? = null,

    @SerializedName("windspeedKmph")
    @Expose
    var windspeedKmph: String? = null,

    @SerializedName("winddirDegree")
    @Expose
    var winddirDegree: String? = null,

    @SerializedName("winddir16Point")
    @Expose
    var winddir16Point: String? = null,

    @SerializedName("precipMM")
    @Expose
    var precipMM: String? = null,

    @SerializedName("precipInches")
    @Expose
    var precipInches: String? = null,

    @SerializedName("humidity")
    @Expose
    var humidity: String? = null,

    @SerializedName("visibility")
    @Expose
    var visibility: String? = null,

    @SerializedName("visibilityMiles")
    @Expose
    var visibilityMiles: String? = null,

    @SerializedName("pressure")
    @Expose
    var pressure: String? = null,

    @SerializedName("pressureInches")
    @Expose
    var pressureInches: String? = null,

    @SerializedName("cloudcover")
    @Expose
    var cloudcover: String? = null,

    @SerializedName("FeelsLikeC")
    @Expose
    var feelsLikeC: String? = null,

    @SerializedName("FeelsLikeF")
    @Expose
    var feelsLikeF: String? = null,

    @SerializedName("uvIndex")
    @Expose
    var uvIndex: String? = null
)