package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.monitech.favore_app.R

class ServiceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        val btnAddRequest: Button = findViewById(R.id.btnAddRequest)


        btnAddRequest.setOnClickListener(){
            val instance = Intent(this, NewRequestActivity::class.java)
            startActivity(instance)
        }

    }

}