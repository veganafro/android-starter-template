package com.veganafro.injector

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkingModule::class,
    FragmentFactoryModule::class
])
interface AppComponent {

    fun fragmentFactory(): FragmentFactory
}
