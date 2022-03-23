package com.android.opensooq.core.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Request")
data class Request (
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idRequest")
    var idRequest: Int? = null,

    @SerializedName("type")
    @ColumnInfo(name = "type")
    @Expose
    var type: String? = null,

    @SerializedName("query")
    @ColumnInfo(name = "query")
    @Expose
    var query: String? = null
)