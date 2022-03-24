package com.android.opensooq.core.api

import com.android.opensooq.core.api.ApiEndPoints.BASE_URL_POST_FIX
import com.android.opensooq.core.models.response.SearchResult
import com.android.opensooq.core.utils.Constants.KEY_FORMAT
import com.android.opensooq.core.utils.Constants.KEY_KEY
import com.android.opensooq.core.utils.Constants.KEY_NUMBER_OF_DAYS
import com.android.opensooq.core.utils.Constants.KEY_QUERY
import com.android.opensooq.core.utils.Constants.KEY_TIME_PERIOD
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(BASE_URL_POST_FIX)
    fun getSearchResults(
        @Query(KEY_KEY) key: String,
        @Query(KEY_QUERY) query: String,
        @Query(KEY_NUMBER_OF_DAYS) numOfDays: String,
        @Query(KEY_TIME_PERIOD) tp: String,
        @Query(KEY_FORMAT) format: String
    ): Observable<SearchResult>
}