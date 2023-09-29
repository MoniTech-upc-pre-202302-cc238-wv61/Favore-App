package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.monitech.favore_app.R

class ConfigureAvailabilityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_availability)

        val btnSaveAvailabilityChanges:Button = findViewById(R.id.btnSaveAvailabilityChanges)

        btnSaveAvailabilityChanges.setOnClickListener(){

        }
    }
}