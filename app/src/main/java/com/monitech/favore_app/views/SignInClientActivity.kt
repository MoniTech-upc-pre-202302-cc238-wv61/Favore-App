package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.monitech.favore_app.R

class SignInClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_client)

        val btnFreelancer: Button = findViewById(R.id.btnFreelancer)
        btnFreelancer.setOnClickListener(){
            val instance = Intent(this, SignInFreelancerActivity::class.java)
            startActivity(instance)
        }

        val btnReturn: ImageButton = findViewById(R.id.btnReturn)
        btnReturn.setOnClickListener(){
            finish()
        }

        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener(){
            val instance = Intent(this, SignUpActivity::class.java)
            startActivity(instance)
        }

        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        btnSignIn.setOnClickListener(){
            val instance = Intent(this, ClientHomeActivity::class.java)
            startActivity(instance)
        }
    }
}