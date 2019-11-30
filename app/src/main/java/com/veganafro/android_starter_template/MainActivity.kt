package com.veganafro.android_starter_template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.veganafro.contract.GenericActivity
import com.veganafro.injector.DaggerAppComponent
import com.veganafro.view.FragmentOne
import com.veganafro.view.FragmentTwo
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.fragment_container

class MainActivity : AppCompatActivity(), GenericActivity {

    private val dagger = DaggerAppComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(
            ContextCompat.getColor(this, R.color.colorAccent)
        )
        supportActionBar?.title = "android-starter-template"

        supportFragmentManager.fragmentFactory = dagger.fragmentFactory()

        fragment_container?.let {
            // if the activity is being restored from a different state,
            // don't do anything and return, otherwise there could be overlapping
            // fragments
            savedInstanceState?.let {
                return
            }

            val fragmentOne = supportFragmentManager
                .fragmentFactory
                .instantiate(
                    classLoader,
                    FragmentOne::class.java.canonicalName!!
                )
            fragmentOne.arguments = intent.extras

            supportFragmentManager
                .beginTransaction()
                .add(it.id, fragmentOne)
                .commit()
        }
    }

    override fun goToScreen() {
        fragment_container?.let {

            val fragmentTwo = supportFragmentManager
                .fragmentFactory
                .instantiate(
                    classLoader,
                    FragmentTwo::class.java.canonicalName!!
                )
            fragmentTwo.arguments = intent.extras

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(fragmentTwo.tag)
                .replace(it.id, fragmentTwo)
                .commit()
        }
    }
}
