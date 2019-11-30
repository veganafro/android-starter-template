package com.veganafro.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.veganafro.contract.GenericFragment
import kotlinx.android.synthetic.main.fragment_two_view.fragment_two_text_view
import javax.inject.Inject

class FragmentTwo @Inject constructor(
) : Fragment(R.layout.fragment_two_view), GenericFragment {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_two_view, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_two_text_view.text = "Welcome to fragment two!"
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
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
}
