package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.monitech.favore_app.R

class FreelancerHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freelancer_home)
        val btnAddNewFavor:LinearLayout = findViewById(R.id.btnAddNewFavor)
        val btnConfigureAvailability:LinearLayout = findViewById(R.id.btnConfigureAvailability)
        val btnConfigurePortfolio:LinearLayout = findViewById(R.id.btnConfigurePortfolio)

        val btnViewFreelancerFavors:LinearLayout = findViewById(R.id.btnViewFreelancerFavors)

        btnAddNewFavor.setOnClickListener(){
            val instance = Intent(this, AddFavorActivity::class.java)
            startActivity(instance)
        }
        btnConfigureAvailability.setOnClickListener(){
            val instance = Intent(this, ConfigureAvailabilityActivity::class.java)
            startActivity(instance)
        }
        btnConfigurePortfolio.setOnClickListener(){
            val instance = Intent(this, ConfigurePortfolioActivity::class.java)
            startActivity(instance)
        }
        btnViewFreelancerFavors.setOnClickListener(){
            val instance = Intent(this, FreelancerFavorsManagementActivity::class.java)
            startActivity(instance)
        }
    }
}