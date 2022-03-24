package com.android.opensooq.core.models.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Hourly")
data class Hourly(

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idHourly")
    var idHourly: Int? = null,

    @SerializedName("time")
    @ColumnInfo(name = "time")
    @Expose
    var time: String? = null,

    @SerializedName("tempC")
    @ColumnInfo(name = "tempC")
    @Expose
    var tempC: String? = null,

    @SerializedName("tempF")
    @ColumnInfo(name = "tempF")
    @Expose
    var tempF: String? = null,

    @SerializedName("windspeedMiles")
    @ColumnInfo(name = "windspeedMiles")
    @Expose
    var windspeedMiles: String? = null,

    @SerializedName("windspeedKmph")
    @ColumnInfo(name = "windspeedKmph")
    @Expose
    var windspeedKmph: String? = null,

    @SerializedName("winddirDegree")
    @ColumnInfo(name = "winddirDegree")
    @Expose
    var winddirDegree: String? = null,

    @SerializedName("winddir16Point")
    @ColumnInfo(name = "winddir16Point")
    @Expose
    var winddir16Point: String? = null,

    @SerializedName("weatherCode")
    @ColumnInfo(name = "weatherCode")
    @Expose
    var weatherCode: String? = null,

    @SerializedName("weatherIconUrl")
    @ColumnInfo(name = "weatherIconUrl")
    @Expose
    var weatherIconUrl: ArrayList<WeatherIconUrl>? = null,

    @SerializedName("weatherDesc")
    @ColumnInfo(name = "weatherDesc")
    @Expose
    var weatherDesc: ArrayList<WeatherDesc>? = null,

    @SerializedName("precipMM")
    @ColumnInfo(name = "precipMM")
    @Expose
    var precipMM: String? = null,

    @SerializedName("precipInches")
    @ColumnInfo(name = "precipInches")
    @Expose
    var precipInches: String? = null,

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    @Expose
    var humidity: String? = null,

    @SerializedName("visibility")
    @ColumnInfo(name = "visibility")
    @Expose
    var visibility: String? = null,

    @SerializedName("visibilityMiles")
    @ColumnInfo(name = "visibilityMiles")
    @Expose
    var visibilityMiles: String? = null,

    @SerializedName("pressure")
    @ColumnInfo(name = "pressure")
    @Expose
    var pressure: String? = null,

    @SerializedName("pressureInches")
    @ColumnInfo(name = "pressureInches")
    @Expose
    var pressureInches: String? = null,

    @SerializedName("cloudcover")
    @ColumnInfo(name = "cloudcover")
    @Expose
    var cloudcover: String? = null,

    @SerializedName("HeatIndexC")
    @ColumnInfo(name = "HeatIndexC")
    @Expose
    var heatIndexC: String? = null,

    @SerializedName("HeatIndexF")
    @ColumnInfo(name = "HeatIndexF")
    @Expose
    var heatIndexF: String? = null,

    @SerializedName("DewPointC")
    @ColumnInfo(name = "DewPointC")
    @Expose
    var dewPointC: String? = null,

    @SerializedName("DewPointF")
    @ColumnInfo(name = "DewPointF")
    @Expose
    var dewPointF: String? = null,

    @SerializedName("WindChillC")
    @ColumnInfo(name = "WindChillC")
    @Expose
    var windChillC: String? = null,

    @SerializedName("WindChillF")
    @ColumnInfo(name = "WindChillF")
    @Expose
    var windChillF: String? = null,

    @SerializedName("WindGustMiles")
    @ColumnInfo(name = "WindGustMiles")
    @Expose
    var windGustMiles: String? = null,

    @SerializedName("WindGustKmph")
    @ColumnInfo(name = "WindGustKmph")
    @Expose
    var windGustKmph: String? = null,

    @SerializedName("FeelsLikeC")
    @ColumnInfo(name = "FeelsLikeC")
    @Expose
    var feelsLikeC: String? = null,

    @SerializedName("FeelsLikeF")
    @ColumnInfo(name = "FeelsLikeF")
    @Expose
    var feelsLikeF: String? = null,

    @SerializedName("chanceofrain")
    @ColumnInfo(name = "chanceofrain")
    @Expose
    var chanceofrain: String? = null,

    @SerializedName("chanceofremdry")
    @ColumnInfo(name = "chanceofremdry")
    @Expose
    var chanceofremdry: String? = null,

    @SerializedName("chanceofwindy")
    @ColumnInfo(name = "chanceofwindy")
    @Expose
    var chanceofwindy: String? = null,

    @SerializedName("chanceofovercast")
    @ColumnInfo(name = "chanceofovercast")
    @Expose
    var chanceofovercast: String? = null,

    @SerializedName("chanceofsunshine")
    @ColumnInfo(name = "chanceofsunshine")
    @Expose
    var chanceofsunshine: String? = null,

    @SerializedName("chanceoffrost")
    @ColumnInfo(name = "chanceoffrost")
    @Expose
    var chanceoffrost: String? = null,

    @SerializedName("chanceofhightemp")
    @ColumnInfo(name = "chanceofhightemp")
    @Expose
    var chanceofhightemp: String? = null,

    @SerializedName("chanceoffog")
    @ColumnInfo(name = "chanceoffog")
    @Expose
    var chanceoffog: String? = null,

    @SerializedName("chanceofsnow")
    @ColumnInfo(name = "chanceofsnow")
    @Expose
    var chanceofsnow: String? = null,

    @SerializedName("chanceofthunder")
    @ColumnInfo(name = "chanceofthunder")
    @Expose
    var chanceofthunder: String? = null,

    @SerializedName("uvIndex")
    @ColumnInfo(name = "uvIndex")
    @Expose
    var uvIndex: String? = null
): Parcelable