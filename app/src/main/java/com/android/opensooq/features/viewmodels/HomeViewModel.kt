package com.android.opensooq.features.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.opensooq.core.api.ApiInterface
import com.android.opensooq.core.models.response.SearchResult
import com.android.opensooq.core.utils.Constants.API_ERROR
import com.android.opensooq.core.utils.Constants.API_KEY
import com.android.opensooq.core.utils.Constants.FORMAT
import com.android.opensooq.core.utils.Constants.NUM_OF_DAYS
import com.android.opensooq.core.utils.Constants.TIME_PERIOD
import com.android.opensooq.core.utils.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val apiInterface: ApiInterface) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var mSearchResult: MutableLiveData<SearchResult> = MutableLiveData()
    var mState: MutableLiveData<State> = MutableLiveData()

    /*
    * Search API service to search the city or data.
    * Following, providing the default arguments to the API for demonstration and make call
    * simple and easy to use.
    * We can add/pass these arguments according to the need.
    * */
    private fun getSearch(query: String) {
        compositeDisposable.add(
            apiInterface.getSearchResults(API_KEY, query, NUM_OF_DAYS, TIME_PERIOD, FORMAT)
                /*
                * Be notified on the main thread
                * */
                .observeOn(AndroidSchedulers.mainThread())
                /*
               * Run on a background thread
               * */
                .subscribeOn(Schedulers.io())
                /*
                * Run on subscribing
                * */
                .doOnSubscribe { updateState(State.LOADING) }
                /*
                * Run when the calls end.
                * */
                .doFinally { updateState(State.DONE) }
                .subscribe({ response ->
                    handleSearchResponse(response)
                    updateState(State.DONE)
                }, { error ->
                    handleSearchError(error)
                    updateState(State.DONE)
                })
        )
    }

    public override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    private fun handleSearchResponse(searchResult: SearchResult) {
        mSearchResult.value = searchResult
    }

    private fun handleSearchError(error: Throwable) {
        Log.d(API_ERROR, error.toString())
    }

    fun getSearchResults(query: String) {
        getSearch(query)
    }

    fun getState(): LiveData<State> {
        return this.mState
    }

    private fun updateState(state: State) {
        this.mState.value = state
    }
}