package com.krp.zipcodeapi.zipcodebyradius.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.krp.zipcodeapi.zipcodebyradius.R

/**
 * [ZipcodeByRadiusActivity]
 * Screen as an entry point into the feature "Zip code by radius" to display the UI.
 */
class ZipcodeByRadiusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zipcode_by_radius)
        loadFragment(ZipcodeByRadiusFragment.newInstance(), ZipcodeByRadiusFragment.TAG)
    }

    /**
     * loads the fragment into the container with the tag name.
     * @param fragment instance of a [Fragment] to load.
     * @param tag name to use to tie or find for the fragment.
     */
    private fun loadFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag)
            .commit()
    }
}