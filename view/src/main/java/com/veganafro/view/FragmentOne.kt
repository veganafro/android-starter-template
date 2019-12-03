package com.veganafro.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.veganafro.contract.GenericActivity
import com.veganafro.contract.GenericFragment
import com.veganafro.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_one_view.fragment_one_text_view
import javax.inject.Inject

class FragmentOne @Inject constructor(
    private val presenter: Presenter
) : Fragment(R.layout.fragment_one_view), GenericFragment {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_one_view, container, false)

        presenter.fragment = this

        // uncomment this when you've connected the service to a valid endpoint
        // presenter.subscribe()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_one_text_view.text = "Hello world! Click on me!"
        fragment_one_text_view.setOnClickListener {
            onItemClickedCallback()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.fragment = null
        presenter.unsubscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onFetchDataStarted() {
        Log.v("AST|FragmentOne", "called onFetchDataStarted")
    }

    override fun onFetchDataCompleted() {
        Log.v("AST|FragmentOne", "called onFetchDataCompleted")
    }

    override fun onFetchDataSuccess(arg: Any?) {
        Log.v("AST|FragmentOne", "called onFetchDataSuccess")
    }

    override fun onFetchDataError(throwable: Throwable) {
        Log.e("AST|FragmentOne", "called onFetchDataError")
    }

    private fun onItemClickedCallback() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Log.v("AST|FragmentOne", "called onItemClickedCallback")
            (requireActivity() as GenericActivity).goToScreen()
        }
    }
}
