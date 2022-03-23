package com.android.opensooq.core.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Astronomy")
data class Astronomy(

    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idAstronomy")
    var idAstronomy: Int? = null,

    @SerializedName("sunrise")
    @ColumnInfo(name = "sunrise")
    @Expose
    var sunrise: String? = null,

    @SerializedName("sunset")
    @ColumnInfo(name = "sunset")
    @Expose
    var sunset: String? = null,

    @SerializedName("moonrise")
    @ColumnInfo(name = "moonrise")
    @Expose
    var moonrise: String? = null,

    @SerializedName("moonset")
    @ColumnInfo(name = "moonset")
    @Expose
    var moonset: String? = null,

    @SerializedName("moon_phase")
    @ColumnInfo(name = "moon_phase")
    @Expose
    var moonPhase: String? = null,

    @SerializedName("moon_illumination")
    @ColumnInfo(name = "moon_illumination")
    @Expose
    var moonIllumination: String? = null
)