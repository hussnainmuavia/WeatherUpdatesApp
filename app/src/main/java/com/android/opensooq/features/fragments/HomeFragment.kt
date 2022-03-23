package com.android.opensooq.features.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.opensooq.R
import com.android.opensooq.core.dao.OpenSooqDatabase
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.core.models.response.SearchResult
import com.android.opensooq.core.platform.BaseFragment
import com.android.opensooq.core.utils.State
import com.android.opensooq.features.adapters.FavouriteCitiesAdapter
import com.android.opensooq.features.callbacks.OnItemClickListener
import com.android.opensooq.features.viewmodels.HomeViewModel


class HomeFragment : BaseFragment(), OnItemClickListener {

    private var KEY_AMMAN: String = "Amman"
    private var KEY_IRBID: String = "Irbid"
    private var KEY_AQABA: String = "Aqaba"

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var openSooqDatabase: OpenSooqDatabase
    private lateinit var mSearchResult: SearchResult
    private lateinit var mFavouriteCities: FavouriteCitiesAdapter
    private var mFavourites: ArrayList<FavouriteModel> = ArrayList()

    private lateinit var mView: View
    private lateinit var rvCityCardsView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var etSearch: EditText
    private lateinit var tvNoResultFound: TextView
    private lateinit var pullToRefresh: SwipeRefreshLayout

    override fun layoutId() = R.layout.fragment_home

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        rvCityCardsView = mView?.findViewById(R.id.rvCityCardsView)
        mProgressBar = mView?.findViewById(R.id.progressBar)
        etSearch = mView?.findViewById(R.id.etSearch)
        tvNoResultFound = mView?.findViewById(R.id.tvNoResultFound)
        pullToRefresh = mView?.findViewById(R.id.pullToRefresh)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)

        initDatabaseInstance()
        initViewModels()
        observeStateLoader()
        initAdapterViews()
        observeSearchResult()
        searchTextChangedListener()
        etSearchEditorActionListener()
        getDataFromRepoOrNetwork()
        swipeToRefresh()
    }

    private fun initViewModels() {
        mHomeViewModel =
            ViewModelProviders
                .of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    private fun observeStateLoader() {
        mHomeViewModel?.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                State.LOADING -> mProgressBar.visibility = View.VISIBLE
                State.ERROR -> mProgressBar.visibility = View.GONE
                State.DONE -> mProgressBar.visibility = View.GONE
            }
        })
    }

    private fun initDatabaseInstance() {
        this.context?.let {
            OpenSooqDatabase.getDatabaseInstance(it)?.let { dbInstance ->
                openSooqDatabase = dbInstance
            }
        }
    }

    private fun initAdapterViews() {
        mFavouriteCities = FavouriteCitiesAdapter(requireContext())
        rvCityCardsView?.apply {
            layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            adapter = mFavouriteCities
        }
        mFavouriteCities.registerCallback(this@HomeFragment)
    }

    override fun onItemClickListener(position: Int, any: Any) {
        val favouriteModel = any as FavouriteModel
        addFragment(CityDetailFragment.newInstance(favouriteModel))
    }

    override fun onMoreClickListener(position: Int, any: Any) {
        val favouriteModel = any as FavouriteModel
        addFragment(CityDetailFragment.newInstance(favouriteModel))
    }

    private fun swipeToRefresh(){
        pullToRefresh.setOnRefreshListener {
            mFavourites = openSooqDatabase.openSooqDao().favouriteCities as ArrayList<FavouriteModel>
            mFavourites?.forEach {
                mHomeViewModel.getSearchResults(it.query.toString())
            }

            pullToRefresh.isRefreshing = false
        }
    }

    private fun getSearchService(query: String) {
        mHomeViewModel.getSearchResults(query)
    }

    private fun observeSearchResult() {
        mHomeViewModel.mSearchResult.observe(this,
            Observer<SearchResult> { search ->
                if (search?.data?.currentCondition != null) {
                    showHide(isVisible = true)
                    mSearchResult = search
                    if (!mFavourites.contains(prepareFavouriteModel(search))) {
                        val favouriteModel = prepareFavouriteModel(search)
                        mFavourites.add(favouriteModel)
                        openSooqDatabase.openSooqDao().insertFavouriteCity(favouriteModel)
                        mFavouriteCities.setSearchResults(mFavourites)
                        if (etSearch.text?.isNotEmpty() == true && search.data != null) {
                            notify(getString(R.string.message_result_added))
                        }
                    } else {
                        notify(getString(R.string.message_result_already_added))
                    }
                } else {
                    showHide(isVisible = false)
                }
            })
    }

    private fun prepareFavouriteModel(searchResult: SearchResult): FavouriteModel {
        val favouriteModel = FavouriteModel()
        favouriteModel.query = searchResult.data?.request?.get(0)?.query
        favouriteModel.type = searchResult.data?.request?.get(0)?.type
        favouriteModel.searchResult = searchResult
        return favouriteModel
    }

    private fun searchTextChangedListener() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(editable: Editable) {
                val search = editable.toString()
                if (search?.isNotEmpty() && search?.length >= 2) {
                    getSearchService(search)
                }
            }
        })
    }

    private fun etSearchEditorActionListener() {
        etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val search = etSearch.text.toString()
                if (search.isNotEmpty() && search.length >= 2) {
                    hideKeyboard()
                    getSearchService(search)
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun showHide(isVisible: Boolean){
        when(isVisible){
            true -> {
                rvCityCardsView.visibility = View.VISIBLE
                tvNoResultFound.visibility = View.GONE
            }
            false -> {
                if (mFavouriteCities?.itemCount > 0) {
                    notify(getString(R.string.message_no_result_found))
                } else {
                    rvCityCardsView.visibility = View.GONE
                    tvNoResultFound.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getDataFromRepoOrNetwork(){
        mFavourites = openSooqDatabase.openSooqDao().favouriteCities as ArrayList<FavouriteModel>
        if (mFavourites.isNotEmpty()){
            showHide(isVisible = true)
            mFavouriteCities.setSearchResults(mFavourites)
        } else {
            getSearchService(KEY_AMMAN)
            getSearchService(KEY_IRBID)
            getSearchService(KEY_AQABA)
        }
    }

    override fun onDestroy() {
        openSooqDatabase?.destroyInstance()
        super.onDestroy()
    }
}
