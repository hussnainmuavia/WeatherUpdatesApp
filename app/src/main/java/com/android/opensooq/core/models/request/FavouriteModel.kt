package com.android.opensooq.core.models.request

import com.android.opensooq.core.models.response.SearchResult

data class FavouriteModel(
    var query: String? = null,
    var type: String? = null,
    var searchResult: SearchResult? = null
)