package com.monitech.favore_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.monitech.favore_app.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnReturn: ImageButton = findViewById(R.id.btnReturn)
        btnReturn.setOnClickListener(){
            finish()
        }

        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        btnSignIn.setOnClickListener(){
            val instance = Intent(this, SignInClientActivity::class.java)
            startActivity(instance)
        }

        val btnClient: Button = findViewById(R.id.btnClient)
        val btnFreelancer: Button = findViewById(R.id.btnFreelancer)


        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener(){
        }
    }
}