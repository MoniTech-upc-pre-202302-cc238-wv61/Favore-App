package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.search.SearchBar
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.models.User

class FreelancerHomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_home)
        val btnAddNewFavor:LinearLayout = findViewById(R.id.btnAddNewFavor)
        val btnConfigureAvailability:LinearLayout = findViewById(R.id.btnConfigureAvailability)

        val btnViewFreelancerFavors:LinearLayout = findViewById(R.id.btnViewFreelancerFavors)
        val btnManageFreelancerContracts:LinearLayout = findViewById(R.id.btnManageFreelancerContracts)

        val sharedPreferences = getSharedPreferences("favore", MODE_PRIVATE)
        val json = sharedPreferences.getString("user", "")
        val user = Gson().fromJson(json, User::class.java)

        val txtHello = findViewById<TextView>(R.id.txtHelloFreelancer)
        txtHello.text = "Hello, ${user.name}! You are in Favorer account"


        btnAddNewFavor.setOnClickListener(){
            val instance = Intent(this, AddFavorActivity::class.java)
            startActivity(instance)
        }

        btnConfigureAvailability.setOnClickListener(){
            val instance = Intent(this, ConfigureAvailabilityActivity::class.java)
            startActivity(instance)
        }
        btnViewFreelancerFavors.setOnClickListener(){
            val instance = Intent(this, FreelancerFavorsManagementActivity::class.java)
            startActivity(instance)
        }
        btnManageFreelancerContracts.setOnClickListener(){
            val instance = Intent(this, FreelancerManageContracts::class.java)
            startActivity(instance)
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
                    startActivity(Intent(this, UserProfileActivity::class.java))
                    finish()
                }
            }
            true
        }

    }
}