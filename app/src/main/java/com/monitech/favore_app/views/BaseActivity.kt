package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.monitech.favore_app.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val HomeFragment = HomeFragment()
        val OrdersFragment = OrdersFragment()
        val SearchFragment = SearchFragment()
        val InboxFragment = InboxFragment()
        val UserFragment = UserFragment()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, HomeFragment())
            commit()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, HomeFragment())
                        commit()
                    }
                    true
                }
                R.id.navigation_orders -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, OrdersFragment())
                        commit()
                    }
                    true
                }
                R.id.navigation_search -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, SearchFragment())
                        commit()
                    }
                    true
                }
                R.id.navigation_inbox -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, InboxFragment())
                        commit()
                    }
                    true
                }
                R.id.navigation_user -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, UserFragment())
                        commit()
                    }
                    true
                }

                else -> false
            }
        }
    }
}