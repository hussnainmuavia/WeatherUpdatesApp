package com.android.opensooq.core.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.opensooq.core.typeConverters.MonthTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ClimateAverage")
data class ClimateAverage (

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @SerializedName("month")
    @ColumnInfo(name = "month")
    @TypeConverters(MonthTypeConverter::class)
    @Expose
    var month: ArrayList<Month>? = null
)