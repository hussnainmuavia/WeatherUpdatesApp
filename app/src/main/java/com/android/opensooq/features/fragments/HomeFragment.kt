package com.android.opensooq.features.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.opensooq.R
import com.android.opensooq.core.dao.OpenSooqDatabase
import com.android.opensooq.core.utils.HomeConstants
import com.android.opensooq.core.utils.State
import com.android.opensooq.features.adapters.FavouriteCitiesAdapter
import com.android.opensooq.features.callbacks.OnItemClickListener
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.core.models.response.SearchResult
import com.android.opensooq.features.viewmodels.HomeViewModel


class HomeFragment : BaseFragment(), OnItemClickListener {

    override fun layoutId() = R.layout.fragment_home

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var openSooqDatabase: OpenSooqDatabase
    private lateinit var mSearchResult: SearchResult
    private lateinit var mFavouriteCities: FavouriteCitiesAdapter
    private var mFavourites : ArrayList<FavouriteModel> = ArrayList()

    private lateinit var mView: View
    private lateinit var rvCityCardsView: RecyclerView
    private lateinit var mProgressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        rvCityCardsView = mView?.findViewById(R.id.rvCityCardsView)
        mProgressBar = mView?.findViewById(R.id.progressBar)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)

        //initDatabaseInstance()
        initViewModels()
        observeStateLoader()
        initAdapterViews()

        mHomeViewModel.getSearchResults(
            "e5ea508aa7174598bc4104948222003",
            "Lahore",
            "2",
            "3",
            "json"
        )
        mHomeViewModel.mSearchResult?.observe(this,
            Observer<SearchResult> { search ->
                if (search?.data != null) {
                    try {
                        mSearchResult = search

                        val favouriteModel = FavouriteModel()
                        favouriteModel.query = search?.data?.request?.get(0)?.query
                        favouriteModel.type = search?.data?.request?.get(0)?.type
                        favouriteModel.searchResult = search
                        mFavourites.add(favouriteModel)
                        mFavouriteCities.setSearchResults(mFavourites)

                        try {
                            //musicDatabase!!.musicDao().deleteAllItems()
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }

                       /* for (ms247 in ms247List) {
                            for (ms247Item in ms247) {
                                musicDatabase!!.musicDao().insertItems(ms247Item.items)
                            }
                        }*/
                        Log.d(HomeConstants.TAG, HomeConstants.TAG_SAVED)

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            })
    }

    private fun initViewModels() {
        mHomeViewModel =
            ViewModelProviders
                .of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    private fun observeStateLoader(){
        mHomeViewModel?.getState().observe(viewLifecycleOwner , Observer{
            when(it){
                State.LOADING -> mProgressBar.visibility = View.VISIBLE
                State.ERROR -> mProgressBar.visibility = View.GONE
                State.DONE -> mProgressBar.visibility = View.GONE
            }
        })
    }

    private fun initDatabaseInstance() {
        this.context?.let { context ->
            OpenSooqDatabase.getDatabaseInstance(context)?.let { dbInstance ->
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

    private fun addFragment(fragment: Fragment, position: Int) {
        val transaction = this.activity!!.supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        //bundle.putParcelable(HomeConstants.KEY_ITEMS, ms247?.get(position)!!.items)
        fragment?.arguments = bundle
        //transaction.add(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        openSooqDatabase?.destroyInstance()
        super.onDestroy()
    }

    private fun saveFavouriteCity(position: Int) {
        /*try {
            val album = ms247?.get(position)?.items?.album
            var count = ms247?.get(position)?.items?.count
            var track = ms247?.get(position)?.items?.track

            val favouriteTrack = FavouriteTracks(position, album, count, track)
            openSooqDatabase!!.musicDao().insertFavouriteItems(favouriteTrack)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }*/
    }

    override fun onUserItemClickListener(position: Int) {

    }
}
