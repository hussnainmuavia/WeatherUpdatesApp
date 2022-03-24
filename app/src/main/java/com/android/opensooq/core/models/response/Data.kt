package com.android.opensooq.core.models.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.opensooq.core.typeConverters.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Data")
data class Data(

    @SerializedName("idData")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    var idData: Int? = null,

    @SerializedName("request")
    @ColumnInfo(name = "request")
    @TypeConverters(RequestTypeConverter::class)
    @Expose
    var request: List<Request>? = null,

    @SerializedName("current_condition")
    @ColumnInfo(name = "current_condition")
    @TypeConverters(CurrentConditionTypeConvector::class)
    @Expose
    var currentCondition: List<CurrentCondition>? = null,

    @SerializedName("weather")
    @ColumnInfo(name = "weather")
    @TypeConverters(WeatherTypeConverter::class)
    @Expose
    var weather: List<Weather>? = null,

    @SerializedName("ClimateAverages")
    @ColumnInfo(name = "ClimateAverages")
    @TypeConverters(ClimateAverageTypeConverter::class)
    @Expose
    var climateAverages: List<ClimateAverage>? = null
) : Parcelable