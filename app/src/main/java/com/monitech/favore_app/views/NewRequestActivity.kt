package com.monitech.favore_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.monitech.favore_app.R

class NewRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_request)

        val btnSendRequest: Button = findViewById(R.id.btnSendRequest)


        btnSendRequest.setOnClickListener(){

        }

    }
}