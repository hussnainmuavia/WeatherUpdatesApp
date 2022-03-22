package com.android.opensooq.features.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.opensooq.AndroidApplication
import com.android.opensooq.core.di.ApplicationComponent
import javax.inject.Inject

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {

    private var v: View? = null
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(layoutId(), container, false)
        v?.isClickable = true
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view, savedInstanceState)
    }

    open fun initViews(parent: View, savedInstanceState: Bundle?) {}

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
