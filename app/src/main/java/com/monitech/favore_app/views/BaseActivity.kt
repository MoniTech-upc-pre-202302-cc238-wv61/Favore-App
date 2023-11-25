package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.monitech.favore_app.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun configureNavBar(userType: String) {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.menu.clear()
        if (userType == "Freelancer") {
            bottomNavigation.inflateMenu(R.menu.menu_freelancer)
        } else {
            bottomNavigation.inflateMenu(R.menu.menu_client)
        }

        setUpBottomNavigationListener(bottomNavigation)
    }

    private fun setUpBottomNavigationListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                }
                R.id.navigation_orders -> {
                }
                R.id.navigation_search -> {
                }
            }
            true
        }
    }
}

