package com.android.opensooq.core.models.response

import android.os.Parcelable
import androidx.room.*
import com.android.opensooq.core.typeConverters.HourlyTypeConverter
import com.android.opensooq.core.typeConverters.RequestTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Weather")
data class Weather(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idWeather")
    var idWeather: Int? = null,

    @SerializedName("date")
    @ColumnInfo(name = "astronomy")
    @Expose
    var date: String? = null,

    @SerializedName("astronomy")
    @ColumnInfo(name = "astronomy")
    @Expose
    @Ignore
    var astronomy: ArrayList<Astronomy>? = null,

    @SerializedName("maxtempC")
    @ColumnInfo(name = "maxtempC")
    @Expose
    var maxtempC: String? = null,

    @SerializedName("maxtempF")
    @ColumnInfo(name = "maxtempF")
    @Expose
    var maxtempF: String? = null,

    @SerializedName("mintempC")
    @ColumnInfo(name = "mintempC")
    @Expose
    var mintempC: String? = null,

    @SerializedName("mintempF")
    @ColumnInfo(name = "mintempF")
    @Expose
    var mintempF: String? = null,

    @SerializedName("avgtempC")
    @ColumnInfo(name = "avgtempC")
    @Expose
    var avgtempC: String? = null,

    @SerializedName("avgtempF")
    @ColumnInfo(name = "avgtempF")
    @Expose
    var avgtempF: String? = null,

    @SerializedName("totalSnow_cm")
    @ColumnInfo(name = "totalSnow_cm")
    @Expose
    var totalSnowCm: String? = null,

    @SerializedName("sunHour")
    @ColumnInfo(name = "sunHour")
    @Expose
    var sunHour: String? = null,

    @SerializedName("uvIndex")
    @ColumnInfo(name = "uvIndex")
    @Expose
    var uvIndex: String? = null,

    @SerializedName("hourly")
    @ColumnInfo(name = "hourly")
    @Expose
    @TypeConverters(HourlyTypeConverter::class)
    var hourly: ArrayList<Hourly>? = null
) : Parcelable