package com.android.opensooq.core.models.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Month")
data class Month(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @SerializedName("index")
    @ColumnInfo(name = "index")
    @Expose
    var index: String? = null,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @Expose
    var name: String? = null,

    @SerializedName("avgMinTemp")
    @ColumnInfo(name = "avgMinTemp")
    @Expose
    var avgMinTemp: String? = null,

    @SerializedName("avgMinTemp_F")
    @ColumnInfo(name = "avgMinTemp_F")
    @Expose
    var avgMinTempF: String? = null,

    @SerializedName("absMaxTemp")
    @ColumnInfo(name = "absMaxTemp")
    @Expose
    var absMaxTemp: String? = null,

    @SerializedName("absMaxTemp_F")
    @ColumnInfo(name = "absMaxTemp_F")
    @Expose
    var absMaxTempF: String? = null,

    @SerializedName("avgDailyRainfall")
    @ColumnInfo(name = "avgDailyRainfall")
    @Expose
    var avgDailyRainfall: String? = null
): Parcelable