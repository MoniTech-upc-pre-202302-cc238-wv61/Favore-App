package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.dto.UserLoginDTO
import com.monitech.favore_app.services.LoginService

class SignInFreelancerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_freelancer)

        val btnReturn: ImageButton = findViewById(R.id.btnReturn)
        btnReturn.setOnClickListener(){
            finish()
        }

        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener(){
            val instance = Intent(this, SignUpActivity::class.java)
            startActivity(instance)
        }

        val loginService = LoginService()

        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        btnSignIn.setOnClickListener(){

            //get the value of the text field inputs
            val emailField: TextInputLayout = findViewById(R.id.emailTextField)
            val passwordField: TextInputLayout = findViewById(R.id.passwordTextField)

            val userLoginDTO = UserLoginDTO(
                emailField.editText?.text.toString(),
                passwordField.editText?.text.toString()
            )

            loginService.login(userLoginDTO) { user, message ->
                if (user != null) {
                    if (user.type!="FREELANCER") {
                        val txtError: TextView = findViewById(R.id.txtError)
                        txtError.text="You are not a freelancer"
                    } else {
                        val instance = Intent(this, FreelancerHomeActivity::class.java)

                        // Save user data to shared preferences
                        val storedUser = Gson().toJson(user)
                        val sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("user", storedUser).apply()

                        startActivity(instance)
                    }
                } else {
                    val txtError: TextView = findViewById(R.id.txtError)
                    txtError.text=message
                }
            }
        }
    }
}