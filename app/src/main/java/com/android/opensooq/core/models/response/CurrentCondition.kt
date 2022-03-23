package com.android.opensooq.core.models.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.opensooq.core.typeConverters.RequestTypeConverter
import com.android.opensooq.core.typeConverters.WeatherDescTypeConverter
import com.android.opensooq.core.typeConverters.WeatherIconUrlTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "CurrentCondition")
data class CurrentCondition(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idCurrentCondition")
    var idCurrentCondition: Int? = null,

    @SerializedName("observation_time")
    @ColumnInfo(name = "observation_time")
    @Expose
    var observationTime: String? = null,

    @SerializedName("temp_C")
    @ColumnInfo(name = "temp_C")
    @Expose
    var tempC: String? = null,

    @SerializedName("temp_F")
    @ColumnInfo(name = "temp_F")
    @Expose
    var tempF: String? = null,

    @SerializedName("weatherCode")
    @ColumnInfo(name = "weatherCode")
    @Expose
    var weatherCode: String? = null,

    @SerializedName("weatherIconUrl")
    @ColumnInfo(name = "weatherIconUrl")
    @TypeConverters(WeatherIconUrlTypeConverter::class)
    @Expose
    var weatherIconUrl: ArrayList<WeatherIconUrl>? = null,

    @SerializedName("weatherDesc")
    @ColumnInfo(name = "weatherDesc")
    @TypeConverters(WeatherDescTypeConverter::class)
    @Expose
    var weatherDesc: ArrayList<WeatherDesc>? = null,

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

    @SerializedName("FeelsLikeC")
    @ColumnInfo(name = "FeelsLikeC")
    @Expose
    var feelsLikeC: String? = null,

    @SerializedName("FeelsLikeF")
    @ColumnInfo(name = "FeelsLikeF")
    @Expose
    var feelsLikeF: String? = null,

    @SerializedName("uvIndex")
    @ColumnInfo(name = "uvIndex")
    @Expose
    var uvIndex: String? = null
): Parcelable