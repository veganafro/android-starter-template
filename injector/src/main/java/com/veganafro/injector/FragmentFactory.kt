package com.veganafro.injector

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.veganafro.view.FragmentOne
import com.veganafro.view.FragmentTwo
import javax.inject.Inject
import javax.inject.Provider

class FragmentFactory @Inject constructor(
    private val fragmentOneProvider: Provider<FragmentOne>,
    private val fragmentTwoProvider: Provider<FragmentTwo>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            FragmentOne::class.java.canonicalName -> fragmentOneProvider.get()
            FragmentTwo::class.java.canonicalName -> fragmentTwoProvider.get()
            else -> TODO("Missing fragment $className")
        }
    }
}
