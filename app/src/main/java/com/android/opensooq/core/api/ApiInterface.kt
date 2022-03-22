package com.android.opensooq.core.api

import com.android.opensooq.core.api.ApiEndPoints.BASE_URL_POST_FIX
import com.android.opensooq.core.models.response.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(BASE_URL_POST_FIX)
    fun getSearchResults(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("num_of_days") numOfDays: String,
        @Query("tp") tp: String,
        @Query("format") format: String
    ): Observable<SearchResult>
}