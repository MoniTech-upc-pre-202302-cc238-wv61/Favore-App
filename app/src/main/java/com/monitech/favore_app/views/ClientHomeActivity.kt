package com.monitech.favore_app.views

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.monitech.favore_app.R
import com.google.android.material.search.SearchBar
import com.google.gson.Gson
import com.monitech.favore_app.models.User

class ClientHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        val searchBar = findViewById<SearchBar>(R.id.searchBar)

        searchBar.setOnClickListener {
            val intent = Intent(this, SearchServiceActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
        val json = sharedPreferences.getString("user", "")
        val user = Gson().fromJson(json, User::class.java)

        val txtHello = findViewById<TextView>(R.id.txtHelloClient)

        txtHello.text = "Hello, ${user.name}!"

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.selectedItemId = R.id.navigation_home
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, ClientHomeActivity::class.java))
                    finish()
                }
                R.id.navigation_orders -> {
//                    startActivity(Intent(this, FreelancerFavorsManagementActivity::class.java))
                    startActivity(Intent(this, ClientContractsManagement::class.java))
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
    }
}
