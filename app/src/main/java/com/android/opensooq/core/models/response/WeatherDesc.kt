package com.android.opensooq.core.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "WeatherDesc")
data class WeatherDesc (
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idWeatherDesc")
    var idWeatherDesc: Int? = null,

    @SerializedName("value")
    @ColumnInfo(name = "value")
    @Expose
    var value: String? = null
)