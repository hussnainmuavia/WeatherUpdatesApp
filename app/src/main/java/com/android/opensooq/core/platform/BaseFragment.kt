package com.android.opensooq.core.platform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.opensooq.AndroidApplication
import com.android.opensooq.R
import com.android.opensooq.core.di.ApplicationComponent
import com.android.opensooq.features.main.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {

    private var v: View? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    protected fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    protected fun showKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private val viewContainer: View get() = (activity as MainActivity).fragmentContainer

    internal fun notify(message: String) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    protected fun addFragment(fragment: Fragment) {
        val transaction = this.activity?.supportFragmentManager?.beginTransaction()
        transaction?.add(R.id.fragmentContainer, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
