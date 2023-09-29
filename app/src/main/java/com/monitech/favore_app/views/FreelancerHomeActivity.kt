package com.monitech.favore_app.views

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

        btnAddNewFavor.setOnClickListener(){

        }
        btnConfigureAvailability.setOnClickListener(){

        }
        btnConfigurePortfolio.setOnClickListener(){

        }
    }
}