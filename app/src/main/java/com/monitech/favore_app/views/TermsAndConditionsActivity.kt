package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.monitech.favore_app.R

class TermsAndConditionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)

        val acceptButton: Button = findViewById(R.id.btnAccept)
        acceptButton.setOnClickListener {
            finish()
        }
    }
}