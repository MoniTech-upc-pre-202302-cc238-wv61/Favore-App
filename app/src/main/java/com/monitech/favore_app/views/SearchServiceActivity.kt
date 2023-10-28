package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monitech.favore_app.R

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_service)

        val btnServices = findViewById<View>(R.id.btn_list_services)

        val maintenanceServices = findViewById<View>(R.id.ll_maintenance_services)
        maintenanceServices.setOnClickListener {
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.selectedItemId = R.id.navigation_home
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, ClientHomeActivity::class.java))
                    finish()
                }
                R.id.navigation_orders -> {
                    startActivity(Intent(this, FreelancerFavorsManagementActivity::class.java))
                    finish()
                }
                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchServiceActivity::class.java))
                    finish()
                }
                R.id.navigation_inbox -> {
                    startActivity(Intent(this, AddFavorActivity::class.java))
                    finish()
                }
                R.id.navigation_user -> {
                    startActivity(Intent(this, SignInClientActivity::class.java))
                    finish()
                }
            }
            true
        }

        btnServices.setOnClickListener {
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
        }

    }
}