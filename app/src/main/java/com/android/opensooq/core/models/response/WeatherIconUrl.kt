package com.android.opensooq.core.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "WeatherIconUrl")
data class WeatherIconUrl(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idWeatherIconUrl")
    var idWeatherIconUrl: Int? = null,

    @SerializedName("value")
    @ColumnInfo(name = "value")
    @Expose
    var value: String? = null
)