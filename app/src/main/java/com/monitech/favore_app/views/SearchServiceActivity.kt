package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monitech.favore_app.R

import android.content.Intent
import android.view.View

class SearchServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_service)

        val maintenanceServices = findViewById<View>(R.id.ll_maintenance_services)
        maintenanceServices.setOnClickListener {
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
        }
    }
}