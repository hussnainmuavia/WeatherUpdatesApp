package com.android.opensooq.core.models.request

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.opensooq.core.models.response.SearchResult
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "FavouriteModel")
data class FavouriteModel(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "idFavouriteModel")
    var idFavouriteModel: Int? = null,

    @ColumnInfo(name = "query")
    var query: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @Embedded
    var searchResult: SearchResult? = null
) : Parcelable