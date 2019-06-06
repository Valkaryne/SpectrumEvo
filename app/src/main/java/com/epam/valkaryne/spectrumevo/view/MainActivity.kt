package com.epam.valkaryne.spectrumevo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epam.valkaryne.spectrumevo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: setStartFragment()
    }

    private fun setStartFragment() {
        val listFragment = SpectrumListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, listFragment)
            .commit()
    }
}
