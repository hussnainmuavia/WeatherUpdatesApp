package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Request (
    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("query")
    @Expose
    var query: String? = null
)