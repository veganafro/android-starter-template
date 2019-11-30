package com.veganafro.injector

import androidx.fragment.app.FragmentFactory
import dagger.Module
import dagger.Provides

@Module
class FragmentFactoryModule {

    @Provides
    fun provideFragmentFactory(fragmentFactory: FragmentFactory): FragmentFactory = fragmentFactory
}
