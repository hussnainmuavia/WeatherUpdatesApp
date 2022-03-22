package com.android.opensooq.features.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.opensooq.core.api.ApiInterface
import com.android.opensooq.core.models.response.SearchResult
import com.android.opensooq.core.utils.Constants.API_ERROR
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

    private fun getSearch(
        key: String,
        query: String,
        numOfDays: String,
        tp: String,
        format: String
    ) {
        compositeDisposable.add(
            apiInterface.getSearchResults(key, query, numOfDays, tp, format)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { updateState(State.LOADING) }
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

    fun getSearchResults(
        key: String,
        query: String,
        numOfDays: String,
        tp: String,
        format: String
    ) {
        getSearch(key, query, numOfDays, tp, format)
    }

    fun getState(): LiveData<State> {
        return this.mState
    }

    private fun updateState(state: State) {
        this.mState.value = state
    }
}