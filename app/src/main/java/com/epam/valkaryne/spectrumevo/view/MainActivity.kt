package com.epam.valkaryne.spectrumevo.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.epam.valkaryne.spectrumevo.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

/**
 * [MainActivity] contains action bar with logo, bottom navigation view and container for fragments.
 *
 * @author Valentine Litvin
 */
class MainActivity : AppCompatActivity() {

    private val isPortrait
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isPortrait) {
            setUpPortraitView(savedInstanceState)
        } else {
            setUpLandView(savedInstanceState)
        }
    }

    private fun setUpPortraitView(savedInstanceState: Bundle?) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_default -> setFragment(SpectrumListFragment())
                R.id.action_spectrum -> setFragment(SpectrumPageFragment())
            }
            true
        }

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.action_spectrum
        }

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun setUpLandView(savedInstanceState: Bundle?) {
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_default -> setFragment(SpectrumListFragment())
                R.id.action_spectrum -> setFragment(SpectrumPageFragment())
            }
            true
        }

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.action_spectrum)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, fragment)
            .commit()
    }
}
