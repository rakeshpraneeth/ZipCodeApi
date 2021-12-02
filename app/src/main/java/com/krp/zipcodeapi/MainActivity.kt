package com.krp.zipcodeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krp.zipcodeapi.zipcodebyradius.ui.ZipcodeByRadiusActivity

/**
 * [MainActivity]
 * Launcher activity and will be called when user opens/launches the app.
 * When user launches, we are redirecting the user to [ZipcodeByRadiusActivity] to display the feature.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, ZipcodeByRadiusActivity::class.java))
        finish()
    }
}