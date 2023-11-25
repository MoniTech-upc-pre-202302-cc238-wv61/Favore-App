package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.models.User

open class BaseActivity : AppCompatActivity() {

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun loadUserAndConfigureNavBar() {
        val sharedPreferences = getSharedPreferences("favore", MODE_PRIVATE)
        val json = sharedPreferences.getString("user", "")
        if (json != null) {
            if (json.isNotEmpty()) {
                user = Gson().fromJson(json, User::class.java)
                configureNavBar(user.type)
            } else {
                finish()
            }
        }
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
                R.id.navigation_home_client -> {
                    val intent = Intent(this, ClientHomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_home_freelancer -> {
                    val intent = Intent(this, FreelancerHomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_orders_client -> {
                    val intent = Intent(this, ClientContractsManagement::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_orders_freelancer -> {
                    val intent = Intent(this, FreelancerManageContracts::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navitagion_favors_freelancer -> {
                    val intent = Intent(this, FreelancerFavorsManagementActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_search -> {
                    val intent = Intent(this, SearchServiceActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_user -> {
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
            true
        }
    }
}

