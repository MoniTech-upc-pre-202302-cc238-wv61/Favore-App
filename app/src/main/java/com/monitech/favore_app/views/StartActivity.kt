package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.monitech.favore_app.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val btnSignIn: Button = findViewById(R.id.btnSignIn)

        btnSignIn.setOnClickListener(){
            val instance = Intent(this, SignInActivity::class.java)
            startActivity(instance)
        }
    }
}