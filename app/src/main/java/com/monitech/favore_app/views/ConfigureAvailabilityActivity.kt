package com.monitech.favore_app.views

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.monitech.favore_app.R

class ConfigureAvailabilityActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_availability)
        loadUserAndConfigureNavBar()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val btnSaveAvailabilityChanges:Button = findViewById(R.id.btnSaveAvailabilityChanges)

        btnSaveAvailabilityChanges.setOnClickListener(){

        }

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            finish()
        }

    }
}