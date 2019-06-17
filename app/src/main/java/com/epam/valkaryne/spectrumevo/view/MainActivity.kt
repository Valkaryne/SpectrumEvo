package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.epam.valkaryne.spectrumevo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, fragment)
            .commit()
    }
}
