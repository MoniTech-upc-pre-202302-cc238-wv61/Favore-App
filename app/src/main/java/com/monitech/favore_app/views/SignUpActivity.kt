package com.monitech.favore_app.views

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputLayout
import com.monitech.favore_app.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var selected = "";

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

        btnClient.setOnClickListener(){
            //change the color of the button
            btnClient.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.md_theme_light_secondary));
            btnClient.setTextColor(resources.getColor(R.color.white));
            if(selected=="FREELANCER"){
                btnFreelancer.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white));
                btnFreelancer.setTextColor(resources.getColor(R.color.md_theme_light_secondary));
            }
            selected = "CLIENT";
        }

        btnFreelancer.setOnClickListener(){
            btnFreelancer.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.md_theme_light_secondary));
            btnFreelancer.setTextColor(resources.getColor(R.color.white));
            if(selected=="CLIENT"){
                btnClient.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white));
                btnClient.setTextColor(resources.getColor(R.color.md_theme_light_secondary));
            }
            selected = "FREELANCER";
        }

        val nameTextField: TextInputLayout = findViewById(R.id.nameTextField)
        val emailTextField: TextInputLayout = findViewById(R.id.emailTextField)
        val passwordTextField: TextInputLayout = findViewById(R.id.passwordTextField)

        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener(){
            Log.d("selected", selected)
            Log.d("name", nameTextField.editText?.text.toString())
            Log.d("email", emailTextField.editText?.text.toString())
            Log.d("password", passwordTextField.editText?.text.toString())

        }
    }
}