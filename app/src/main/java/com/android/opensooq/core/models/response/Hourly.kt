package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("time")
    @Expose
    var time: String? = null,

    @SerializedName("tempC")
    @Expose
    var tempC: String? = null,

    @SerializedName("tempF")
    @Expose
    var tempF: String? = null,

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

    @SerializedName("weatherCode")
    @Expose
    var weatherCode: String? = null,

    @SerializedName("weatherIconUrl")
    @Expose
    var weatherIconUrl: List<WeatherIconUrl>? = null,

    @SerializedName("weatherDesc")
    @Expose
    var weatherDesc: List<WeatherDesc>? = null,

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

    @SerializedName("HeatIndexC")
    @Expose
    var heatIndexC: String? = null,

    @SerializedName("HeatIndexF")
    @Expose
    var heatIndexF: String? = null,

    @SerializedName("DewPointC")
    @Expose
    var dewPointC: String? = null,

    @SerializedName("DewPointF")
    @Expose
    var dewPointF: String? = null,

    @SerializedName("WindChillC")
    @Expose
    var windChillC: String? = null,

    @SerializedName("WindChillF")
    @Expose
    var windChillF: String? = null,

    @SerializedName("WindGustMiles")
    @Expose
    var windGustMiles: String? = null,

    @SerializedName("WindGustKmph")
    @Expose
    var windGustKmph: String? = null,

    @SerializedName("FeelsLikeC")
    @Expose
    var feelsLikeC: String? = null,

    @SerializedName("FeelsLikeF")
    @Expose
    var feelsLikeF: String? = null,

    @SerializedName("chanceofrain")
    @Expose
    var chanceofrain: String? = null,

    @SerializedName("chanceofremdry")
    @Expose
    var chanceofremdry: String? = null,

    @SerializedName("chanceofwindy")
    @Expose
    var chanceofwindy: String? = null,

    @SerializedName("chanceofovercast")
    @Expose
    var chanceofovercast: String? = null,

    @SerializedName("chanceofsunshine")
    @Expose
    var chanceofsunshine: String? = null,

    @SerializedName("chanceoffrost")
    @Expose
    var chanceoffrost: String? = null,

    @SerializedName("chanceofhightemp")
    @Expose
    var chanceofhightemp: String? = null,

    @SerializedName("chanceoffog")
    @Expose
    var chanceoffog: String? = null,

    @SerializedName("chanceofsnow")
    @Expose
    var chanceofsnow: String? = null,

    @SerializedName("chanceofthunder")
    @Expose
    var chanceofthunder: String? = null,

    @SerializedName("uvIndex")
    @Expose
    var uvIndex: String? = null
)