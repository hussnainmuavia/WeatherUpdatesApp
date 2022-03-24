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
import com.android.opensooq.core.dao.OpenSooqDao
import com.android.opensooq.core.dao.OpenSooqDatabase
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.core.models.response.SearchResult
import com.android.opensooq.core.platform.BaseFragment
import com.android.opensooq.core.utils.Constants.KEY_AMMAN
import com.android.opensooq.core.utils.Constants.KEY_AQABA
import com.android.opensooq.core.utils.Constants.KEY_IRBID
import com.android.opensooq.core.utils.State
import com.android.opensooq.features.adapters.FavouriteCitiesAdapter
import com.android.opensooq.features.callbacks.OnItemClickListener
import com.android.opensooq.features.viewmodels.HomeViewModel


class HomeFragment : BaseFragment(), OnItemClickListener {

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var openSooqDatabase: OpenSooqDatabase
    private lateinit var mOpenSooqDao: OpenSooqDao
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

    /*
    * Initializing view model for the Fragment
    * It is just a demonstration to show case the operations with ViewModel and APIs inside it.
    * */
    private fun initViewModels() {
        mHomeViewModel =
            ViewModelProviders
                .of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    /*
    * observing the State for the mProgressBar on the bases of API call subscriptions
    * */
    private fun observeStateLoader() {
        mHomeViewModel?.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                State.LOADING -> mProgressBar.visibility = View.VISIBLE
                State.ERROR -> mProgressBar.visibility = View.GONE
                State.DONE -> mProgressBar.visibility = View.GONE
            }
        })
    }

    /*
    * Initializing database instance here for the Database related operations
    * */
    private fun initDatabaseInstance() {
        this.context?.let {
            OpenSooqDatabase.getDatabaseInstance(it)?.let { dbInstance ->
                openSooqDatabase = dbInstance
            }
        }
        mOpenSooqDao = openSooqDatabase.openSooqDao()
    }

    /*
    * Initializing the Favourite cities adapter view.
    * registering callback and adding favourite city adapter.
    * */
    private fun initAdapterViews() {
        mFavouriteCities = FavouriteCitiesAdapter(requireContext())
        rvCityCardsView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mFavouriteCities
        }
        mFavouriteCities.registerCallback(this@HomeFragment)
    }

    /*
    * It will listen the click over the item of the adapter view and handle
    * further navigation towards the details fragment
    * */
    override fun onItemClickListener(position: Int, any: Any) {
        val favouriteModel = any as FavouriteModel
        addFragment(CityDetailFragment.newInstance(favouriteModel))
    }

    /*
    * It is for the demonstration purpose to show the details of the selected city updates
    * we can add/do further actions/details inside this @fragment CityDetailFragment
    * */
    override fun onMoreClickListener(position: Int, any: Any) {
        val favouriteModel = any as FavouriteModel
        addFragment(CityDetailFragment.newInstance(favouriteModel))
    }

    /*
    * Pull to refresh is to always have a updated data
    * */
    private fun swipeToRefresh(){
        pullToRefresh.setOnRefreshListener {
            mFavourites = mOpenSooqDao.favouriteCities as ArrayList<FavouriteModel>
            mFavourites?.forEach {
                getSearchService(it.query.toString())
            }
            pullToRefresh.isRefreshing = false
        }
    }

    /*
    * API call for searching the query.
    * Here in this function call passing the query only
    * to search city for weather updates from the API
    * So the Query will be String input by user.
    * @param query String
    * */
    private fun getSearchService(query: String) {
        mHomeViewModel.getSearchResults(query)
    }

    /*
    * Observing the @param mSearchResult as MutableLiveData when getSearch API will call.
    * It will observe the API response and pass the data to [setSearchResult] function.
    * */
    private fun observeSearchResult() {
        mHomeViewModel.mSearchResult.observe(this,
            Observer<SearchResult> { search ->
                val data = search?.data
                if (data?.currentCondition != null) {
                    showHide(isVisible = true)
                    mSearchResult = search
                    setSearchResult(search)
                } else {
                    showHide(isVisible = false)
                }
            })
    }

   /*
   * This to set SearchResult to the adapter
   * Checking either the records are in database or not.
   * if record found it will remove the old record and add new record for updated data purposes.
   * It will always have an update version of the data by checking the record from db.
   * @param search SearchResult
   * */
    private fun setSearchResult(search: SearchResult) {
        val data = search.data
        if (!mFavourites.contains(prepareFavouriteModel(search))) {
            val favouriteModel = prepareFavouriteModel(search)
            mFavourites.add(favouriteModel)
            mFavourites = mOpenSooqDao.favouriteCities as ArrayList<FavouriteModel>
            mFavourites.forEach {
                if (data?.request?.get(0)?.query == it.query){
                    mOpenSooqDao.deleteFavouriteModel(it)
                }
            }
            mOpenSooqDao.insertFavouriteCity(favouriteModel)
            mFavouriteCities.setSearchResults(mFavourites)
            if (etSearch.text?.isNotEmpty() == true && search.data != null) {
                notify(getString(R.string.message_result_added))
            }
        } else {
            notify(getString(R.string.message_result_already_added))
        }
    }

    /*
    * This to prepare the list view to make adapter more accessible and
    * iterable. We are adding the city as a key and the SearchResult for details of that key
    * weather update
    * @param searchResult SearchResult
    * @return A new instance of FavouriteModel.
    * */
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
                    //getSearchService(search)

                    /*
                    * Intentionally commenting it out to just demonstrate that we can add
                    * search here by just observing edit text or we can search the result by
                    * pressing the search icon from keypad in @etSearchEditorActionListener()
                    * */
                }
            }
        })
    }


    /*
    * we can add search results here by just pressing the search icon
    * from keypad in @etSearchEditorActionListener()
    * @param etSearch EditText
    * */
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

    /*
    * It will show either the @rvCityCardsView RecyclerView if result found or if no record found
    * or empty view then it will show no result found @tvNoResultFound TextView
    * @param isVisible Boolean.
    * @param rvCityCardsView RecyclerView
    * @param tvNoResultFound TextView
    * */
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


    /*
    * It is the requirement of the task to show the data from database or API.
    * It will initially check the data from the Room Database for stored Cities.
    * If data found from the DB, it will show the record in adapterView.
    * Or it will fetch the latest data from the API and show in adapterView.
    *
    *  NOTE: I am manually adding KEY params to demonstrate and fetch the record as mentioned in the
    * Task statement for the stated cities i.e Amman, Irbid, Aqaba
    * */
    private fun getDataFromRepoOrNetwork(){
        mFavourites = mOpenSooqDao.favouriteCities as ArrayList<FavouriteModel>
        if (mFavourites.isNotEmpty()){
            showHide(isVisible = true)
            mFavouriteCities.setSearchResults(mFavourites)
        } else {
            /*
            * NOTE: I am manually adding KEY params to demonstrate and fetch the record as mentioned in the
            * Task statement for the stated cities i.e Amman, Irbid, Aqaba
            * */
            getSearchService(KEY_AMMAN)
            getSearchService(KEY_IRBID)
            getSearchService(KEY_AQABA)
        }
    }

    /*
    * Call on exit
    * Destroy the database instance to avoid memory leaks
    * */
    override fun onDestroy() {
        openSooqDatabase?.destroyInstance()
        super.onDestroy()
    }
}
