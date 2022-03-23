package com.android.opensooq.core.models.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "SearchResult")
data class SearchResult(
    @PrimaryKey
    @ColumnInfo(name = "idSearchResult")
    var idSearchResult: Int? = null,

    @SerializedName("data")
    @Expose
    @Embedded
    var data: Data? = null
): Parcelable