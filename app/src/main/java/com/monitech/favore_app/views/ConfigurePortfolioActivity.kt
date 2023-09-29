package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.monitech.favore_app.R

class ConfigurePortfolioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_portfolio)

        val btnResetChanges:Button = findViewById(R.id.btnResetPortfolioChanges)
        val btnSaveChanges:Button = findViewById(R.id.btnSavePortfolioChanges)
        val portfolioDescription:EditText = findViewById(R.id.portfolioDescription)

        btnResetChanges.setOnClickListener(){

        }
        btnSaveChanges.setOnClickListener(){

        }
    }
}